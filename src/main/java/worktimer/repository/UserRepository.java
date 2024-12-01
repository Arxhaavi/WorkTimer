package worktimer.repository;

import worktimer.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // a way to find user by username
    User findByUsername(String username);
}