package devtitans.antoshchuk.healthcare.controllers.examination;

import devtitans.antoshchuk.healthcare.DTOs.ticketDTO.ExaminationCreateDTO;
//import devtitans.antoshchuk.healthcare.DTOs.ExaminationDTO
import devtitans.antoshchuk.healthcare.util.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/examination")
public class ExaminationController {

    private final JwtTokenProvider jwtTokenProvider;
    private static final Logger logger = LoggerFactory.getLogger(ExaminationController.class);

    @Autowired
    public ExaminationController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/add_examination")
    public ResponseEntity<String> addExamination(@RequestBody ExaminationCreateDTO examinationDTO, HttpServletRequest request) {
        String username = extractUsernameFromRequest(request);
        if (username == null) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }

return null;
    }

    private String extractUsernameFromRequest(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            logger.error("Invalid or missing token");
            return null;
        }
        return jwtTokenProvider.getUsername(token);
    }


}
