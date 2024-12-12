package devtitans.antoshchuk.healthcare.services;

import devtitans.antoshchuk.healthcare.models.Examination;
import devtitans.antoshchuk.healthcare.models.ReferralForExamination;
import devtitans.antoshchuk.healthcare.models.ResultOfExamination;
import devtitans.antoshchuk.healthcare.repositories.ExaminationRefferralRepository;
import devtitans.antoshchuk.healthcare.repositories.ExaminationRepository;
import devtitans.antoshchuk.healthcare.repositories.ResultOfExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminationService {
    @Autowired
    private ExaminationRepository examinationRepository;
    @Autowired
    private ResultOfExaminationRepository resultOfExaminationRepository;
    @Autowired
    private ExaminationRefferralRepository refferalForExaminationRepository;


    public void addNewExamination(Examination examination) {
        examinationRepository.save(examination);
    }

    public void addNewRefferalForExamination(ReferralForExamination refferralForExamination) {
        refferalForExaminationRepository.save(refferralForExamination);
    }

    public void addNewResultOfExamination(ResultOfExamination resultOfExamination) {
        resultOfExaminationRepository.save(resultOfExamination);
    }

    public List<Examination> getAllExamination() {
        return examinationRepository.findAll();
    }

    public List<ResultOfExamination> getAllResultOfExamination() {
        return resultOfExaminationRepository.findAll();
    }

    public List<ReferralForExamination> getAllRefferalForExamination() {
        return refferalForExaminationRepository.findAll();
    }

    public Examination getExaminationById(Long id) {
        return examinationRepository.findById(id);
    }

    public ResultOfExamination getResultOfExaminationById(Long id) {
        return resultOfExaminationRepository.findById(id);
    }

    public ReferralForExamination getRefferalForExaminationById(Long id) {
        return refferalForExaminationRepository.findById(id);
    }
}


