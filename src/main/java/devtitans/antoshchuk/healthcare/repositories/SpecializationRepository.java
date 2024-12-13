package devtitans.antoshchuk.healthcare.repositories;

import devtitans.antoshchuk.healthcare.models.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    @Override
    Optional<Specialization> findById(Long aLong);
}
