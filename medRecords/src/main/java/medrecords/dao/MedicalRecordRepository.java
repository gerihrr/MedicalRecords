package medrecords.dao;

import medrecords.model.MedicalRecord;
import medrecords.model.Prescription;

import java.time.LocalDate;
import java.util.List;

public interface MedicalRecordRepository extends Repository<Long, MedicalRecord>{
    List<MedicalRecord> findByDoctorIdPatientIdDate(Long docId, Long patientId, LocalDate date);

}
