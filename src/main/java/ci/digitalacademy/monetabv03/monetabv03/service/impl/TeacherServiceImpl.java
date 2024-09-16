package ci.digitalacademy.monetabv03.monetabv03.service.impl;

import ci.digitalacademy.monetabv03.monetabv03.models.Gender;
import ci.digitalacademy.monetabv03.monetabv03.models.Student;
import ci.digitalacademy.monetabv03.monetabv03.models.Teacher;
import ci.digitalacademy.monetabv03.monetabv03.repositories.StudentRepository;
import ci.digitalacademy.monetabv03.monetabv03.repositories.TeacherRepository;
import ci.digitalacademy.monetabv03.monetabv03.service.TeacherService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.TeacherDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.mapper.StudentMapper;
import ci.digitalacademy.monetabv03.monetabv03.service.mapper.TeacherMapper;
import ci.digitalacademy.monetabv03.monetabv03.service.mapping.StudentMapping;
import ci.digitalacademy.monetabv03.monetabv03.service.mapping.TeacherMapping;
import ci.digitalacademy.monetabv03.monetabv03.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service

public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.DtoToEntity(teacherDTO);
        teacher.setSlug(SlugifyUtils.generate(teacher.getSpecialty()));
        return teacherMapper.ToDto(teacherRepository.save(teacher));
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO) {
        return findById(teacherDTO.getId_person()).map(existingTeacher ->{
            existingTeacher.setLastName(teacherDTO.getLastName());
            existingTeacher.setFirstName(teacherDTO.getFirstName());
            existingTeacher.setDateOfBirth(teacherDTO.getDateOfBirth());
            existingTeacher.setNumbers(teacherDTO.getNumbers());
            existingTeacher.setSpecialty(teacherDTO.getSpecialty());
            existingTeacher.setGender(teacherDTO.getGender());
            return save(teacherDTO);
        }).orElseThrow(() ->new IllegalArgumentException());
    }

    @Override
    public TeacherDTO partialUpdate(TeacherDTO teacherDTO, Long id) {
        return teacherRepository.findById(id).map(teacher -> {
            TeacherMapping.partialUpdate(teacher,teacherDTO);
            return teacher;
        }).map(teacherRepository::save).map(teacherMapper::ToDto).orElse(null);
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO, Long id) {
        teacherDTO.setId_person(id);
        return update(teacherDTO);
    }

    @Override
    public Optional<TeacherDTO> findById(Long id) {
        return teacherRepository.findById(id).map(teacher -> {
            return teacherMapper.ToDto(teacher);
        });
    }

    @Override
    public List<TeacherDTO> findAll() {
        return teacherRepository.findAll().stream().map(teacher -> {
            return teacherMapper.ToDto(teacher);
        }).toList();
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);

    }



    @Override
    public List<TeacherDTO> findByLastNameOrSpecialtyAndGender(String query, String gender) {
        List<Teacher> teachers = teacherRepository.findByLastNameOrSpecialtyAndGender(query , query ,  Gender.valueOf(gender));
        return teachers.stream().map(teacher -> teacherMapper.ToDto(teacher)).toList();
    }

}
