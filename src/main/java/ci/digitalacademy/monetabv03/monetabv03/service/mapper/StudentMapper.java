package ci.digitalacademy.monetabv03.monetabv03.service.mapper;


import ci.digitalacademy.monetabv03.monetabv03.models.Student;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {
}
