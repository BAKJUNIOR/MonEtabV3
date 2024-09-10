package ci.digitalacademy.monetabv03.monetabv03.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                        .requestMatchers("/css/**").permitAll() // Permettre l'accès aux ressources css
                        .requestMatchers("/images/**").permitAll() // Permettre l'accès aux ressources images
                        .requestMatchers("/js/**").permitAll() // Permettre l'accès aux ressources js
                        .requestMatchers("/reports").permitAll() // Autoriser l'accès au point de terminaison /reports
                        .requestMatchers("/reports/generateExcel").permitAll() // Autoriser l'accès au point de terminaison /generateExcel
                        .requestMatchers("/students").permitAll() // Autoriser l'accès au point de terminaison /students
                        .requestMatchers("/SeConnecter").permitAll() // Autoriser l'accès à /SeConnecter
                        .requestMatchers("/app-setting").permitAll() // Autoriser l'accès à /app-setting
                        .requestMatchers("/login").permitAll() // Autoriser l'accès à la page de connexion
                        .requestMatchers("/").permitAll() // Accès à la page d'accueil
                        .requestMatchers("/public/**").permitAll() // Accès aux ressources publiques
                        .requestMatchers("/students/**").permitAll() // Accès aux ressources publiques
                        .requestMatchers("/api/students/**").permitAll() // Accès aux ressources publiques
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
                . exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")); // Rediriger vers la page de connexion si non authentifié

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}