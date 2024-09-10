package ci.digitalacademy.monetabv03.monetabv03.service;


import ci.digitalacademy.monetabv03.monetabv03.service.dto.AppSettingDTO;

import java.util.List;
import java.util.Optional;

public interface AppSettingService {

    AppSettingDTO existingParameter();

    AppSettingDTO save(AppSettingDTO AppSettingDTO);
    AppSettingDTO update(AppSettingDTO AppSettingDTO);
    void delete(Long id);
    List<AppSettingDTO> findAll();
    Optional<AppSettingDTO> findOne(Long id);
    AppSettingDTO initApp(AppSettingDTO appSettingDTO);
    List<AppSettingDTO>findAllBySmtpUsernames(String smtpUsername);
}
