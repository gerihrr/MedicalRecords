package medrecords.dao;

import medrecords.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {
    List<Prescription> findByDoctorIdAndPatientIdAndDate(Long docId, Long patientId, LocalDate date);
}
