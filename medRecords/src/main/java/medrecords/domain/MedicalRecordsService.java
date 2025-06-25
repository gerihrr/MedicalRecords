package medrecords.domain;

import medrecords.model.Appointment;
import medrecords.model.MedicalRecord;
import medrecords.model.MedicalRecord;

import java.time.LocalDate;
import java.util.List;

public interface MedicalRecordsService {
    List<MedicalRecord> findAllMedicalRecords();
    MedicalRecord findMedicalRecordById(Long id);
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);
    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);
    MedicalRecord deleteMedicalRecordById(Long id);
    long countMedicalRecords();
    List<MedicalRecord> filterByDoctorPatientDate(Long docId, Long patId, LocalDate date);

}
