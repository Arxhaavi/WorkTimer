package worktimer.configuration;

import worktimer.entity.User;
import worktimer.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

//We use this to create a test user
@Configuration
public class TestUserInitializer {
    @Bean
    // Create a test user using commandlinerunner. Username: testuser, password:
    // password
    public CommandLineRunner createTestUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // check if testuser exists and create it if it doesn't
            if (userRepository.findByUsername("testuser") == null) {
                User user = new User();
                user.setUsername("testuser");
                user.setPassword(passwordEncoder.encode("password"));
                user.setRole("USER");
                userRepository.save(user);
                System.out.println("Test user created!");
            }
            User user = userRepository.findByUsername("testuser");
            if (user != null) {
                System.out.println("User found: " + user.getUsername());
            } else {
                System.out.println("User not found!");
            }
        };
    }
}
