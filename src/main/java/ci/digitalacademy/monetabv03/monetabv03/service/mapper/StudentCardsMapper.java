package ci.digitalacademy.monetabv03.monetabv03.service.mapper;


import ci.digitalacademy.monetabv03.monetabv03.models.StudentCards;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentCardsDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface StudentCardsMapper extends EntityMapper<StudentCardsDTO, StudentCards> {
}
