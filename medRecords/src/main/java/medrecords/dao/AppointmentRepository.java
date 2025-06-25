package medrecords.dao;

import medrecords.model.Appointment;
import medrecords.model.Prescription;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends Repository<Long, Appointment> {
    List<Appointment> findByDoctorIdPatientIdDate(Long docId, Long patientId, LocalDate date);

}
