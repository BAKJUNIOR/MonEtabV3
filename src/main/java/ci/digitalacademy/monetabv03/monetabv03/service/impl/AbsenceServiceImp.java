package ci.digitalacademy.monetabv03.monetabv03.service.impl;

import ci.digitalacademy.monetabv03.monetabv03.models.Absence;
import ci.digitalacademy.monetabv03.monetabv03.repositories.AbsenceRepository;
import ci.digitalacademy.monetabv03.monetabv03.service.AbsenceService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AbsenceDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.mapper.AbsenceMapper;
import ci.digitalacademy.monetabv03.monetabv03.service.mapping.AbsenceMapping;
import ci.digitalacademy.monetabv03.monetabv03.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j // pour la journalisation
@Service
@RequiredArgsConstructor
public class AbsenceServiceImp implements AbsenceService {
    private final AbsenceRepository absenceRepository;
    private final AbsenceMapper absenceMapper;

    @Override
    public AbsenceDTO save(AbsenceDTO absenceDTO) {
        log.debug("Request to save absence {}", absenceDTO);
        Absence absence = absenceMapper.DtoToEntity(absenceDTO);
        absence= absenceRepository.save(absence);
        absenceDTO.setSlug(SlugifyUtils.generate(absenceDTO.getNumberAbsence().toString()));


        return absenceMapper.ToDto(absence);
    }



    @Override
    public AbsenceDTO saveAbsence(AbsenceDTO absenceDTO) {
        final String slug = SlugifyUtils.generate(absenceDTO.getNumberAbsence().toString());
        absenceDTO.setSlug(slug);
        return save(absenceDTO);
    }

    @Override
    public Optional<AbsenceDTO> findOne(Long id) {
        return absenceRepository.findById(id).map(absence -> {
            return absenceMapper.ToDto(absence);
        });
    }

    @Override
    public List<AbsenceDTO> findAll() {
        return absenceRepository.findAll().stream().map(absence -> {
            return absenceMapper.ToDto(absence);
        }).toList();
    }

    @Override
    public AbsenceDTO update(AbsenceDTO absenceDTO) {
        return findOne(absenceDTO.getId_absence()).map(existingAbsence -> {
            existingAbsence.setDateAbsence(absenceDTO.getDateAbsence());
            existingAbsence.setNumberAbsence(absenceDTO.getNumberAbsence());
            return save(existingAbsence);
        }).orElseThrow(()->new IllegalArgumentException());
    }

    @Override
    public AbsenceDTO partialUpdate(AbsenceDTO absenceDTO, Long id) {
        return absenceRepository.findById(id).map(absence -> {
            AbsenceMapping.partialUpdate(absence, absenceDTO);
            return absence;
        }).map(absenceRepository::save).map(absenceMapper::ToDto).orElse(null);
    }

    @Override
    public AbsenceDTO update(AbsenceDTO absenceDTO, Long id) {
        absenceDTO.setId_absence(id);
        return update(absenceDTO);
    }

    @Override
    public void delete(Long id) {
       absenceRepository.deleteById(id);
    }


//    @Override
//    public AbsenceDTO update(AbsenceDTO absenceDTO) {
//        return findOne(absenceDTO.getId_absence()).map(existingAbsence -> {
//            existingAbsence.setDateAbsence(absenceDTO.getDateAbsence());
//            existingAbsence.setNumberAbsence(absenceDTO.getNumberAbsence());
//            return save(existingAbsence);
//        }).orElseThrow(()->new IllegalArgumentException());
//    }
//
//    @Override
//    public AbsenceDTO update(AbsenceDTO absenceDTO, Long id) {
//        absenceDTO.setId_absence(id);
//        return update(absenceDTO);
//    }
//
//
//    @Override
//    public Optional<AbsenceDTO> findOne(Long id) {
//        return absenceRepository.findById(id).map(absence -> {
//            return absenceMapper.ToDto(absence);
//        });
//    }
//
//    @Override
//    public List<AbsenceDTO> findAll() {
//        return absenceRepository.findAll().stream().map(absence -> {
//            return absenceMapper.ToDto(absence);
//        }).toList();
//
//    }
//
//    @Override
//    public void delete(Long id) {
//
//    }
//
//
//
//    @Override
//    public AbsenceDTO partialUpdate(AbsenceDTO absenceDTO, Long id) {
//        return absenceRepository.findById(id).map(absence -> {
//            AbsenceMapping.partialUpdate(absence, absenceDTO);
//            return absence;
//        }).map(absenceRepository::save).map(absenceMapper::ToDto).orElse(null);
//}


}
