package ci.digitalacademy.monetabv03.monetabv03.service.dto;

;
import ci.digitalacademy.monetabv03.monetabv03.models.Gender;
import ci.digitalacademy.monetabv03.monetabv03.models.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;


@Getter
@Setter
@ToString
public class PersonDTO {


    private Long id_person;

    private String firstName;

    private String lastName;

    private String numbers;

    private Date dateOfBirth;

    private String urlPicture;

    private Gender gender;

    private AddressDTO address;

    private User user;

}
