package devtitans.antoshchuk.healthcare.repositories;

import devtitans.antoshchuk.healthcare.models.ResultOfExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ResultOfExaminationRepository extends JpaRepository<ResultOfExamination, Integer> {
    public ResultOfExamination findById(Long id);
}
