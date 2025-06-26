package medrecords.dao;

import medrecords.model.MedicalRecord;
import medrecords.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByDoctorIdAndPatientIdAndDate(Long docId, Long patientId, LocalDate date);

}
