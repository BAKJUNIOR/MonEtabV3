package ci.digitalacademy.monetabv03.monetabv03.repositories;

import ci.digitalacademy.monetabv03.monetabv03.models.AppSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppSettingRepository extends JpaRepository<AppSetting, Long> {

    List<AppSetting> findBySmtpUsername(String smtpUsername);

}
