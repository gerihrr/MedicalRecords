package medrecords.dao;

import medrecords.model.Prescription;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionRepository extends Repository<Long,Prescription>{
    List<Prescription> findByDoctorId(Long id);
    List<Prescription> findByPatientId(Long id);
    List<Prescription> findByDate(LocalDate date);

}
