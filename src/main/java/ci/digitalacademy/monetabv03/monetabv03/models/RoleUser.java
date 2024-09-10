package ci.digitalacademy.monetabv03.monetabv03.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "role_user")
@ToString
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role_user")
    private Long idRoleUser;

    @Column(unique = true , name = "name_role")
    private String nameRole;



}

