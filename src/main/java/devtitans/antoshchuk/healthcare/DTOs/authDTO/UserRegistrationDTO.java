package devtitans.antoshchuk.healthcare.DTOs.authDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class UserRegistrationDTO {
    private String firstName;
    private String secondName;
    private String patronymic;
    private String username;
    private String email;
    private String phoneNum;
    private String password;
    private String role; // PATIENT or DOCTOR

    // Fields specific to doctor
    private Long specializationId;
    private int yearExperience;
    private Long cabinetId;

    // Fields specific to patient
    private String emergencyContact;
}
