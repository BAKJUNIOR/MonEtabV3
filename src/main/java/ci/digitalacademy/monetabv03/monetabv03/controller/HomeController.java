package ci.digitalacademy.monetabv03.monetabv03.controller;

import ci.digitalacademy.monetabv03.monetabv03.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    private final SchoolService schoolService;
    @GetMapping
    public String showHomePage(Model model){
        model.addAttribute("schoolName", schoolService.findAll().stream().findFirst().orElse(null));
        return "layouts/home";
    }





}
