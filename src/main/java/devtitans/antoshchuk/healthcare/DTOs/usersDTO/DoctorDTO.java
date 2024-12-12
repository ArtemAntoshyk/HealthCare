package devtitans.antoshchuk.healthcare.DTOs.usersDTO;

import devtitans.antoshchuk.healthcare.models.Specialization;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorDTO {
    private Long id;
    private UserMainInfoDTO user;
    private SpecializationDTO specialization;
    private CabinetDTO cabinet;
}
