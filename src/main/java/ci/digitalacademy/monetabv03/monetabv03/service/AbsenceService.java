package ci.digitalacademy.monetabv03.monetabv03.service;


import ci.digitalacademy.monetabv03.monetabv03.service.dto.AbsenceDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AbsenceService {


    AbsenceDTO save(AbsenceDTO absenceDTO);
    AbsenceDTO saveAbsence(AbsenceDTO absenceDTO);

    Optional<AbsenceDTO> findOne(Long id);
    List<AbsenceDTO> findAll();
    AbsenceDTO update(AbsenceDTO absenceDTO);
    AbsenceDTO update(AbsenceDTO absenceDTO , Long id);
    void delete(Long id);

    AbsenceDTO partialUpdate(AbsenceDTO absenceDTO, Long id);
}
