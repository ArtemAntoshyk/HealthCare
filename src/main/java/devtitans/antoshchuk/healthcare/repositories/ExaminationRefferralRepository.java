package devtitans.antoshchuk.healthcare.repositories;

import devtitans.antoshchuk.healthcare.models.Examination;
import devtitans.antoshchuk.healthcare.models.ReferralForExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ExaminationRefferralRepository extends JpaRepository<ReferralForExamination, Integer> {

    ReferralForExamination findById(Long id);
}
