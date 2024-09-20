package ci.digitalacademy.monetabv03.monetabv03.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String username;
    private String password;
    private boolean rumemberMe;
}