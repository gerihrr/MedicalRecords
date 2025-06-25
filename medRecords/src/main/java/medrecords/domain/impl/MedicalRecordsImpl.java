package medrecords.domain.impl;

import medrecords.dao.MedicalRecordRepository;
import medrecords.domain.MedicalRecordsService;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.Appointment;
import medrecords.model.MedicalRecord;
import medrecords.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordsImpl implements MedicalRecordsService {

    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public MedicalRecordsImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
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
        return medicalRecordRepository.create(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.update(medicalRecord).orElseThrow(() ->
                new NonexisitingEntityException(
                        String.format("Cannot update non-existing medicalRecord  id Id=%d",  medicalRecord.getId())
                ));
    }

    @Override
    public MedicalRecord deleteMedicalRecordById(Long id) {
        return medicalRecordRepository.deleteById(id).orElseThrow(() ->
                new NonexisitingEntityException(
                        String.format("Cannot update non-existing medicalRecord with id Id=%d", id)
                ));
    }

    @Override
    public long countMedicalRecords() {
        return medicalRecordRepository.count();
    }

    @Override
    public List<MedicalRecord> filterByDoctorPatientDate(Long docId, Long patId, LocalDate date) {
        return medicalRecordRepository.findByDoctorIdPatientIdDate(docId, patId, date);
    }
}

