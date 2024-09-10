package ci.digitalacademy.monetabv03.monetabv03.controller;


import ci.digitalacademy.monetabv03.monetabv03.service.AppSettingService;
import ci.digitalacademy.monetabv03.monetabv03.service.SchoolService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AppSettingDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class IndexController {
    private final AppSettingService appSettingService;
    private final SchoolService schoolService;


    @GetMapping
    public  String verifyConfiguration() {
        List<AppSettingDTO> appSettingDTOS = appSettingService.findAll();
        List<SchoolDTO> schoolDTOS = schoolService.getAll();
        if (appSettingDTOS.isEmpty()) {
            return "redirect:/appSettings";
        } else if (schoolDTOS.isEmpty()) {
            return "redirect:/schools";
        }else {
            return "redirect:/login";
        }
    }

}

