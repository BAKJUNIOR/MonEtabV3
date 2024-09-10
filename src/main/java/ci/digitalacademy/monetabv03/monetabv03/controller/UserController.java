package ci.digitalacademy.monetabv03.monetabv03.controller;

import ci.digitalacademy.monetabv03.monetabv03.service.RoleUserService;
import ci.digitalacademy.monetabv03.monetabv03.service.UserService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleUserService roleUserService;

    @GetMapping("/add")
    public String showAddUserPage(Model model){
        model.addAttribute("user", new UserDTO());
        return "user/forms";
    }

    @PostMapping("/save")
    public String saveUser(UserDTO userDTO){

        if (userDTO.getCreatedDate() == null) {
            userDTO.setCreatedDate(Instant.now());
        }
        userService.save(userDTO);
        return "redirect:/users";
    }
    @GetMapping
    public String showUserPage(Model model){
        List<UserDTO> userDTOS = userService.getAll();
        model.addAttribute("users", userDTOS);
        return "user/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateTeacherForms(@PathVariable Long id, Model model){

        Optional<UserDTO> userDTO = userService.findOne(id);
        if (userDTO.isPresent()){
            model.addAttribute("user", userDTO.get());
            return "user/forms";
        }else {
            return "redirect:/users";
        }
    }


    @GetMapping("/toggleStatus/{id}")
    public String toggleUserStatus(@PathVariable Long id) {
        Optional<UserDTO> userDTO = userService.findOne(id);
        if (userDTO.isPresent()) {
            UserDTO user = userDTO.get();
//            user.setActive(!user.isActive());
            userService.save(user);
        }
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
        model.addAttribute("roles", roleUserService.findAll());

        return "user/list";
    }
}
