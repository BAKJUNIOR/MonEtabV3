package ci.digitalacademy.monetabv03.monetabv03.web.resources;

import ci.digitalacademy.monetabv03.monetabv03.service.RoleUserService;
import ci.digitalacademy.monetabv03.monetabv03.service.UserService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.RoleUserDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/roleUsers")
public class RoleUserResource {
    private  final RoleUserService roleUserService;
    @PostMapping
    public ResponseEntity<?> saveRoleUser(@Valid @RequestBody RoleUserDTO user) {
        log.info("REST Request to save roleUsers : {}", user);

        // Validation de l'étudiant
        List<String> errors =  validateRoleUser(user);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        RoleUserDTO savedRoleUser = roleUserService.save(user);

        // Préparer la réponse avec le message de succès et les détails de l'étudiant enregistré
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "roleUser a été enregistré avec succès.",
                "roleUser", savedRoleUser
        ));



    }


//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateUser(@RequestBody UserDTO user, @PathVariable Long id) {
//        log.info("REST Request to update user : {} {}", user, id);
//
//        // Vérifiez si user existe en utilisant findOne
//        Optional<UserDTO> userOptional = userService.findOne(id);
//
//        if (!userOptional.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User avec l'id " + id + " n'existe pas.");
//        }
//
//        // Mise à jour de user
//        UserDTO updatedUser = userService.update(user, id);
//
//        // Préparez la réponse avec le message de succès et les détails de l'étudiant mis à jour
//        return ResponseEntity.ok(Map.of(
//                "message", "User avec l'id " + id + " a été mis à jour avec succès.",
//                "user", updatedUser
//        ));
//    }

//    @PatchMapping("/{id}")
//    public UserDTO partialUpdate(@RequestBody UserDTO userDTO, @PathVariable Long id){
//        log.debug("REST request to partial update {} {}", userDTO, id);
//        return userService.partialUpdate(userDTO, id);
//    }
    @GetMapping
    public List<RoleUserDTO> getAllRoleUser(){
        log.info("REST Request to get all User");
        return roleUserService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleUserById(@PathVariable Long id){
        log.info("REST Request to get RoleUser : {}", id);
        Optional<RoleUserDTO> roleUser = roleUserService.findOne(id);
        if (roleUser.isPresent()){
            return new ResponseEntity<>(roleUser.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("user not fond",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoleUser(@PathVariable Long id) {
        log.info("REST Request to delete RoleUser : {}", id);

        Optional<RoleUserDTO> RoleUserOptional = roleUserService.findOne(id);

        if (!RoleUserOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("RoleUser avec l'id " + id + " n'existe pas.");
        }

        roleUserService.delete(id);
        return ResponseEntity.ok("RoleUser avec l'id " + id + " a été supprimé avec succès.");
    }



    private List<String> validateRoleUser(RoleUserDTO roleUserDTO) {
        List<String> errors = new ArrayList<>();

//        // Validation du pseudo
//        if (user.getPseudo() == null || user.getPseudo().isEmpty()) {
//            errors.add("Le pseudo est obligatoire");
//        } else if (user.getPseudo().length() > 50) {
//            errors.add("Le pseudo ne doit pas dépasser 50 caractères");
//        }
//
//        // Validation du mot de passe
//        if (user.getPassword() == null || user.getPassword().isEmpty()) {
//            errors.add("Le mot de passe est obligatoire");
//        } else if (user.getPassword().length() < 6) {
//            errors.add("Le mot de passe doit contenir au moins 6 caractères");
//        }
//
//        // Validation de l'activation
//        if (user.getActive() == null) {
//            errors.add("L'état d'activation est obligatoire");
//        }
//
//        // Validation du slug
//        if (user.getSlug() != null && user.getSlug().length() > 100) {
//            errors.add("Le slug ne doit pas dépasser 100 caractères");
//        }
//
////        // Validation des rôles
////        if (user.getRoleUser() == null || user.getRoleUser().isEmpty()) {
////            errors.add("Au moins un rôle est obligatoire");
////        }
//
////        // Validation de l'école
////        if (user.getSchool() == null) {
////            errors.add("L'école est obligatoire");
////        }

        return errors;
    }


}
