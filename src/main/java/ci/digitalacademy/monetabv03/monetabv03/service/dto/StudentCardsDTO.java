package ci.digitalacademy.monetabv03.monetabv03.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
public class StudentCardsDTO {

    private Long idStudentCard;

    private String reference;

    private Date issueDate;

    private Date expiryDate;

    private StudentDTO student;
}
