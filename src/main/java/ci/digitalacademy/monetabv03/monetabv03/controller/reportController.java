package ci.digitalacademy.monetabv03.monetabv03.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class reportController {
    @GetMapping("report")
    public String indexRapport(){
        return "/report/index";
    }
}
