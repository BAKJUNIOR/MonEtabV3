package ci.digitalacademy.monetabv03.monetabv03.controller;

import ci.digitalacademy.monetabv03.monetabv03.models.User;
import ci.digitalacademy.monetabv03.monetabv03.service.RoleUserService;
import ci.digitalacademy.monetabv03.monetabv03.service.UserService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.RoleUserDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.SetUserDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleUserService roleUserService;
    private final BCryptPasswordEncoder passwordEncoder;


    @GetMapping
    public String showUserPage(HttpServletRequest request, Model model){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<UserDTO> users = userService.getAll();
        List<RoleUserDTO> roleUser = roleUserService.getAll();
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("roleUsers", roleUser);
        model.addAttribute("namee", name);
        return "user/list";
    }


    @GetMapping("/add")
    public String showAddUserPage(HttpServletRequest request, Model model){
        List<RoleUserDTO> all = roleUserService.getAll();
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("useradd", new SetUserDTO());
        model.addAttribute("roles", all);
        return "user/forms";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute SetUserDTO user, Model model) {
        user.setCreatedDate(Instant.now());
        user.setActive(true);

        // Vérifie si le roleId est null
        if (user.getRoleName() == null) {
            model.addAttribute("error", "Le rôle doit être sélectionné.");
            model.addAttribute("roles", roleUserService.getAll());
            model.addAttribute("useradd", user);
            return "user/forms"; // Retourne au formulaire avec un message d'erreur
        }

        // Hacher le mot de passe avant de l'enregistrer
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword); // Remplacer le mot de passe par le mot de passe haché
        // Récupérer le rôle par ID
        Optional<RoleUserDTO> optionalRole = roleUserService.findOne(user.getRoleName());

        if (optionalRole.isPresent()) {
            Set<RoleUserDTO> roles = new HashSet<>();
            roles.add(optionalRole.get());
            user.setRoleUser(roles);

            // Enregistrer l'utilisateur
            userService.save(user);
            return "redirect:/users";
        } else {
            model.addAttribute("error", "Le rôle sélectionné n'existe pas.");
            model.addAttribute("roles", roleUserService.getAll());
            model.addAttribute("useradd", user);
            return "user/forms";
        }
    }


//    @PostMapping("/save")
//    public String saveUser(SetUserDTO user) {
//        user.setCreatedDate(Instant.now());
//        Set<RoleUserDTO> roles = new HashSet<>();
//        roles.add(roleUserService.findOne(user.getId_user()).orElse(null));
//        user.setRoleUser(roles);
//        user.setActive(true);
//        userService.save(user);
//        return "redirect:/users";
//
//    }



    @GetMapping("/update/{id}")
    public String showUpdateUserForm(HttpServletRequest request, Model model, @PathVariable Long id){
        String currentUrl = request.getRequestURI();
        Optional<UserDTO> user = userService.findOne(id);
        model.addAttribute("currentUrl", currentUrl);
        if(user.isPresent()){
            model.addAttribute("user", user.get());
            return "user/forms";
        }else {
            return "redirect:/users";
        }
    }

    @PostMapping("/status/{id}")
    public String toggleUserStatus(@PathVariable Long id) {
        Optional<UserDTO> userDTO = userService.findOne(id);
        UserDTO userDTO1 = userDTO.get();
        userDTO1.setActive(!userDTO1.getActive());
        userService.save(userDTO1);
        return "redirect:/users";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }


    @GetMapping("/search")
    public String searchTeachers(@RequestParam LocalDate date  , @RequestParam String role, Model model)
    {
        List<UserDTO> users = userService.findByCreatedDateLessThanAndRoleUserNameRole(Instant.from(date.atStartOfDay(ZoneOffset.systemDefault())), role);
        model.addAttribute("users", users);
        model.addAttribute("date", date);
        model.addAttribute("role", role);
        model.addAttribute("roles", roleUserService.getAll());

        return "user/list";
    }
}
