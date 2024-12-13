package devtitans.antoshchuk.healthcare.DTOs.ticketDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketCreateDTO {

    private Long doctorId;

    private Date appointmentDate;
}
