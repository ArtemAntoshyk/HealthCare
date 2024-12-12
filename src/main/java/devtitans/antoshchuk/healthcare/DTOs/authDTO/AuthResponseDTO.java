package devtitans.antoshchuk.healthcare.DTOs.authDTO;
import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private String role;
    private String username;
}
