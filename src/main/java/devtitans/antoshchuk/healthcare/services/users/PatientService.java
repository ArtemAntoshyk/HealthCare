package devtitans.antoshchuk.healthcare.services.users;

import devtitans.antoshchuk.healthcare.models.Patient;
import devtitans.antoshchuk.healthcare.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient getPatientByUsername(String username){
        return patientRepository.findByUserUsername(username);
    }

    public Optional<Patient> getPatientById(Long id){
        return patientRepository.findById(id);
    }

}
