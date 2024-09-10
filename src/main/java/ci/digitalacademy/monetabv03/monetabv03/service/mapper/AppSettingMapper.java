package ci.digitalacademy.monetabv03.monetabv03.service.mapper;


import ci.digitalacademy.monetabv03.monetabv03.models.AppSetting;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AppSettingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppSettingMapper extends EntityMapper<AppSettingDTO, AppSetting> {
}
