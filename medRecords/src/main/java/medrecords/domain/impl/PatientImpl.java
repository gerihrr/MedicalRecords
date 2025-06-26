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
        return PatientRepository.save(Patient);
    }

    @Override
    public Patient updatePatient(Patient Patient) {
        findPatientById(Patient.getId());
        return PatientRepository.save(Patient);
    }

    @Override
    public Patient deletePatientById(Long id) {
        var old = findPatientById(id);
        PatientRepository.deleteById(id);
        return old;
    }

    @Override
    public long countPatients() {
        return PatientRepository.count();
    }
}
