package ci.digitalacademy.monetabv03.monetabv03.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class StudentDTO extends PersonDTO{

    private String matricule;
//    private List<AbsenceDTO> absence;
    private String phoneNumberParent;

}
