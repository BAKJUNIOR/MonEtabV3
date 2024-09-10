package ci.digitalacademy.monetabv03.monetabv03.controller;


import ci.digitalacademy.monetabv03.monetabv03.models.Gender;
import ci.digitalacademy.monetabv03.monetabv03.service.AppService;
import ci.digitalacademy.monetabv03.monetabv03.service.AppSettingService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AppSettingDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/appSettings")
public class AppSettingController {
    private final AppSettingService appSettingService;

    private final AppService appService;
    @GetMapping
    public String showSettingPage(Model model){
        model.addAttribute("appSetting", new AppSettingDTO());
        return "appSetting/form";
    }


    @GetMapping("/update")
    public String showUpdateProfessorForm( Model model){
        AppSettingDTO appSettingDTO = appSettingService.findAll().stream().findFirst().orElse(null);
        model.addAttribute("appSetting", appSettingDTO);
        return "appSetting/form";

    }

    @PostMapping("/postAppSettings")
    public String saveSetting( AppSettingDTO appSettingDTO){
        appSettingService.save(appSettingDTO);
        return "redirect:/";
    }




}