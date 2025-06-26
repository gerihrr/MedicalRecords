package medrecords.domain.impl;

import medrecords.dao.AppointmentRepository;
import medrecords.domain.AppointmentsService;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentsImpl implements AppointmentsService {

    private AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentsImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    @Override
    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment findAppointmentById (Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new NonexisitingEntityException(
                String.format("Appointment with ID='%s' does not exist.", id)
        ));
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        findAppointmentById(appointment.getId());
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment deleteAppointmentById(Long id) {
        var old = findAppointmentById(id);
        appointmentRepository.deleteById(id);
        return old;
    }

    @Override
    public long countAppointments() {
        return appointmentRepository.count();
    }

    @Override
    public List<Appointment> filterByDoctorPatientDate(Long docId, Long patId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndPatientIdAndDate(docId, patId, date);
    }
}
