package devtitans.antoshchuk.healthcare.DTOs.usersDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserMainInfoDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private String patronymic;
}
