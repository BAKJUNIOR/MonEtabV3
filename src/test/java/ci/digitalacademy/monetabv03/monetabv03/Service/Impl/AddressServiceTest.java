package ci.digitalacademy.monetabv03.monetabv03.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import ci.digitalacademy.monetabv03.monetabv03.models.Address;
import ci.digitalacademy.monetabv03.monetabv03.repositories.AddressRepository;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AddressDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.impl.AddressServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class AddressServiceTest {

    // Simule un bean (AddressRepository) qui sera injecté comme mock
    @MockBean
    private AddressRepository addressRepository;

    // Injecte automatiquement le service AddressServiceImpl que nous voulons tester
    @Autowired
    private AddressServiceImp addressService;

    @Test
    void whenFindById_thenReturnAddressNotEmpty() {
        // Mock le comportement du repository pour qu'il retourne une adresse spécifique lorsque findById(1L) est appelé
        when(addressRepository.findById(1L))
            .thenReturn(Optional.of(new Address(1L, "ABIDJAN", "COCODY", "CIV")));

        // Appelle la méthode du service qui doit être testée
        Optional<AddressDTO> address = addressService.findOne(1L);

        // Vérifie que l'objet Optional contient une adresse (n'est pas vide)
        assertTrue(address.isPresent(), "L'adresse n'est pas vide");
    }
}
