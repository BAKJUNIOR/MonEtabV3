package ci.digitalacademy.monetabv03.monetabv03.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class UserDTO {

    private Long id_user;

    private String pseudo;

    private String password;

    private Instant createdDate;

    private Set<RoleUserDTO> roleUser;

    private SchoolDTO school;
//    private boolean active;

}
