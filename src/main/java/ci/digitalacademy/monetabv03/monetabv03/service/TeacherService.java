package ci.digitalacademy.monetabv03.monetabv03.service;


import ci.digitalacademy.monetabv03.monetabv03.service.dto.TeacherDTO;

import java.util.List;
import java.util.Optional;


public interface TeacherService {

    TeacherDTO save(TeacherDTO teacherDTO);
    TeacherDTO update(TeacherDTO teacherDTO);
    Optional<TeacherDTO> findById(Long id);
    List<TeacherDTO> findAll();
    void deleteById(Long id);
    List<TeacherDTO> findByLastNameOrSpecialtyAndGender(String query, String gender);
}
