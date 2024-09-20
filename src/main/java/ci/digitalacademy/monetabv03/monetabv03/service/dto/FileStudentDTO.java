package ci.digitalacademy.monetabv03.monetabv03.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileStudentDTO extends StudentDTO{
    private MultipartFile file;
}
