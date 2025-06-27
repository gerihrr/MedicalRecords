package medrecords.dao;

import medrecords.model.Doctor;
import medrecords.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialityContainingIgnoreCase(String speciality);

}
