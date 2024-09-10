package ci.digitalacademy.monetabv03.monetabv03.service;


import ci.digitalacademy.monetabv03.monetabv03.service.dto.AddressDTO;

import java.util.List;
import java.util.Optional;

public interface AdressService {

    AddressDTO save(AddressDTO adressDTO);
    AddressDTO update(AddressDTO adressDTO);
    Optional<AddressDTO> findById(Long id);
    List<AddressDTO> findAll();
    void deleteById(Long id);
}
