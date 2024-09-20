package ci.digitalacademy.monetabv03.monetabv03.service.impl;

import ci.digitalacademy.monetabv03.monetabv03.models.RoleUser;
import ci.digitalacademy.monetabv03.monetabv03.repositories.RoleUserRepository;
import ci.digitalacademy.monetabv03.monetabv03.service.RoleUserService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.RoleUserDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.mapper.RoleUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleUserServiceImp implements RoleUserService {
    private final RoleUserRepository roleUserRepository;
    private final RoleUserMapper roleUserMapper;
    @Override
    public RoleUserDTO save(RoleUserDTO roleUserDTO) {
        RoleUser roleUser = roleUserMapper.DtoToEntity(roleUserDTO);
        return roleUserMapper.ToDto(roleUserRepository.save(roleUser));
    }


    @Override
    public RoleUserDTO update(RoleUserDTO roleUserDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {
        roleUserRepository.deleteById(id);
    }

    @Override
    public List<RoleUserDTO> getAll() {
        return roleUserRepository.findAll().stream().map(role -> {
            return roleUserMapper.ToDto(role);
        }).toList();
    }

    @Override
    public List<RoleUserDTO> findAll() {
        return roleUserRepository.findAll().stream().map(roleUser -> {
            return roleUserMapper.ToDto(roleUser);
        }).toList();
    }
    @Override
    public Optional<RoleUserDTO> findOne(Long id) {
        return roleUserRepository.findById(id)
                .map(roleUserMapper::ToDto);
    }

    @Override
    public List<RoleUserDTO> verifyExistingRoles() {
        return List.of();
    }

    @Override
    public List<RoleUserDTO> initRoles(List<RoleUserDTO> rolesUserDTO) {
        log.debug("Request to init roles {}", rolesUserDTO);
        List<RoleUserDTO> roleUserDTOS = getAll();
        if (roleUserDTOS.isEmpty()) {
            rolesUserDTO.forEach(roleUserDTO -> {
                save(roleUserDTO);
            });
        }
        return getAll();
    }

    @Override
    public List<RoleUserDTO> findByRole(String roleUser) {
        return roleUserRepository.findBynameRole(roleUser).stream().map(role -> {
            return roleUserMapper.ToDto(role);
        }).toList();
}
}
