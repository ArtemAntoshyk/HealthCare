package devtitans.antoshchuk.healthcare.DTOs.usersDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CabinetDTO {
    private Long id;
    private Long cabinetNumber;
    private Long floor;
}
