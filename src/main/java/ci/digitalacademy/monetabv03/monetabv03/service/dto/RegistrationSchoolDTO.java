package ci.digitalacademy.monetabv03.monetabv03.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
public class RegistrationSchoolDTO extends SchoolDTO {

    private MultipartFile file;
}
