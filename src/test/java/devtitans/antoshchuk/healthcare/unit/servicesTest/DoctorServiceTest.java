package devtitans.antoshchuk.healthcare.unit.servicesTest;

import devtitans.antoshchuk.healthcare.models.Doctor;
import devtitans.antoshchuk.healthcare.repositories.DoctorRepository;
import devtitans.antoshchuk.healthcare.services.users.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class DoctorServiceTest {
//    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService;

    @Test
    void shouldCreateDoctorSuccessfully() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        // Act
        doctorService.createDoctor(doctor);

        // Assert
        verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    void shouldReturnDoctorById() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        // Act
        Optional<Doctor> result = doctorService.getDoctorById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }
}
