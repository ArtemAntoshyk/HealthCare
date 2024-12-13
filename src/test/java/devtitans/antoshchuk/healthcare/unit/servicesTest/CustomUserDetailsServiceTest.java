package devtitans.antoshchuk.healthcare.unit.servicesTest;

import devtitans.antoshchuk.healthcare.models.User;
import devtitans.antoshchuk.healthcare.repositories.UserRepository;
import devtitans.antoshchuk.healthcare.services.auth.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomUserDetailsServiceTest {

    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Test
    void shouldLoadUserByUsernameSuccessfully() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");

        // Assert
        assertNotNull(userDetails);
        assertEquals("testUser", userDetails.getUsername());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("nonExistentUser"));
    }
}

