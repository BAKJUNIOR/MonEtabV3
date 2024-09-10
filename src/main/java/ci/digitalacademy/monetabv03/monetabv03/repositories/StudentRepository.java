package ci.digitalacademy.monetabv03.monetabv03.repositories;


import ci.digitalacademy.monetabv03.monetabv03.models.Gender;
import ci.digitalacademy.monetabv03.monetabv03.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByLastNameIgnoreCaseOrMatriculeIgnoreCaseAndGender(String lastName, String matricule , Gender gender);
}
