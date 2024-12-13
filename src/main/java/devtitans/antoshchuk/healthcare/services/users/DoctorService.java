package devtitans.antoshchuk.healthcare.services.users;

import devtitans.antoshchuk.healthcare.models.Doctor;
import devtitans.antoshchuk.healthcare.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    public void createDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }
    public Optional<Doctor> getDoctorById(Long id){
        return doctorRepository.findById(id);
    }

    public Long getDoctorIdUserId(Long userId){
        return doctorRepository.findDoctorByUserId(userId).getId();
    }
}
