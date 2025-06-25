package medrecords.domain.impl;

import medrecords.dao.PatientRepository;
import medrecords.domain.PatientService;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientImpl implements PatientService {

    private PatientRepository PatientRepository;

    @Autowired
    public PatientImpl (PatientRepository PatientRepository) {
        this.PatientRepository = PatientRepository;
    }


    @Override
    public List<Patient> findAllPatients() {
        return PatientRepository.findAll();
    }

    @Override
    public Patient findPatientById (Long id) {
        return PatientRepository.findById(id).orElseThrow(() -> new NonexisitingEntityException(
                String.format("User with ID='%s' does not exist.", id)
        ));
    }

    @Override
    public Patient createPatient(Patient Patient) {
        return PatientRepository.create(Patient);
    }

    @Override
    public Patient updatePatient(Patient Patient) {
        return PatientRepository.update(Patient).orElseThrow(() ->
                new NonexisitingEntityException(
                        String.format("Cannot update non-existing user '%s' with id Id=%d", Patient.getUsername(), Patient.getId())
                ));
    }

    @Override
    public Patient deletePatientById(Long id) {
        return PatientRepository.deleteById(id).orElseThrow(() ->
                new NonexisitingEntityException(
                        String.format("Cannot update non-existing with id Id=%d", id)
                ));
    }

    @Override
    public long countPatients() {
        return PatientRepository.count();
    }
}
