package ci.digitalacademy.monetabv03.monetabv03.web.resources;

import ci.digitalacademy.monetabv03.monetabv03.service.TeacherService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.TeacherDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/teachers")
public class TeacherResource {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<?> saveTeacher(@RequestBody TeacherDTO teacherDTO) {
        log.debug("REST Request to save Teacher : {}", teacherDTO);

        List<String> errors = validateTeacher(teacherDTO);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        TeacherDTO savedTeacher = teacherService.save(teacherDTO);

        // Préparer la réponse avec le message de succès et les détails de l'étudiant enregistré
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Teacher a été enregistré avec succès.",
                "teacher", savedTeacher
        ));

    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacherDTO, @PathVariable Long id) {
        log.debug("REST Request to update Teacher : {}", teacherDTO);
        return new ResponseEntity<>(teacherService.update(teacherDTO, id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public TeacherDTO partialUpdate(@RequestBody TeacherDTO teacherDTO, @PathVariable Long id){
        log.debug("REST request to partial update {} {}", teacherDTO, id);
        return teacherService.partialUpdate(teacherDTO, id);
    }

    @GetMapping
    public List<TeacherDTO> getAllStudent(){
        log.info("REST Request to get all Students");
        return teacherService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        log.info("REST Request to get Student : {}", id);
        Optional<TeacherDTO> teacherDTO = teacherService.findById(id);
        if (teacherDTO.isPresent()){
            return new ResponseEntity<>(teacherDTO.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("teacher not fond",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        log.info("REST Request to delete teacher : {}", id);

        // Vérifiez si l'étudiant existe en utilisant findById
        Optional<TeacherDTO> teacherOptional = teacherService.findById(id);

        if (!teacherOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher avec l'id " + id + " n'existe pas.");
        }

        teacherService.deleteById(id);
        return ResponseEntity.ok("Teacher avec l'id " + id + " a été supprimé avec succès.");
    }


    private List<String> validateTeacher(TeacherDTO teacherDTO) {
        List<String> errors = new ArrayList<>();


        if (teacherDTO.getSpecialty() == null || teacherDTO.getSpecialty().isEmpty()) {
            errors.add("La spécification est obligatoire");
        } else if (teacherDTO.getSpecialty().length() > 20) {
            errors.add("La spécification ne doit pas dépasser 20 caractères");
        }

        // Validation du prénom
        if (teacherDTO.getFirstName() == null || teacherDTO.getFirstName().isEmpty()) {
            errors.add("prénom est obligatoire");
        } else if (teacherDTO.getFirstName().length() > 50) {
            errors.add("Le prénom ne doit pas dépasser 50 caractères");
        }

        // Validation du nom
        if (teacherDTO.getLastName() == null || teacherDTO.getLastName().isEmpty()) {
            errors.add("Le nom est obligatoire");
        } else if (teacherDTO.getLastName().length() > 50) {
            errors.add("Le nom ne doit pas dépasser 50 caractères");
        }


            // Validation de la date de naissance
            if (teacherDTO.getDateOfBirth() == null) {
                errors.add("La date de naissance est obligatoire");
            } else {
                // Vérifier si la date de naissance est dans le futur
                if (teacherDTO.getDateOfBirth().after(new Date(System.currentTimeMillis()))) {
                    errors.add("La date de naissance ne peut pas être dans le futur");
                }

}

            return errors;
        }
    }

