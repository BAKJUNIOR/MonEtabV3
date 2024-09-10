package ci.digitalacademy.monetabv03.monetabv03.web.ressources;

import ci.digitalacademy.monetabv03.monetabv03.service.StudentService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/students")
@RestController
public class StudentRessource {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO) {
        log.debug("REST  request to save {}", studentDTO);
        return new ResponseEntity<>(studentService.save(studentDTO) , HttpStatus.CREATED) ;
        // personnaliser la reponse grace a ResponseEntity
    }

@PutMapping("/{id}")
    public StudentDTO update( @RequestBody  StudentDTO studentDTO, @PathVariable Long id) {
        log.debug(" REST   request to update {}", studentDTO);
        return studentService.update(studentDTO, id);
    }

@GetMapping("/{id}")
    public ResponseEntity<?> findByIdStudent( @PathVariable Long id) {
        log.debug(" REST  request to get one {}");
        Optional<StudentDTO> studentDTO = studentService.findById(id);
        if (studentDTO.isPresent()){
            return new ResponseEntity<>(studentDTO.get(), HttpStatus.OK);
        }else
          return   new ResponseEntity<>(studentDTO , HttpStatus.NOT_FOUND);

    }


    @GetMapping
    public List<StudentDTO> findAllStudent() {
        log.debug("REST a  request to all student {}");
        return studentService.findAll();
    }


    @DeleteMapping("/{id}")
    public void deleteByIdStudent(@PathVariable Long id) {
        log.debug("request to delete {}");
        studentService.deleteById(id);

    }

}
