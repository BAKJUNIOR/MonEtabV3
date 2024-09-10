package ci.digitalacademy.monetabv03.monetabv03.service.mapper;


import ci.digitalacademy.monetabv03.monetabv03.models.RoleUser;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.RoleUserDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RoleUserMapper extends EntityMapper<RoleUserDTO, RoleUser> {
}
