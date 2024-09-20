package ci.digitalacademy.monetabv03.monetabv03.service.impl;


import ci.digitalacademy.monetabv03.monetabv03.repositories.AddressRepository;
import ci.digitalacademy.monetabv03.monetabv03.service.AddressService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AddressDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImp implements AddressService {
    private  final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        return addressMapper.ToDto(addressRepository.save(addressMapper.DtoToEntity(addressDTO)));
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<AddressDTO> getAll() {
        return addressRepository.findAll().stream().map(address -> {
            return addressMapper.ToDto(address);
        }).toList();
    }

    @Override
    public Optional<AddressDTO> findOne(Long id) {
        // Cherche une adresse par son identifiant (id) dans le dépôt de données (repository)
        // La méthode findById renvoie un Optional<Address> (peut être vide si l'adresse n'existe pas)
        return addressRepository.findById(id)
                .map(absence -> {
                    // Si une adresse est trouvée, elle est convertie en AddressDTO via le mapper
                    return addressMapper.ToDto(absence);
                });
        // Le résultat final est un Optional<AddressDTO>, contenant l'adresse sous forme de DTO si elle existe,
        // ou un Optional vide si aucune adresse n'a été trouvée.
    }

}
