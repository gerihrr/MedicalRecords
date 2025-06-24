package medrecords.dao.impl;

import medrecords.dao.AppointmentRepository;
import medrecords.dao.IdGenerator;
import medrecords.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public class AppointmentRepositoryInMemory extends RepositoryInMemory<Long, Appointment>
        implements AppointmentRepository {
    @Autowired
    public AppointmentRepositoryInMemory(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }

    @Override
    public List<Appointment> findByDoctorId(Long id) {
        var all =  findAll();
        var filtered = all.stream()
                .filter(a -> a.getDoctorId().equals(id))
                .toList();
        return filtered;
    }

    @Override
    public List<Appointment> findByPatientId(Long id) {
        var all =  findAll();
        var filtered = all.stream()
                .filter(a -> a.getPatientId().equals(id))
                .toList();
        return filtered;
    }

    @Override
    public List<Appointment> findByDate(LocalDate date) {
        var all =  findAll();
        var filtered = all.stream()
                .filter(a -> a.getDate().equals(date))
                .toList();
        return filtered;
    }
}
