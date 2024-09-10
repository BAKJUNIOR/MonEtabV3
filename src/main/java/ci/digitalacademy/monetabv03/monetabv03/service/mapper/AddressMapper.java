package ci.digitalacademy.monetabv03.monetabv03.service.mapper;


import ci.digitalacademy.monetabv03.monetabv03.models.Address;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AddressDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {
}
