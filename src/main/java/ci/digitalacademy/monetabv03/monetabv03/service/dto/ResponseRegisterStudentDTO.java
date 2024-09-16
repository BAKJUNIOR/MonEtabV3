package ci.digitalacademy.monetabv03.monetabv03.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseRegisterStudentDTO {

    @JsonIgnoreProperties({"id_person", "numbers", "dateOfBirth", "phoneNumber", "urlPicture", "gender", "address", "user"})
    private StudentDTO student;

    @JsonIgnoreProperties({"id_adress"})
    private AddressDTO address;
}