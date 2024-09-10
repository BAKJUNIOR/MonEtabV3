package ci.digitalacademy.monetabv03.monetabv03.service.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SchoolDTO {

    private Long id_school;

    private String nameSchool;

    private String urlLogo;

    private AppSettingDTO appSetting;
}
