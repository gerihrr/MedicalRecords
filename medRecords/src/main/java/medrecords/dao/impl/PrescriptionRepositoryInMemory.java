package medrecords.dao.impl;

import medrecords.dao.IdGenerator;
import medrecords.dao.PrescriptionRepository;
import medrecords.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public class PrescriptionRepositoryInMemory extends RepositoryInMemory<Long, Prescription>
        implements PrescriptionRepository {

    @Autowired
    public PrescriptionRepositoryInMemory(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }


    @Override
    public List<Prescription> findByDoctorId(Long id) {
        var allPrescriptions =  findAll();
        var filtered = allPrescriptions.stream()
                .filter(a -> a.getDoctorId().equals(id))
                .toList();
        return filtered;
    }

    @Override
    public List<Prescription> findByPatientId(Long id) {
        var allPrescriptions =  findAll();
        var filtered = allPrescriptions.stream()
                .filter(a -> a.getPatientId().equals(id))
                .toList();
        return filtered;
    }

    @Override
    public List<Prescription> findByDate(LocalDate date) {
        var allPrescriptions =  findAll();
        var filtered = allPrescriptions.stream()
                .filter(a -> a.getDate().equals(date))
                .toList();
        return filtered;
    }
}
