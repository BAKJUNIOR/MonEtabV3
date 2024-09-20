package ci.digitalacademy.monetabv03.monetabv03.web.resources;


import ci.digitalacademy.monetabv03.monetabv03.service.AddressService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AddressDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
@Slf4j
@RequiredArgsConstructor
public class AdressResource {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDTO> save(@RequestBody AddressDTO addressDTO) {
        log.debug("REST Request to save {}", addressDTO);
        AddressDTO savedAddress = addressService.save(addressDTO);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAll() {
        log.debug("REST Request to getAll");
        List<AddressDTO> addresses = addressService.getAll();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        log.debug("REST request to get one {}", id);
        Optional<AddressDTO> addressDTO = addressService.findOne(id);
        if (addressDTO.isPresent()) {
            return new ResponseEntity<>(addressDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/slug/{slug}")
//    public ResponseEntity<?> getOneBySlug(@PathVariable String slug) {
//        log.debug("REST request to get one by slug {}", slug);
//        Optional<AddressDTO> addressDTO = addressService.findBySlug(slug);
//        if (addressDTO.isPresent()) {
//            return new ResponseEntity<>(addressDTO.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);
//        }
//    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> update(@RequestBody AddressDTO addressDTO, @PathVariable Long id) {
        log.debug("REST request to update address {} {}", addressDTO, id);
        AddressDTO updatedAddress = addressService.update(addressDTO);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<AddressDTO> partialUpdate(@RequestBody AddressDTO addressDTO, @PathVariable Long id) {
//        log.debug("REST request to partial update {} {}", addressDTO, id);
//        AddressDTO partiallyUpdatedAddress = addressService.partialUpdate(addressDTO, id);
//        return new ResponseEntity<>(partiallyUpdatedAddress, HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST Request to delete {}", id);
        addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}