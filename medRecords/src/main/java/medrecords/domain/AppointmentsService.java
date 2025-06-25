package medrecords.domain;

import medrecords.model.Appointment;
import medrecords.model.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentsService{
    List<Appointment> findAllAppointments();
    Appointment findAppointmentById(Long id);
    Appointment createAppointment(Appointment appointment);
    Appointment updateAppointment(Appointment appointment);
    Appointment deleteAppointmentById(Long id);
    long countAppointments();
    List<Appointment> filterByDoctorPatientDate(Long docId, Long patId,LocalDate date);

}
