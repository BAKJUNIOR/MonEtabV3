package ci.digitalacademy.monetabv03.monetabv03.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
public class RegistrationStudentDTO  {

    private String firstName;
    private String lastName;
    private String matricule;
    private String email;
    private String country;
    private String city;
    private String street;
    private String pseudo;
}
