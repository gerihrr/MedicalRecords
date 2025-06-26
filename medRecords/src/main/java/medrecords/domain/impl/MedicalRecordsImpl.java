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
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        findMedicalRecordById(medicalRecord.getId());
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord deleteMedicalRecordById(Long id) {
        var old = findMedicalRecordById(id);
        medicalRecordRepository.deleteById(id);
        return old;
    }

    @Override
    public long countMedicalRecords() {
        return medicalRecordRepository.count();
    }

    @Override
    public List<MedicalRecord> filterByDoctorPatientDate(Long docId, Long patId, LocalDate date) {
        return medicalRecordRepository.findByDoctorIdAndPatientIdAndDate(docId, patId, date);
    }
}

