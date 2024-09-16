package ci.digitalacademy.monetabv03.monetabv03.web.resources;

import ci.digitalacademy.monetabv03.monetabv03.repositories.StudentRepository;
import ci.digitalacademy.monetabv03.monetabv03.service.StudentService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AbsenceDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.RegistrationStudentDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.ResponseRegisterStudentDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;
import ci.digitalacademy.monetabv03.monetabv03.utils.SlugifyUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/students")
public class StudentResource {
    private  final StudentService studentService;
    @PostMapping
    public ResponseEntity<?> saveStudent(@Valid @RequestBody StudentDTO student) {
        log.info("REST Request to save Student : {}", student);

        // Validation de l'étudiant
        List<String> errors = validateStudent(student);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            // Sauvegarde de l'étudiant
            StudentDTO savedStudent = studentService.save(student);

            // Préparer la réponse avec le message de succès et les détails de l'étudiant enregistré
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "message", "Student a été enregistré avec succès.",
                    "student", savedStudent
            ));
        } catch (DataIntegrityViolationException e) {
            // Vérifiez si l'exception est liée à un duplicata
            if (e.getMessage().contains("UKcdpum2apv0d0otcn04htkdwsn")) {
                return ResponseEntity.badRequest().body("Le matricule doit être unique");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody StudentDTO student, @PathVariable Long id) {
        log.info("REST Request to update Student : {} {}", student, id);

        // Vérifiez si l'étudiant existe en utilisant findById
        Optional<StudentDTO> studentOptional = studentService.findById(id);

        if (!studentOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student avec l'id " + id + " n'existe pas.");
        }

        // Mise à jour de l'étudiant
        StudentDTO updatedStudent = studentService.update(student, id);

        // Préparez la réponse avec le message de succès et les détails de l'étudiant mis à jour
        return ResponseEntity.ok(Map.of(
                "message", "Student avec l'id " + id + " a été mis à jour avec succès.",
                "student", updatedStudent
        ));
    }

    @PatchMapping("/{id}")
    public StudentDTO partialUpdate(@RequestBody StudentDTO studentDTO, @PathVariable Long id){
        log.debug("REST request to partial update {} {}", studentDTO, id);
        return studentService.partialUpdate(studentDTO, id);
    }
    @GetMapping
    public List<StudentDTO> getAllStudent(){
        log.info("REST Request to get all Students");
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        log.info("REST Request to get Student : {}", id);
        Optional<StudentDTO> studentDTO = studentService.findById(id);
        if (studentDTO.isPresent()){
            return new ResponseEntity<>(studentDTO.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("student not fond",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        log.info("REST Request to delete Student : {}", id);

        // Vérifiez si l'étudiant existe en utilisant findById
        Optional<StudentDTO> studentOptional = studentService.findById(id);

        if (!studentOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student avec l'id " + id + " n'existe pas.");
        }

        studentService.deleteById(id);
        return ResponseEntity.ok("Student avec l'id " + id + " a été supprimé avec succès.");
    }

    @PostMapping("/register-student")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseRegisterStudentDTO registerStudent(@RequestBody RegistrationStudentDTO registrationStudentDTO) {
        log.debug("REST Request to register student : {}", registrationStudentDTO);
        return studentService.registerStudent(registrationStudentDTO);
}




    private List<String> validateStudent(StudentDTO student) {
        List<String> errors = new ArrayList<>();

        // Validation du matricule
        if (student.getMatricule() == null || student.getMatricule().isEmpty()) {
            errors.add("Le matricule est obligatoire");
        } else if (student.getMatricule().length() > 20) {
            errors.add("Le matricule ne doit pas dépasser 20 caractères");
        }

        // Validation du prénom
        if (student.getFirstName() == null || student.getFirstName().isEmpty()) {
            errors.add("Le prénom est obligatoire");
        } else if (student.getFirstName().length() > 50) {
            errors.add("Le prénom ne doit pas dépasser 50 caractères");
        }

        // Validation du nom
        if (student.getLastName() == null || student.getLastName().isEmpty()) {
            errors.add("Le nom est obligatoire");
        } else if (student.getLastName().length() > 50) {
            errors.add("Le nom ne doit pas dépasser 50 caractères");
        }

        // Validation du numéro de téléphone du parent
        if (student.getPhoneNumberParent() == null || student.getPhoneNumberParent().isEmpty()) {
            errors.add("Le numéro de téléphone du parent est obligatoire");
        } else if (!student.getPhoneNumberParent().matches("\\d{10}")) {
            errors.add("Le numéro de téléphone du parent doit contenir 10 chiffres");
        }

        // Validation de la date de naissance
        if (student.getDateOfBirth() == null) {
            errors.add("La date de naissance est obligatoire");
        } else {
            // Vérifier si la date de naissance est dans le futur
            if (student.getDateOfBirth().after(new Date(System.currentTimeMillis()))) {
                errors.add("La date de naissance ne peut pas être dans le futur");
            }
        }


        return errors;
    }





}
