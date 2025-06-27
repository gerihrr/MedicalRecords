package medrecords.domain.impl;

import medrecords.dao.*;
import medrecords.domain.PatientService;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class PatientImpl implements PatientService {

    private PatientRepository PatientRepository;
    private AppointmentRepository appointmentRepository;
    private PrescriptionRepository prescriptionRepository;
    private DoctorRepository doctorRepository;
    private MedicalRecordRepository medicalRecordRepository;

    public PatientImpl(PatientRepository patientRepository, AppointmentRepository appointmentRepository, PrescriptionRepository prescriptionRepository, DoctorRepository doctorRepository, MedicalRecordRepository medicalRecordRepository) {
        PatientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.doctorRepository = doctorRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Autowired


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
        if(!old.getPrescriptions().isEmpty()){
            old.getPrescriptions().forEach(p -> {
                prescriptionRepository.deleteById(p.getId());
                p.getDoctor().getPrescriptions().remove(p);
            });
        };

        if(!old.getAppointments().isEmpty()){
            old.getAppointments().forEach(a -> {
                appointmentRepository.deleteById(a.getId());
                a.getDoctor().getAppointments().remove(a);
            });
        };

        if(!old.getMedicalRecords().isEmpty()){
            old.getMedicalRecords().forEach(r -> {
                medicalRecordRepository.deleteById(r.getId());
                r.getDoctor().getMedicalRecords().remove(r);
            });
        };
        PatientRepository.deleteById(id);
        return old;
    }

    @Override
    public long countPatients() {
        return PatientRepository.count();
    }
}
