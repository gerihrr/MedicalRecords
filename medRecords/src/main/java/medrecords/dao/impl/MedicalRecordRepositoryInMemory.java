package medrecords.dao.impl;

import medrecords.dao.IdGenerator;
import medrecords.dao.MedicalRecordRepository;
import medrecords.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public class MedicalRecordRepositoryInMemory extends RepositoryInMemory<Long, MedicalRecord> implements MedicalRecordRepository {

    @Autowired
    public MedicalRecordRepositoryInMemory(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }

    @Override
    public List<MedicalRecord> findByDoctorId(Long id) {
        var all =  findAll();
        var filtered = all.stream()
                .filter(a -> a.getDoctorId().equals(id))
                .toList();
        return filtered;
    }

    @Override
    public List<MedicalRecord> findByPatientId(Long id) {
        var all =  findAll();
        var filtered = all.stream()
                .filter(a -> a.getPatientId().equals(id))
                .toList();
        return filtered;
    }

    @Override
    public List<MedicalRecord> findByDate(LocalDate date) {
        var all =  findAll();
        var filtered = all.stream()
                .filter(a -> a.getDate().equals(date))
                .toList();
        return filtered;
    }
}
