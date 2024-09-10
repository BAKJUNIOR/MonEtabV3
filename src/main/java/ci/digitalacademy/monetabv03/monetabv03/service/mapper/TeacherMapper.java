package ci.digitalacademy.monetabv03.monetabv03.service.mapper;


import ci.digitalacademy.monetabv03.monetabv03.models.Teacher;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.TeacherDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TeacherMapper extends EntityMapper<TeacherDTO, Teacher> {
}
