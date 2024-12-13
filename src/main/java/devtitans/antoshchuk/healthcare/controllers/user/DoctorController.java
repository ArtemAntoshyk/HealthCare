package devtitans.antoshchuk.healthcare.controllers.user;

import devtitans.antoshchuk.healthcare.DTOs.usersDTO.DoctorDTO;
import devtitans.antoshchuk.healthcare.models.Doctor;
import devtitans.antoshchuk.healthcare.services.users.DoctorService;
import devtitans.antoshchuk.healthcare.services.users.TicketService;
import devtitans.antoshchuk.healthcare.util.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private DoctorService doctorService;
    private TicketService ticketService;
    private final JwtTokenProvider jwtTokenProvider;
    private ModelMapper modelMapper = new ModelMapper();
    private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);
    @Autowired
    public DoctorController(DoctorService doctorService, TicketService ticketService, JwtTokenProvider jwtTokenProvider) {
        this.doctorService = doctorService;
        this.ticketService = ticketService;

        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/all_doctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors(HttpServletRequest request) {
        String username = getUsernameFromToken(request);
        if (username == null) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }

        List<Doctor> doctors = doctorService.getAllDoctors();
        List<DoctorDTO> doctorDTOs = doctors.stream().map(this::convertDoctorToDoctorDTO).toList();
        return ResponseEntity.ok(doctorDTOs);
    }

    private String getUsernameFromToken(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return null;
        }
        return jwtTokenProvider.getUsername(token);
    }



    private DoctorDTO convertDoctorToDoctorDTO(Doctor doctor){
        return modelMapper.map(doctor, DoctorDTO.class);
    }

}
