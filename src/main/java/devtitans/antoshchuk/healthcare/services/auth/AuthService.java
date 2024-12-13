package devtitans.antoshchuk.healthcare.services.auth;

import devtitans.antoshchuk.healthcare.DTOs.authDTO.UserRegistrationDTO;
import devtitans.antoshchuk.healthcare.models.*;
import devtitans.antoshchuk.healthcare.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final SpecializationRepository specializationRepository;
    private final CabinetRepository cabinetRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Користувач з таким ім'ям уже існує.");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Користувач з таким email уже існує.");
        }

        // Створення користувача
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setSecondName(dto.getSecondName());
        user.setPatronymic(dto.getPatronymic());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhoneNum(dto.getPhoneNum());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole().toUpperCase());

        user = userRepository.save(user);

        // Реєстрація лікаря або пацієнта
        if ("DOCTOR".equals(dto.getRole().toUpperCase())) {
            Doctor doctor = new Doctor();
            doctor.setUser(user);

            if (dto.getSpecializationId() != null) {
                Optional<Specialization> specialization = specializationRepository.findById(dto.getSpecializationId());
                doctor.setSpecialization(specialization.orElseThrow(
                        () -> new IllegalArgumentException("Спеціалізацію не знайдено")));
            }

            if (dto.getCabinetId() != null) {
                Optional<Cabinet> cabinet = cabinetRepository.findById(dto.getCabinetId());
                doctor.setCabinet(cabinet.orElseThrow(() -> new IllegalArgumentException("Кабінет не знайдено")));
            }

            doctor.setYearExperience((long) dto.getYearExperience());
            doctorRepository.save(doctor);
        } else if ("PATIENT".equals(dto.getRole().toUpperCase())) {
            Patient patient = new Patient();
            patient.setUser(user);
            patient.setEmergencyContact(dto.getEmergencyContact());
            patientRepository.save(patient);
        }

        return user;
    }
}