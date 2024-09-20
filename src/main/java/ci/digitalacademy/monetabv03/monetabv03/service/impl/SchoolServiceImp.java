package ci.digitalacademy.monetabv03.monetabv03.service.impl;



import ci.digitalacademy.monetabv03.monetabv03.models.School;
import ci.digitalacademy.monetabv03.monetabv03.models.Student;
import ci.digitalacademy.monetabv03.monetabv03.repositories.SchoolRepository;
import ci.digitalacademy.monetabv03.monetabv03.service.SchoolService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.SchoolDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.mapper.SchoolMapper;
import ci.digitalacademy.monetabv03.monetabv03.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolServiceImp implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    @Override
    public SchoolDTO save(SchoolDTO schoolDTO) {
        School school = schoolMapper.DtoToEntity(schoolDTO);
        school.setSlug(SlugifyUtils.generate(school.getNameSchool()));
        return schoolMapper.ToDto(schoolRepository.save(school));
    }

    @Override
    public SchoolDTO update(SchoolDTO schoolDTO) {
        return findOne(schoolDTO.getId_school()).map(existingSchool -> {
            existingSchool.setNameSchool(schoolDTO.getNameSchool());
            return save(existingSchool);
        }).orElseThrow(() -> new RuntimeException("School not found"));
    }

    @Override
    public void delete(Long id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public List<SchoolDTO> findAll() {
        return schoolRepository.findAll().stream().map(school ->{
            return schoolMapper.ToDto(school);
        }).toList();
    }

    @Override
    public List<SchoolDTO> getAll() {
        return schoolRepository.findAll().stream().map(school -> {
            return schoolMapper.ToDto(school);
        }).toList();
    }

    @Override
    public SchoolDTO initSchool(SchoolDTO schoolDTO) {
        SchoolDTO school = existingParameter();
        if (Objects.isNull(school)) {
            return save(schoolDTO);
        }
        return school;
    }

    @Override
    public SchoolDTO existingParameter() {
        List<SchoolDTO> all = getAll();
        return all.stream().findFirst().orElse(null);
    }

    @Override
    public Optional<SchoolDTO> findOne(Long id) {
        return schoolRepository.findById(id).map(address -> {
            return schoolMapper.ToDto(address);
        });
    }
}
