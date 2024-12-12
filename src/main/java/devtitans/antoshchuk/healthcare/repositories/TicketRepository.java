package devtitans.antoshchuk.healthcare.repositories;

import devtitans.antoshchuk.healthcare.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByPatientId(Long patientId);

    List<Ticket> findAllByDoctorId(Long id);
}
