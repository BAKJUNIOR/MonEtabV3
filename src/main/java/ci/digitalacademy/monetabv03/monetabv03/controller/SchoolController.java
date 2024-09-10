package ci.digitalacademy.monetabv03.monetabv03.controller;


import ci.digitalacademy.monetabv03.monetabv03.service.AppService;
import ci.digitalacademy.monetabv03.monetabv03.service.AppSettingService;
import ci.digitalacademy.monetabv03.monetabv03.service.FileStorageService;
import ci.digitalacademy.monetabv03.monetabv03.service.SchoolService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;
import java.time.Instant;
import java.util.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("schools")
public class SchoolController {


    private final SchoolService schoolService;
    private final AppSettingService appSettingService;
    private final AppService appService;
    private final FileStorageService fileStorageService;
    private final BCryptPasswordEncoder passwordEncoder;



    @GetMapping
    public String showAddAppSettingPage(Model model) {
        model.addAttribute("schools", new RegistrationSchoolDTO());
        return "School/form";
    }

    @PostMapping("/postSchools")
    public String saveSchool(@ModelAttribute RegistrationSchoolDTO registrationSchoolDTO) throws IOException {
        String upload = fileStorageService.upload(registrationSchoolDTO.getFile());
        AppSettingDTO settingDTO = appSettingService.findAll().stream().findFirst().orElse(null);
        registrationSchoolDTO.setAppSetting(settingDTO);
        registrationSchoolDTO.setUrlLogo(upload);
        SchoolDTO savedSchool = schoolService.save(registrationSchoolDTO);

        // Créer les utilisateurs associés à l'école
        SaveUser(savedSchool);
        return "redirect:/";
    }


    @GetMapping("/update")
    public String showUpdateProfessorForm( Model model){
        SchoolDTO schoolDTO = schoolService.findAll().stream().findFirst().orElse(null);
        RegistrationSchoolDTO registrationSchoolDto = new RegistrationSchoolDTO();
        registrationSchoolDto.setAppSetting(schoolDTO.getAppSetting());
        registrationSchoolDto.setUrlLogo(schoolDTO.getUrlLogo());
        registrationSchoolDto.setNameSchool(schoolDTO.getNameSchool());
        registrationSchoolDto.setId_school(schoolDTO.getId_school());
        model.addAttribute("schools", registrationSchoolDto);
        return "School/form";

    }



    public void SaveUser(SchoolDTO school) {
        // Création des rôles
        RoleUserDTO roleAdmin = new RoleUserDTO();
        RoleUserDTO roleUser = new RoleUserDTO();
        RoleUserDTO roleOther = new RoleUserDTO();

        roleAdmin.setNameRole("ADMIN");
        roleUser.setNameRole("USER");
        roleOther.setNameRole("OTHER");

        List<RoleUserDTO> roleUserDTOS = List.of(roleAdmin, roleUser, roleOther);
        roleUserDTOS = appService.initRoleUser(roleUserDTOS); // Initialiser les rôles

        // Création des ensembles de rôles pour chaque utilisateur
        Set<RoleUserDTO> roleUserSet1 = new HashSet<>();
        roleUserSet1.add(roleUserDTOS.get(0)); // ADMIN

        Set<RoleUserDTO> roleUserSet2 = new HashSet<>();
        roleUserSet2.add(roleUserDTOS.get(1)); // USER

        Set<RoleUserDTO> roleUserSet3 = new HashSet<>();
        roleUserSet3.add(roleUserDTOS.get(2)); // OTHER

// Création des utilisateurs
        UserDTO user1 = new UserDTO();
        user1.setPseudo("Bak");
        user1.setCreatedDate(Instant.now());
        String password1 = passwordEncoder.encode("bak123@");
        user1.setPassword(password1);
        user1.setRoleUser(roleUserSet3);


        UserDTO user2 = new UserDTO();
        user2.setPseudo("Bakus");
        user2.setCreatedDate(Instant.now());
        String password2 = passwordEncoder.encode("bak123@");
        user2.setPassword(password2);
        user1.setRoleUser(roleUserSet2);

        UserDTO user3 = new UserDTO();
        user3.setPseudo("Junior");
        user3.setCreatedDate(Instant.now());
        String password3 = passwordEncoder.encode("bak123@");
        user3.setPassword(password3);
        user1.setRoleUser(roleUserSet1);

        List<UserDTO> userList = List.of(user1, user2, user3);

        // Initialisation les utilisateurs avec les rôles et l'école
        appService.initUser(roleUserDTOS, school, userList);


}
}