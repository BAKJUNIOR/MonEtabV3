package ci.digitalacademy.monetabv03.monetabv03.service.impl;



import ci.digitalacademy.monetabv03.monetabv03.repositories.AppSettingRepository;
import ci.digitalacademy.monetabv03.monetabv03.service.AppSettingService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AppSettingDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.mapper.AppSettingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppSettingServiceImp implements AppSettingService {
    private final AppSettingRepository appSettingRepository;
    private final AppSettingMapper appSettingMapper;
    @Override
    public AppSettingDTO save(AppSettingDTO appSettingDTO) {
        return appSettingMapper.ToDto(appSettingRepository.save(appSettingMapper.DtoToEntity(appSettingDTO)));
    }


    @Override
    public AppSettingDTO update(AppSettingDTO appSettingDTO) {
        return appSettingMapper.ToDto(appSettingRepository.save(appSettingMapper.DtoToEntity(appSettingDTO)));
    }

    @Override
    public void delete(Long id) {
        appSettingRepository.deleteById(id);
    }

    @Override
    public List<AppSettingDTO> findAll() {
        return appSettingRepository.findAll().stream().map(appSetting -> {
            return appSettingMapper.ToDto(appSetting);
        }).toList();
    }

    @Override
    public Optional<AppSettingDTO> findOne(Long id) {
        return appSettingRepository.findById(id).map(address -> {
            return appSettingMapper.ToDto(address);
        });
    }

    @Override
    public AppSettingDTO initApp(AppSettingDTO appSettingDTO) {
        log.debug("Request to init app {}", appSettingDTO);
        System.out.println(appSettingDTO);
        AppSettingDTO settingDTO = existingParameter();
        if (settingDTO == null) {

            return save(appSettingDTO);
        }
        return settingDTO;
    }

    @Override
    public AppSettingDTO existingParameter() {
        log.debug("Request to check if existing parameter");
        List<AppSettingDTO> all = findAll();
        return all.stream().findFirst().orElse(null);
    }

    @Override
    public List<AppSettingDTO> findAllBySmtpUsernames(String smtpUsername) {
        log.debug("Request to get all AppSetting by smtpUsername : {}", smtpUsername);
        return appSettingRepository.findBySmtpUsername(smtpUsername).stream().map(appSetting -> {
            return appSettingMapper.ToDto(appSetting);
        }).toList();
    }
}
