package ci.digitalacademy.monetabv03.monetabv03.web.resources;

import ci.digitalacademy.monetabv03.monetabv03.service.AddressService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AddressDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressResourceTestIntegration {

    @Autowired
    private TestRestTemplate restTemplate; // Injecte un client Rest pour simuler les requêtes HTTP

    @Autowired
    private AddressService addressService; // Injecte le service de gestion des adresses

    @Test
    void testCreateUser() {
        AddressDTO address = new AddressDTO(1L, "ABIDJAN", "COCODY", "CIV");
        
        ResponseEntity<AddressDTO> response = restTemplate.postForEntity("/api/address", address, AddressDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertNotNull(response.getBody());

        assertNotNull(response.getBody().getId_adress());

        assertEquals("ABIDJAN", response.getBody().getCountry());

        AddressDTO savedUser = addressService.findOne(response.getBody().getId_adress()).orElse(null);

        assertNotNull(savedUser);

        assertEquals("ABIDJAN", savedUser.getCountry());
    }
}
