package ci.digitalacademy.monetabv03.monetabv03.service.mapper;


import ci.digitalacademy.monetabv03.monetabv03.models.Absence;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AbsenceDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AbsenceMapper extends EntityMapper<AbsenceDTO, Absence> {
}
