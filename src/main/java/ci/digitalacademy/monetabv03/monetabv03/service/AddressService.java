package ci.digitalacademy.monetabv03.monetabv03.service;



import ci.digitalacademy.monetabv03.monetabv03.service.dto.AddressDTO;

import java.util.List;
import java.util.Optional;


public interface AddressService {
    AddressDTO save(AddressDTO addressDTO);
    AddressDTO update(AddressDTO addressDTO);
    void delete(Long id);
    List<AddressDTO> getAll();
    Optional<AddressDTO> findOne(Long id);
}
