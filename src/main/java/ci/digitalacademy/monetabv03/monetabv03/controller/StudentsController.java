package ci.digitalacademy.monetabv03.monetabv03.controller;


import ci.digitalacademy.monetabv03.monetabv03.models.Gender;
import ci.digitalacademy.monetabv03.monetabv03.service.StudentService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AddressDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {

    private final StudentService studentService;


    @GetMapping
    public String showStudentPage(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student/list";
    }

    @GetMapping("/add")
    public String showAddStudentPage(Model model) {

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAddress(new AddressDTO());

        model.addAttribute("student", studentDTO);
        model.addAttribute("action", "add");
        model.addAttribute("gender", Gender.values());
        return "student/forms";
    }

    @GetMapping("/update/{id}")
    public String showUpdateStudentPage(
            @PathVariable Long id ,
            Model model
    ) {

        Optional<StudentDTO> studentDTO = studentService.findById(id);
        if( studentDTO.isPresent()){
            model.addAttribute("student", studentDTO.get());
            model.addAttribute("action", "update");
            model.addAttribute("genre", Gender.values());
            return "student/forms";
        }else{
            return "redirect:/students";
        }


    }

    @PostMapping("/save")
    public String saveStudent(StudentDTO studentDTO) {
        studentService.save(studentDTO);
        return "redirect:/students";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        if(studentService.findById(id).isPresent()){
            studentService.deleteById(id);
        }
        return "redirect:/students";
    }

    @GetMapping("/search")
    public String searchStudents(@RequestParam String query  ,@RequestParam String gender, Model model)
    {
        List<StudentDTO> students = studentService.findByLastNameOrGenderOrMatricule(query , gender);
        model.addAttribute("students", students);
        model.addAttribute("query", query);
        model.addAttribute("gender", gender);
        return "student/list";
    }
}
