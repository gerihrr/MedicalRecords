package medrecords.dao.impl;

import medrecords.dao.IdGenerator;
import medrecords.dao.MedicalRecordRepository;
import medrecords.model.MedicalRecord;
import medrecords.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MedicalRecordRepositoryInMemory extends RepositoryInMemory<Long, MedicalRecord> implements MedicalRecordRepository {

    @Autowired
    public MedicalRecordRepositoryInMemory(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }

    @Override
    public List<MedicalRecord> findByDoctorIdPatientIdDate(Long docId, Long patientId, LocalDate date) {
        return findAll().stream()
                .filter(prescription -> docId == null || docId.equals(prescription.getDoctorId()))
                .filter(prescription -> patientId == null || patientId.equals(prescription.getPatientId()))
                .filter(prescription -> date == null || date.equals(prescription.getDate()))
                .collect(Collectors.toList());
    }
}
