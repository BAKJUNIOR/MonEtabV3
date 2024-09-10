package ci.digitalacademy.monetabv03.monetabv03.service;



import ci.digitalacademy.monetabv03.monetabv03.service.dto.RoleUserDTO;

import java.util.List;
import java.util.Optional;

public interface RoleUserService {
    RoleUserDTO save(RoleUserDTO roleUserDTO);
    RoleUserDTO update(RoleUserDTO roleUserDTO);
    void delete(Long id);
    List<RoleUserDTO> getAll();
    Optional<RoleUserDTO> findOne(Long id);


    List<RoleUserDTO> findAll();
    List<RoleUserDTO> verifyExistingRoles();
    List<RoleUserDTO> initRoles(List<RoleUserDTO> roleUserDTO);
}
