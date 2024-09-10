package ci.digitalacademy.monetabv03.monetabv03.service.mapper;


import ci.digitalacademy.monetabv03.monetabv03.models.School;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.SchoolDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SchoolMapper extends EntityMapper<SchoolDTO, School> {
}
