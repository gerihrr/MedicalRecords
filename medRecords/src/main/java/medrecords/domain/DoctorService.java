package medrecords.domain;

import medrecords.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> findAllDoctors();
    Doctor findDoctorById(Long id);
    Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(Doctor doctor);
    Doctor deleteDoctorById(Long id);
    long countDoctors();


}
