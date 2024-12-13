package devtitans.antoshchuk.healthcare.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import devtitans.antoshchuk.healthcare.DTOs.ticketDTO.TicketCreateDTO;
import devtitans.antoshchuk.healthcare.models.Ticket;
import devtitans.antoshchuk.healthcare.repositories.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TicketServiceIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void shouldCreateTicketSuccessfully() throws Exception {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setDoctorId(1L);
        dto.setAppointmentDate(new Date());

        // Act & Assert
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        List<Ticket> tickets = ticketRepository.findAll();
        assertFalse(((java.util.List<?>) tickets).isEmpty());
    }
}
