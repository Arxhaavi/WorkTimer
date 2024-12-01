package worktimer.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//JPA entity for User for login purposes
@Entity
public class User implements UserDetails {
    // Auto-generate ID for primary key
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String role;

    // Getters and setters for the different fields in the table
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Create methods for UserDetails because it doesn't work without these here
    // First we fetch all authorities granted by role
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Get a single authority by user's role
        return Collections.singleton(() -> "ROLE_" + role);
    }

    // check account expiration if you're using temporary accounts.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // check if account is locked (banned)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // check if user is disabled
    @Override
    public boolean isEnabled() {
        return true;
    }
}