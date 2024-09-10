package ci.digitalacademy.monetabv03.monetabv03.service.impl;


import ci.digitalacademy.monetabv03.monetabv03.models.Gender;
import ci.digitalacademy.monetabv03.monetabv03.models.Student;
import ci.digitalacademy.monetabv03.monetabv03.repositories.StudentRepository;
import ci.digitalacademy.monetabv03.monetabv03.service.StudentService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        Student student = studentMapper.DtoToEntity(studentDTO);
        return studentMapper.ToDto(studentRepository.save(student));
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        return findById((studentDTO.getId_person())).map(existingStudent ->{
            existingStudent.setAddress(studentDTO.getAddress());
            existingStudent.setFirstName(studentDTO.getFirstName());
            return save(existingStudent);
        }).orElseThrow(()-> new RuntimeException("Student not found"));
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO, Long id) {
        return findById(id).map(student -> {
                    student.setLastName(studentDTO.getLastName());
                    student.setFirstName(studentDTO.getFirstName());
                    student.setMatricule(studentDTO.getMatricule());
                    return save(student);
                }).orElseThrow(()-> new IllegalArgumentException("Students not found"));
    }

    @Override
    public Optional<StudentDTO> findById(Long id) {
        return studentRepository.findById(id).map(student -> studentMapper.ToDto(student));
    }


    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(student -> {
            return studentMapper.ToDto(student);
        }).toList();
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDTO> findByLastNameOrGenderOrMatricule(String query , String gender) {
        List<Student> students = studentRepository.findByLastNameIgnoreCaseOrMatriculeIgnoreCaseAndGender(query  , query , Gender.valueOf(gender));
        return students.stream().map(student -> studentMapper.ToDto(student)).toList();
    }
}
