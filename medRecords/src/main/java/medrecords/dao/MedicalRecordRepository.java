package medrecords.dao;

import medrecords.model.MedicalRecord;

import java.time.LocalDate;
import java.util.List;

public interface MedicalRecordRepository extends Repository<Long, MedicalRecord>{
    List<MedicalRecord> findByDoctorId(Long id);
    List<MedicalRecord> findByPatientId(Long id);
    List<MedicalRecord> findByDate(LocalDate date);
}
