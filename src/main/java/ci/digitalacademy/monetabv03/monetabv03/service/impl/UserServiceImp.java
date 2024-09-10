package ci.digitalacademy.monetabv03.monetabv03.service.impl;


import ci.digitalacademy.monetabv03.monetabv03.models.User;
import ci.digitalacademy.monetabv03.monetabv03.repositories.RoleUserRepository;
import ci.digitalacademy.monetabv03.monetabv03.repositories.UserRepository;
import ci.digitalacademy.monetabv03.monetabv03.service.UserService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.RoleUserDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.UserDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.mapper.RoleUserMapper;
import ci.digitalacademy.monetabv03.monetabv03.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final RoleUserRepository roleUserRepository;
    private final RoleUserMapper roleUserMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDTO save(UserDTO userDTO) {
        // Convertir le DTO en entité
        User user = userMapper.DtoToEntity(userDTO);
        // Enregistrer l'entité dans la base de données
        User savedUser = userRepository.save(user);
        // Convertir l'entité enregistrée de nouveau en DTO pour retourner
        return userMapper.ToDto(savedUser);
    }
    @Override
    public UserDTO update(UserDTO userDTO) {
        return findOne(userDTO.getId_user()).map(existingUser ->{
            existingUser.setPseudo(userDTO.getPseudo());
            existingUser.setPassword(userDTO.getPassword());
            return save(userDTO);
        }).orElseThrow(() ->new IllegalArgumentException());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(user -> {
            return userMapper.ToDto(user);
        }).toList();
    }


    @Override
    public Optional<UserDTO> findOne(Long id) {
        return userRepository.findById(id).map(user -> {
            return userMapper.ToDto(user);
        });
    }

    @Override
    public List<UserDTO> initUser(List<UserDTO> UserDTO) {
        List<UserDTO> userDTOS = getAll();
        if (userDTOS.isEmpty()) {
            UserDTO.forEach(userDTO -> {
                save(userDTO);
            });
        }
        return getAll();
    }


    @Override
    public List<UserDTO> findByCreatedDateLessThanAndRoleUserNameRole(Instant createdDate, String role) {
        List<User> users = userRepository.findByCreatedDateLessThanAndRoleUserNameRole(createdDate, role);
        return users.stream().map(user -> userMapper.ToDto(user)).toList();
    }
}
