package medrecords.domain.impl;

import medrecords.dao.MedicalRecordRepository;
import medrecords.dao.DoctorRepository;
import medrecords.dao.MedicalRecordRepository;
import medrecords.dao.PatientRepository;
import medrecords.domain.MedicalRecordsService;
import medrecords.exception.InvalidEntityDataException;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.MedicalRecord;
import medrecords.model.MedicalRecord;
import medrecords.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class MedicalRecordsImpl implements MedicalRecordsService {

    private MedicalRecordRepository medicalRecordRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    @Autowired
    public MedicalRecordsImpl(MedicalRecordRepository medicalRecordRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }



    @Override
    public List<MedicalRecord> findAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public MedicalRecord findMedicalRecordById (Long id) {
        return medicalRecordRepository.findById(id).orElseThrow(() -> new NonexisitingEntityException(
                String.format("MedicalRecord with ID='%s' does not exist.", id)
        ));
    }

    @Override
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {

        if(medicalRecord.getDoctor() == null || medicalRecord.getDoctor().getId() ==null) {
            throw new InvalidEntityDataException(String.format("Doctor with valid user ID is required"));
        }
        var doctor = doctorRepository.findById(medicalRecord.getDoctor().getId()).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Doctor does not exist")));


        if(medicalRecord.getPatient() == null || medicalRecord.getPatient().getId() ==null) {
            throw new InvalidEntityDataException(String.format("Patient with valid user ID is required"));
        }
        var patient = patientRepository.findById(medicalRecord.getPatient().getId()).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Patient does not exist")));

        medicalRecord.setId(null);
        medicalRecord.setDoctor(doctor);
        doctor.getMedicalRecords().add(medicalRecord);
        medicalRecord.setPatient(patient);
        patient.getMedicalRecords().add(medicalRecord);

        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        var old = findMedicalRecordById(medicalRecord.getId());

        if(medicalRecord.getDoctor() == null || medicalRecord.getDoctor().getId() ==null) {
            throw new InvalidEntityDataException(String.format("Doctor with valid user ID is required"));
        }
        var doctor = doctorRepository.findById(medicalRecord.getDoctor().getId()).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Doctor does not exist")));


        if(medicalRecord.getPatient() == null || medicalRecord.getPatient().getId() ==null) {
            throw new InvalidEntityDataException(String.format("Patient with valid user ID is required"));
        }
        var patient = patientRepository.findById(medicalRecord.getPatient().getId()).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Patient does not exist")));

        if(!(old.getDoctor().equals(medicalRecord.getDoctor()) && old.getPatient().equals(medicalRecord.getPatient()))){
            throw new InvalidEntityDataException(String.format("Patient and Doctor data cannot be changed"));
        }

        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord deleteMedicalRecordById(Long id) {
        var old = findMedicalRecordById(id);
        old.getDoctor().getMedicalRecords().remove(old);
        old.getPatient().getMedicalRecords().remove(old);
        medicalRecordRepository.deleteById(id);

        return old;
    }

    @Override
    public long countMedicalRecords() {
        return medicalRecordRepository.count();
    }

    @Override
    public List<MedicalRecord> filterByDoctorPatientDate(Long docId, Long patId, LocalDate date) {
        doctorRepository.findById(docId).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Doctor does not exist")));


        patientRepository.findById(patId).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Patient does not exist")));

        return medicalRecordRepository.findByDoctorIdAndPatientIdAndDate(docId, patId, date);
    }
}

