package devtitans.antoshchuk.healthcare.controllers.ticket;

import devtitans.antoshchuk.healthcare.DTOs.ticketDTO.TicketCreateDTO;
import devtitans.antoshchuk.healthcare.DTOs.ticketDTO.TicketDTO;
import devtitans.antoshchuk.healthcare.models.Ticket;
import devtitans.antoshchuk.healthcare.models.User;
import devtitans.antoshchuk.healthcare.services.users.DoctorService;
import devtitans.antoshchuk.healthcare.services.users.PatientService;
import devtitans.antoshchuk.healthcare.services.users.TicketService;
import devtitans.antoshchuk.healthcare.services.users.UserService;
import devtitans.antoshchuk.healthcare.util.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper = new ModelMapper();

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @GetMapping("/get_user_tickets")
    public ResponseEntity<List<TicketDTO>> getUserTickets(HttpServletRequest request) {
        String username = extractUsernameFromRequest(request);
        User user = userService.getUserByUsername(username).get();
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        if (username == null) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
        Long userId = userService.getIdByUsername(username);
        System.out.println(user.getRole());
        if (user.getRole().equals("PATIENT")) {
            List<Ticket> tickets = ticketService.getTicketsByPatientUsername(userId);
            ticketDTOs = convertToDTOList(tickets);
        } else if (user.getRole().equals("DOCTOR")) {
            Long doctorId = doctorService.getDoctorIdUserId(userId);
            List<Ticket> tickets = ticketService.getTicketsByDoctorId(doctorId);
            ticketDTOs = convertToDTOList(tickets);
        }
        logTickets(ticketDTOs);
        return ResponseEntity.ok(ticketDTOs);
    }

    @GetMapping("/get_reserved_time_by_doctor_and_date/{doctorId}")
    public ResponseEntity<List<TicketDTO>> getReservedTimeByDoctor(
            @PathVariable Long doctorId) {
        List<Ticket> tickets = ticketService.getTicketsByDoctorId(doctorId);
        logTickets(tickets);

        List<TicketDTO> reservedTickets = convertToDTOList(tickets);

        return ResponseEntity.ok(reservedTickets);
    }

    @PostMapping("/create_ticket_for_user")
    public ResponseEntity<String> createTicket(
            @RequestBody TicketCreateDTO ticketDTO, HttpServletRequest request) {
        String username = extractUsernameFromRequest(request);
        if (username == null) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }

        Ticket ticket = buildTicketFromDTO(ticketDTO, username);
        ticketService.createTicket(ticket);

        logger.info("Ticket created successfully for user: {}", username);
        return ResponseEntity.ok("Ticket created");
    }


    private String extractUsernameFromRequest(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            logger.error("Invalid or missing token");
            return null;
        }
        return jwtTokenProvider.getUsername(token);
    }

    private List<TicketDTO> convertToDTOList(List<Ticket> tickets) {
        return tickets.stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .toList();
    }

    private Ticket buildTicketFromDTO(TicketCreateDTO ticketDTO, String username) {
        Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);
        ticket.setDoctor(doctorService.getDoctorById(ticketDTO.getDoctorId()).orElseThrow(() ->
                new IllegalArgumentException("Doctor not found")));
        ticket.setPatient(patientService.getPatientByUsername(username));
        return ticket;
    }

    private void logTickets(List<?> tickets) {
        System.out.println("Tickets:");
        tickets.forEach(ticket -> logger.info(ticket.toString()));
    }
}

