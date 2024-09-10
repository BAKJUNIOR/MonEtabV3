package ci.digitalacademy.monetabv03.monetabv03.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AbsenceDTO {

    private Long id_absence;

    private Date dateAbsence;

    private int numberAbsence;

    private StudentDTO student;
}
