package medrecords.dao;

import medrecords.model.Prescription;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionRepository extends Repository<Long,Prescription>{
    List<Prescription> findByDoctorIdPatientIdDate(Long docId, Long patientId, LocalDate date);
}
