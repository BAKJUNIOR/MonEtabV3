package ci.digitalacademy.monetabv03.monetabv03.service.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AppSettingDTO {

    private Long id_appsetting;

    private String smtpServer;

    private String smtpPort;

    private String smtpUsername;

    private String smtpPassword;

}
