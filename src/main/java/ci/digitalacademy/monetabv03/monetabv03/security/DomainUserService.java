package ci.digitalacademy.monetabv03.monetabv03.security;

import ci.digitalacademy.monetabv03.monetabv03.models.RoleUser;
import ci.digitalacademy.monetabv03.monetabv03.models.User;
import ci.digitalacademy.monetabv03.monetabv03.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DomainUserService implements UserDetailsService {
    public final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Optional<User> user = userRepository.findByPseudo(username);
     if (user.isEmpty()){
         throw new IllegalArgumentException("User n'existe pas");
     }

     final List<GrantedAuthority> grantedAuthorities = user.get()
             .getRoleUser()
             .stream()
             .map(RoleUser:: getNameRole)
             .map(SimpleGrantedAuthority::new)
             .collect(Collectors.toList());

     return user.map(userRecover -> org.springframework.security.core.userdetails.User.builder()

             .username(userRecover.getPseudo())
             .password(userRecover.getPassword())
             .authorities(grantedAuthorities)
//             .roles()
             .build()).orElseThrow(()-> new IllegalArgumentException("User n'existe pas"));
    }
}
