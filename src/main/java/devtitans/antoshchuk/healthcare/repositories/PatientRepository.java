package devtitans.antoshchuk.healthcare.repositories;

import devtitans.antoshchuk.healthcare.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories

public interface PatientRepository extends JpaRepository<Patient, Long> {
    public Patient findByUserUsername(String username);
}
