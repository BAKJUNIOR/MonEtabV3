package ci.digitalacademy.monetabv03.monetabv03.service.impl;


import ci.digitalacademy.monetabv03.monetabv03.models.Student;
import ci.digitalacademy.monetabv03.monetabv03.models.User;
import ci.digitalacademy.monetabv03.monetabv03.repositories.UserRepository;
import ci.digitalacademy.monetabv03.monetabv03.service.UserService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.UserDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.mapper.UserMapper;
import ci.digitalacademy.monetabv03.monetabv03.service.mapping.StudentMapping;
import ci.digitalacademy.monetabv03.monetabv03.service.mapping.UserMapping;
import ci.digitalacademy.monetabv03.monetabv03.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class  UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.DtoToEntity(userDTO);
        user.setSlug(SlugifyUtils.generate(user.getPseudo()));
        user.setCreatedDate(Instant.now());

        return userMapper.ToDto(userRepository.save(user));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return findOne((userDTO.getId_user())).map(existingStudent ->{
            existingStudent.setRoleUser(userDTO.getRoleUser());
            existingStudent.setPseudo(userDTO.getPseudo());
            existingStudent.setPassword(userDTO.getPassword());
            return save(existingStudent);
        }).orElseThrow(()-> new RuntimeException("user not found"));
    }

    @Override
    public UserDTO partialUpdate(UserDTO userDTO, Long id) {
        return userRepository.findById(id).map(user -> {
            UserMapping.partialUpdate(user,userDTO);
            return user;
        }).map(userRepository::save).map(userMapper::ToDto).orElse(null);
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id) {
        return findOne(id).map(user -> {
            user.setPseudo(userDTO.getPseudo());
            user.setRoleUser(userDTO.getRoleUser());
            user.setPassword(userDTO.getPassword());
            return save(user);
        }).orElseThrow(()-> new IllegalArgumentException("User not found"));
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
        return userRepository.findById(id).map(user -> userMapper.ToDto(user));
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
        List<User> users = userRepository.findByCreatedDateLessThanAndRoleUserNameRole(Date.from(createdDate).toInstant(), role);
        return users.stream().map(user -> userMapper.ToDto(user)).toList();
    }
}
