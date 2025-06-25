package medrecords.domain;

import medrecords.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> findAllPatients();
    Patient findPatientById(Long id);
    Patient createPatient(Patient patient);
    Patient updatePatient(Patient patient);
    Patient deletePatientById(Long id);
    long countPatients();
}
