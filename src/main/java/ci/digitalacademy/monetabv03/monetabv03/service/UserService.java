package ci.digitalacademy.monetabv03.monetabv03.service;



import ci.digitalacademy.monetabv03.monetabv03.service.dto.RoleUserDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.UserDTO;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO save(UserDTO UserDTO);
    UserDTO update(UserDTO UserDTO);
    void delete(Long id);
    List<UserDTO> getAll();

    Optional<UserDTO> findOne(Long id);
    List<UserDTO> initUser(List<UserDTO> UserDTO);
    List<UserDTO> findByCreatedDateLessThanAndRoleUserNameRole(Instant createdDate, String role);

}
