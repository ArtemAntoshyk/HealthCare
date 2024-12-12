package devtitans.antoshchuk.healthcare.repositories;

import devtitans.antoshchuk.healthcare.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    public Doctor findDoctorByUserId(Long userId);
}
