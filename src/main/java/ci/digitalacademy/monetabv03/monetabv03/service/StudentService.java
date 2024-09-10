package ci.digitalacademy.monetabv03.monetabv03.service;


import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



public interface StudentService {

    StudentDTO save(StudentDTO studentDTO);
    StudentDTO update(StudentDTO studentDTO);
    StudentDTO update(StudentDTO studentDTO , Long id);
    Optional<StudentDTO> findById(Long id);
    List<StudentDTO> findAll();
    void deleteById(Long id);
    List<StudentDTO> findByLastNameOrGenderOrMatricule(String query , String gender);
}
