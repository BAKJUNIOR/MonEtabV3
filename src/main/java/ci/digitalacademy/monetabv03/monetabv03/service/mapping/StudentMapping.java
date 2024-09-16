package ci.digitalacademy.monetabv03.monetabv03.service.mapping;

import ci.digitalacademy.monetabv03.monetabv03.models.Student;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;

public final class StudentMapping {


    private StudentMapping() {
    }

    public static void partialUpdate(Student student    , StudentDTO studentDTO) {
        if (studentDTO.getNumbers() != null) {
            student.setNumbers(studentDTO.getNumbers());
        }
        if (studentDTO.getDateOfBirth() != null) {
            student.setDateOfBirth(studentDTO.getDateOfBirth());
        }
    }
}