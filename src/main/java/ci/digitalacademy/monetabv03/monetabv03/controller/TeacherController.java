package ci.digitalacademy.monetabv03.monetabv03.controller;


import ci.digitalacademy.monetabv03.monetabv03.models.Gender;
import ci.digitalacademy.monetabv03.monetabv03.service.StudentService;
import ci.digitalacademy.monetabv03.monetabv03.service.TeacherService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AddressDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.TeacherDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;


    @GetMapping
    public String showStudentPage(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "teacher/list";
    }

    @GetMapping("/add")
    public String showAddStudentPage(Model model) {

        TeacherDTO teacherDTO = new TeacherDTO();
//        teacherDTO.setAddress(new AddressDTO());

        model.addAttribute("teacher", teacherDTO);
        model.addAttribute("gender", Gender.values());
        return "teacher/forms";
    }

    @GetMapping("/update/{id}")
    public String showUpdateStudentPage(
            @PathVariable Long id ,
            Model model
    ) {

        Optional<TeacherDTO> teacherDTO = teacherService.findById(id);
        if( teacherDTO.isPresent()){
            model.addAttribute("teacher", teacherDTO.get());
            model.addAttribute("gender", Gender.values());
            return "teacher/forms";
        }else{
            return "redirect:/teachers";
        }


    }

    @PostMapping("/save")
    public String saveStudent(TeacherDTO teacherDTO) {
        teacherService.save(teacherDTO);
        return "redirect:/teachers";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        if(teacherService.findById(id).isPresent()){
            teacherService.deleteById(id);
        }
        return "redirect:/teachers";
    }

    @GetMapping("/search")
    public String searchTeachers(@RequestParam String query  ,@RequestParam String gender, Model model)
    {
        List<TeacherDTO> teachers = teacherService.findByLastNameOrSpecialtyAndGender(query  , gender);
        model.addAttribute("teachers", teachers);
        model.addAttribute("query", query);
        model.addAttribute("gender", gender);

        return "teacher/list";
    }
}
