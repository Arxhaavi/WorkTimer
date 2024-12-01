package worktimer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // disable csrf (cross site request forgery) for testing
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        // Let everyone access login and h2console etc.
                        .requestMatchers("/", "/login", "/h2-console/**", "/error", "/login/**").permitAll()
                        // Here we set everything else to require authentication
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        // Set /login as the login page
                        .loginPage("/login")
                        .permitAll()
                        .failureUrl("/login?error")
                        // Default success page should be the home page
                        .defaultSuccessUrl("/home", true))
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());

        return http.build();
    }

    // Create an encoder for password security using BCrypt encryption
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
