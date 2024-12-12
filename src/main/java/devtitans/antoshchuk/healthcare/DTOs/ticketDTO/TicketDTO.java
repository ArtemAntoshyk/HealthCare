package devtitans.antoshchuk.healthcare.DTOs.ticketDTO;

import devtitans.antoshchuk.healthcare.DTOs.usersDTO.DoctorDTO;
import devtitans.antoshchuk.healthcare.DTOs.usersDTO.PatientDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketDTO {

    private Long id;

    private DoctorDTO doctor;

    private PatientDTO patient;

    private Date appointmentDate;

    @NotNull
    private Date endTime;

    @NotNull
    private String status;

    private Date issueDate;

    @Override
    public String toString() {
        return "TicketDTO{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", appointmentDate=" + appointmentDate +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", issueDate=" + issueDate +
                '}';
    }
}
