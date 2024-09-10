package ci.digitalacademy.monetabv03.monetabv03.service;



import ci.digitalacademy.monetabv03.monetabv03.service.dto.AppSettingDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.RoleUserDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.SchoolDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.UserDTO;

import java.util.List;

public interface AppService {

    AppSettingDTO initApp(AppSettingDTO appSettingDTO);

    SchoolDTO initSchool(SchoolDTO schoolDTO, AppSettingDTO appSettingDTO);

    List<RoleUserDTO> initRoleUser(List<RoleUserDTO> roleUserDTOs);

    void initUser(List<RoleUserDTO> roleUserDTOs, SchoolDTO schoolDTO, List<UserDTO> userDTOs);
}
