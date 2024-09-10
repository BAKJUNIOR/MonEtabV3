package ci.digitalacademy.monetabv03.monetabv03.service.impl;



import ci.digitalacademy.monetabv03.monetabv03.service.*;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AppSettingDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.RoleUserDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.SchoolDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppServiceImp implements AppService {
    private final AppSettingService appSettingService;
    private final SchoolService schoolService;
    private final RoleUserService roleUserService;
    private final UserService userService;


    @Override
    public AppSettingDTO initApp(AppSettingDTO appSettingDTO) {
        return appSettingService.initApp(appSettingDTO);
    }

    @Override
    public SchoolDTO initSchool(SchoolDTO schoolDTO, AppSettingDTO appSettingDTO) {
        schoolDTO.setAppSetting(appSettingDTO);
        return schoolService.initSchool(schoolDTO);
    }

    @Override
    public List<RoleUserDTO> initRoleUser(List<RoleUserDTO> roleUserDTOs) {
        return roleUserService.initRoles(roleUserDTOs);
    }

    @Override
    public void initUser(List<RoleUserDTO> roleUserDTOs, SchoolDTO schoolDTO, List<UserDTO> userDTOs) {
        userDTOs.forEach(userDTO -> {
            userDTO.setCreatedDate(Date.from(Instant.now()).toInstant());
            userDTO.setSchool(schoolDTO);
        });
        userService.initUser(userDTOs);

    }

}
