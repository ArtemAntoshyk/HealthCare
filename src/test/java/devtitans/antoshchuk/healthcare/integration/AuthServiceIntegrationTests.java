package devtitans.antoshchuk.healthcare.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import devtitans.antoshchuk.healthcare.DTOs.authDTO.UserRegistrationDTO;
import devtitans.antoshchuk.healthcare.models.User;
import devtitans.antoshchuk.healthcare.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthServiceIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldRegisterUserSuccessfully() throws Exception {
        // Arrange
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setUsername("testUser");
        dto.setEmail("testUser@example.com");
        dto.setPassword("securePassword");
        dto.setRole("PATIENT");

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        User registeredUser = userRepository.findByUsername("testUser").orElse(null);
        assertNotNull(registeredUser);
    }

    @Test
    void shouldReturnErrorForExistingUsername() throws Exception {
        // Arrange
        User existingUser = new User();
        existingUser.setUsername("existingUser");
        userRepository.save(existingUser);

        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setUsername("existingUser");
        dto.setEmail("newEmail@example.com");

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
