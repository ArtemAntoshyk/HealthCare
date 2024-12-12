package devtitans.antoshchuk.healthcare.repositories;

import devtitans.antoshchuk.healthcare.models.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ExaminationRepository extends JpaRepository<Examination, Integer> {

    public Examination findByName(String name);
    public Examination findById(Long id);

}
