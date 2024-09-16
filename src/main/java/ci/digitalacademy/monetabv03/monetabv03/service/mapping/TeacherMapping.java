package ci.digitalacademy.monetabv03.monetabv03.service.mapping;

import ci.digitalacademy.monetabv03.monetabv03.models.Student;
import ci.digitalacademy.monetabv03.monetabv03.models.Teacher;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.TeacherDTO;

public final class TeacherMapping {


    private TeacherMapping() {
    }

    public static void partialUpdate(Teacher teacher    , TeacherDTO teacherDTO) {
        if (teacherDTO.getSpecialty() != null) {
            teacher.setSpecialty(teacherDTO.getSpecialty());
        }
        if (teacherDTO.getAvailable() != null) {
            teacher.setAvailable(teacherDTO.getAvailable());
        }
    }
}