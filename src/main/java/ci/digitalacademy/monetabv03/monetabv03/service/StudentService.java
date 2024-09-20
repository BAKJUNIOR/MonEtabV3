package ci.digitalacademy.monetabv03.monetabv03.service;


import ci.digitalacademy.monetabv03.monetabv03.service.dto.AbsenceDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.RegistrationStudentDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.ResponseRegisterStudentDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;



public interface StudentService {

    StudentDTO save(StudentDTO studentDTO);
    StudentDTO uploadStudentPicture(Long id , MultipartFile picture) throws IOException;
    StudentDTO update(StudentDTO studentDTO);
    StudentDTO update(StudentDTO studentDTO , Long id);
    Optional<StudentDTO> findById(Long id);
    List<StudentDTO> findAll();
    void deleteById(Long id);
    List<StudentDTO> findByLastNameOrGenderOrMatricule(String query , String gender);

    StudentDTO partialUpdate(StudentDTO studentDTO, Long id);

    ResponseRegisterStudentDTO registerStudent(RegistrationStudentDTO registrationStudentDTO);
}
