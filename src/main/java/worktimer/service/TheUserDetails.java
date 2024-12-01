package worktimer.service;

import worktimer.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import worktimer.repository.UserRepository;

//Make this a Spring service for the purposes of having user details for login functionality
@Service
public class TheUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    public TheUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check if the user exists in the user database and give an error if it doesn't
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        return user;

    }

}
