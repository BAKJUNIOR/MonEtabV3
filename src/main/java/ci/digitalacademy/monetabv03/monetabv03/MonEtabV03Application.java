package ci.digitalacademy.monetabv03.monetabv03;

import ci.digitalacademy.monetabv03.monetabv03.models.RoleUser;
import ci.digitalacademy.monetabv03.monetabv03.service.AppService;
import ci.digitalacademy.monetabv03.monetabv03.service.UserService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.RoleUserDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class MonEtabV03Application implements CommandLineRunner {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AppService appService;


    public static void main(String[] args) {
        SpringApplication.run(MonEtabV03Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        // Création des rôles
//        RoleUserDTO roleAdmin = new RoleUserDTO( );
//        RoleUserDTO roleUser = new RoleUserDTO();
//        RoleUserDTO roleOther = new RoleUserDTO();
//
//        roleAdmin.setNameRole("ADMIN");
//        roleUser.setNameRole("USER");
//        roleOther.setNameRole("OTHER");
//
//        List<RoleUserDTO> roleUserDTOS = List.of(roleAdmin, roleUser, roleOther);
//        roleUserDTOS = appService.initRoleUser(roleUserDTOS); // Initialiser les rôles
//
//// Création des ensembles de rôles pour chaque utilisateur
//        Set<RoleUserDTO> roleUserSet1 = new HashSet<>();
//        roleUserSet1.add(roleUserDTOS.get(0)); // ADMIN
//
//        Set<RoleUserDTO> roleUserSet2 = new HashSet<>();
//        roleUserSet2.add(roleUserDTOS.get(1)); // USER
//
//        Set<RoleUserDTO> roleUserSet3 = new HashSet<>();
//        roleUserSet3.add(roleUserDTOS.get(2)); // OTHER
//
//// Création des utilisateurs
//        UserDTO user1 = new UserDTO();
//        user1.setPseudo("Bak");
//        user1.setCreatedDate(Instant.now());
//        String password1 = passwordEncoder.encode("bak123@");
//        user1.setPassword(password1);
//        user1.setRoleUser(roleUserSet3); // Correction ici
//
//        UserDTO user2 = new UserDTO();
//        user2.setPseudo("Bakus");
//        user2.setCreatedDate(Instant.now());
//        String password2 = passwordEncoder.encode("bak123@");
//        user2.setPassword(password2);
//        user2.setRoleUser(roleUserSet2); // Correction ici
//
//        UserDTO user3 = new UserDTO();
//        user3.setPseudo("Junior");
//        user3.setCreatedDate(Instant.now());
//        String password3 = passwordEncoder.encode("bak123@");
//        user3.setPassword(password3);
//        user3.setRoleUser(roleUserSet1); // Correction ici
//
//        List<UserDTO> userList = List.of(user1, user2, user3);



        RoleUserDTO roleAdmin = new RoleUserDTO( );
        RoleUserDTO roleUser = new RoleUserDTO();
        RoleUserDTO roleOther = new RoleUserDTO();

        roleAdmin.setNameRole("ADMIN");
        roleUser.setNameRole("USER");
        roleOther.setNameRole("OTHER");

        // Création d'un utilisateur avec des rôles
        UserDTO userDTO = new UserDTO();
        userDTO.setPseudo("Bak");
        userDTO.setPassword("Bak");
        userDTO.setCreatedDate(Instant.now());


    }
}