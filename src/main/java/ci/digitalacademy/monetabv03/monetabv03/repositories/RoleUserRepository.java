package ci.digitalacademy.monetabv03.monetabv03.repositories;


import ci.digitalacademy.monetabv03.monetabv03.models.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {
    List<RoleUser> findBynameRole(String nameRole);
}
