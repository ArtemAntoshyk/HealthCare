package devtitans.antoshchuk.healthcare.DTOs.usersDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientDTO {
    private Long id;
    private UserMainInfoDTO user;
}
