package ci.digitalacademy.monetabv03.monetabv03.controller;

import ci.digitalacademy.monetabv03.monetabv03.service.RoleUserService;
import ci.digitalacademy.monetabv03.monetabv03.service.UserService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.RoleUserDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.SetUserDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String showAddUserPage(HttpServletRequest request, Model model){
        List<RoleUserDTO> all = roleUserService.getAll();
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("user", new SetUserDTO());
        model.addAttribute("roles", all);
//        model.addAttribute("user", new UserDTO());
        return "user/forms";
    }

    @PostMapping("/save")
    public String saveUser(HttpServletRequest request, Model model){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<UserDTO> users = userService.getAll();
        List<RoleUserDTO> all = roleUserService.getAll();
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("users", users);
        model.addAttribute("all", all);
        model.addAttribute("namee", name);
        return "user/list";
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


    @GetMapping("/{id}")
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
        model.addAttribute("roles", roleUserService.findAll());

        return "user/list";
    }
}
