package ci.digitalacademy.monetabv03.monetabv03.service;


import ci.digitalacademy.monetabv03.monetabv03.service.dto.AbsenceDTO;

import java.util.Optional;
import java.util.Set;

public interface AbsenceService {

    Optional<AbsenceDTO> findById(Long id);
    Set<AbsenceDTO> findAll();
    AbsenceDTO save(AbsenceDTO absenceDTO);
    AbsenceDTO update(AbsenceDTO absenceDTO);
    void deleteById(Long id);
}
