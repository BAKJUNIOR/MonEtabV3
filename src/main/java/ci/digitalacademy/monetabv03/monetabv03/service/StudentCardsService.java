package ci.digitalacademy.monetabv03.monetabv03.service;


import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentCardsDTO;

import java.util.List;

public interface StudentCardsService {

    StudentCardsDTO findById(Long id);
    List<StudentCardsDTO> findAll();
    StudentCardsDTO save(StudentCardsDTO studentCardsDTO);
    StudentCardsDTO update(StudentCardsDTO studentCardsDTO);
    void delete(Long id);
}
