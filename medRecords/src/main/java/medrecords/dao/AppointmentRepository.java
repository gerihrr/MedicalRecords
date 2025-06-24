package medrecords.dao;

import medrecords.model.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends Repository<Long, Appointment> {
    List<Appointment> findByDoctorId(Long id);
    List<Appointment> findByPatientId(Long id);
    List<Appointment> findByDate(LocalDate date);
}
