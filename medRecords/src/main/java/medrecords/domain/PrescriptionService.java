package medrecords.domain;

import medrecords.model.Appointment;
import medrecords.model.Prescription;
import medrecords.model.Prescription;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionService  {
    List<Prescription> findAllPrescriptions();
    Prescription findPrescriptionById(Long id);
    Prescription createPrescription(Prescription prescription);
    Prescription updatePrescription(Prescription prescription);
    Prescription deletePrescriptionById(Long id);
    long countPrescriptions();
    List<Prescription> filterByDoctorPatientDate(Long docId, Long patId, LocalDate date);


}
