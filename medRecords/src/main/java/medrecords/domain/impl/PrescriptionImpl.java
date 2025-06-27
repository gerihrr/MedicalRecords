package medrecords.domain.impl;

import medrecords.dao.DoctorRepository;
import medrecords.dao.PatientRepository;
import medrecords.dao.PrescriptionRepository;
import medrecords.domain.PrescriptionService;
import medrecords.exception.InvalidEntityDataException;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.MedicalRecord;
import medrecords.model.Prescription;
import medrecords.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional

public class PrescriptionImpl implements PrescriptionService {

    private PrescriptionRepository prescriptionRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    @Autowired
    public PrescriptionImpl(PrescriptionRepository prescriptionRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }



    @Override
    public List<Prescription> findAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @Override
    public Prescription findPrescriptionById (Long id) {
        return prescriptionRepository.findById(id).orElseThrow(() -> new NonexisitingEntityException(
                String.format("Prescription with ID='%s' does not exist.", id)
        ));
    }

    @Override
    public Prescription createPrescription(Prescription prescription) {
        if(prescription.getDoctor() == null || prescription.getDoctor().getId() ==null) {
            throw new InvalidEntityDataException(String.format("Doctor with valid user ID is required"));
        }
        var doctor = doctorRepository.findById(prescription.getDoctor().getId()).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Doctor does not exist")));


        if(prescription.getPatient() == null || prescription.getPatient().getId() ==null) {
            throw new InvalidEntityDataException(String.format("Patient with valid user ID is required"));
        }
        var patient = patientRepository.findById(prescription.getPatient().getId()).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Patient does not exist")));

        prescription.setId(null);
        prescription.setDoctor(doctor);
        doctor.getPrescriptions().add(prescription);
        prescription.setPatient(patient);
        patient.getPrescriptions().add(prescription);

        return prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription updatePrescription(Prescription prescription) {
        var old = findPrescriptionById(prescription.getId());

        if(prescription.getDoctor() == null || prescription.getDoctor().getId() ==null) {
            throw new InvalidEntityDataException(String.format("Doctor with valid user ID is required"));
        }
        var doctor = doctorRepository.findById(prescription.getDoctor().getId()).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Doctor does not exist")));


        if(prescription.getPatient() == null || prescription.getPatient().getId() ==null) {
            throw new InvalidEntityDataException(String.format("Patient with valid user ID is required"));
        }
        var patient = patientRepository.findById(prescription.getPatient().getId()).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Patient does not exist")));

        if(!(old.getDoctor().equals(prescription.getDoctor()) && old.getPatient().equals(prescription.getPatient()))){
            throw new InvalidEntityDataException(String.format("Patient and Doctor data cannot be changed"));
        }

        return prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription deletePrescriptionById(Long id) {
        var old = findPrescriptionById(id);
        old.getDoctor().getPrescriptions().remove(old);
        old.getPatient().getPrescriptions().remove(old);
        prescriptionRepository.deleteById(id);

        return old;
    }

    @Override
    public long countPrescriptions() {
        return prescriptionRepository.count();
    }

    @Override
    public List<Prescription> filterByDoctorPatientDate(Long docId, Long patId, LocalDate date) {
        doctorRepository.findById(docId).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Doctor does not exist")));


        patientRepository.findById(patId).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Patient does not exist")));

        return prescriptionRepository.findByDoctorIdAndPatientIdAndDate(docId, patId, date);
    }
}
