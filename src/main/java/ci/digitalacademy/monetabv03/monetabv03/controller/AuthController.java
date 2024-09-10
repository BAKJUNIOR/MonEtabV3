package ci.digitalacademy.monetabv03.monetabv03.controller;

import ci.digitalacademy.monetabv03.monetabv03.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
@RequiredArgsConstructor
public class AuthController {
    private final SchoolService schoolService;

    @GetMapping
    public String showLoginPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
        }
        model.addAttribute("schoolName", schoolService.findAll().stream().findFirst().orElse(null));
        return "auth/login";
    }





}
