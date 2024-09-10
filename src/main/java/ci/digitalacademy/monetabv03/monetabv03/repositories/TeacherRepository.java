package ci.digitalacademy.monetabv03.monetabv03.repositories;


import ci.digitalacademy.monetabv03.monetabv03.models.Gender;
import ci.digitalacademy.monetabv03.monetabv03.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByLastNameOrSpecialtyAndGender(String lastName, String specialty, Gender gender);
}
