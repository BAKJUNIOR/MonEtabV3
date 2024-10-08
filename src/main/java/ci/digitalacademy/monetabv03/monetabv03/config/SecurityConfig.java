package ci.digitalacademy.monetabv03.monetabv03.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrfConfigurer -> csrfConfigurer.disable()) // Désactiver la protection CSRF pour cette configuration
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll() // Autoriser les types de requêtes FORWARD et ERROR
                        .requestMatchers("/swagger-ui/**").permitAll() // Permettre l'accès au point de terminaison swagger-ui et tout ce qui suit
                        .requestMatchers("/v3/api-docs/**").permitAll() // Permettre l'accès au point
                        .requestMatchers("/css/**").permitAll() // Permettre l'accès aux ressources css
                        .requestMatchers("/images/**").permitAll() // Permettre l'accès aux ressources images
                        .requestMatchers("/js/**").permitAll() // Permettre l'accès aux ressources js
                        .requestMatchers("/public/**").permitAll() // Accès aux ressources publiques
                        .requestMatchers("/reports").permitAll() // Autoriser l'accès au point de terminaison /reports
                        .requestMatchers("/reports/**").permitAll() // Permettre l'accès au point
                         .requestMatchers("/schools/**").permitAll()
                         .requestMatchers("/appSettings/**").permitAll()
                         .requestMatchers("/api/**").permitAll()
                         .requestMatchers("/login").anonymous()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/users/**").permitAll()
                        .requestMatchers("/index").permitAll()
                        .requestMatchers("/students/**").permitAll() // Accès aux ressources publiques
                        .anyRequest().authenticated() // Toutes les autres demandes nécessitent une authentification


                )
                .formLogin(login -> login
                        .loginPage("/login").permitAll() // Autoriser l'accès à la page de connexion
                        .defaultSuccessUrl("/home", true) // Rediriger vers /home après une connexion réussie
                        .failureUrl("/login?error=true") // Rediriger vers la page de connexion avec une erreur en cas d'échec
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL de déconnexion
                        .logoutSuccessUrl("/login?logout=true") // Rediriger vers la page de connexion après la déconnexion
                        .invalidateHttpSession(true) // Invalider la session après déconnexion
                        .deleteCookies("JSESSIONID") // Supprimer le cookie de session après déconnexion
                        .permitAll() // Permettre à chacun de se déconnecter
                )
                .sessionManagement(session -> session // Crée une session d'état pour les utilisateurs se connectant via le formulaire
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt()); // Utiliser OAuth2 pour les API sécurisées via JWT

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}