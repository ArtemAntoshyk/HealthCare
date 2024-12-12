package devtitans.antoshchuk.healthcare.services.users;

import devtitans.antoshchuk.healthcare.models.Ticket;
import devtitans.antoshchuk.healthcare.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTicketsByPatientUsername(Long id){
        return ticketRepository.findAllByPatientId(id);
    }

    public List<Ticket> getTicketsByDoctorId(Long id){
        return ticketRepository.findAllByDoctorId(id);
    }
    public void createTicket(Ticket ticket){
        Date appointmentDate = ticket.getAppointmentDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(appointmentDate);
        calendar.add(Calendar.MINUTE, 30);
        Date endTime = calendar.getTime();
        ticket.setEndTime(endTime);
        ticket.setIssueDate(new Date());
        ticket.setStatus("PENDING");
        ticketRepository.save(ticket);
    }
}
