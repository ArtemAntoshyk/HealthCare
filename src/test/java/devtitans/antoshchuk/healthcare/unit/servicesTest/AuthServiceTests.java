package devtitans.antoshchuk.healthcare.unit.servicesTest;

import devtitans.antoshchuk.healthcare.DTOs.authDTO.UserRegistrationDTO;
import devtitans.antoshchuk.healthcare.models.Cabinet;
import devtitans.antoshchuk.healthcare.models.Doctor;
import devtitans.antoshchuk.healthcare.models.Specialization;
import devtitans.antoshchuk.healthcare.models.User;
import devtitans.antoshchuk.healthcare.repositories.*;
import devtitans.antoshchuk.healthcare.services.auth.AuthService;
import org.antlr.v4.runtime.misc.LogManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.Optional;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthServiceTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private SpecializationRepository specializationRepository;
    @Mock
    private CabinetRepository cabinetRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldRegisterDoctorSuccessfully() throws IOException {
        // Arrange
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setUsername("doctor1");
        dto.setEmail("doctor1@example.com");
        dto.setPassword("securepassword");
        dto.setRole("DOCTOR");
        dto.setSpecializationId(1L);
        dto.setCabinetId(1L);

        when(userRepository.existsByUsername(dto.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("encodedPassword");
        when(specializationRepository.findById(dto.getSpecializationId()))
                .thenReturn(Optional.of(new Specialization()));
        when(cabinetRepository.findById(dto.getCabinetId()))
                .thenReturn(Optional.of(new Cabinet()));

        // Act
        User registeredUser = authService.registerUser(dto);

        // Assert
        assertNotNull(registeredUser);
        assertEquals(dto.getUsername(), registeredUser.getUsername());
        verify(doctorRepository, times(1)).save(String.valueOf((Doctor.class)));
    }

    private LogManager verify(DoctorRepository doctorRepository, VerificationMode times) {
        return null;
    }

    @Test
    void shouldThrowExceptionWhenUsernameAlreadyExists() {
        // Arrange
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setUsername("existingUser");
        when(userRepository.existsByUsername(dto.getUsername())).thenReturn(true);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> authService.registerUser(dto));
    }
}


