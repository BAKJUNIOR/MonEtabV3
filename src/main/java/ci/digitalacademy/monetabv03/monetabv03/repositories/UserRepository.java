package ci.digitalacademy.monetabv03.monetabv03.repositories;

import ci.digitalacademy.monetabv03.monetabv03.models.User;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPseudo(String pseudo);
    List<User> findByCreatedDateLessThanAndRoleUserNameRole(Instant createdDate, String role);
}
