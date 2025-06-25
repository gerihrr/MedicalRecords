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
        return appointmentRepository.create(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.update(appointment).orElseThrow(() ->
                new NonexisitingEntityException(
                        String.format("Cannot update non-existing appointment  id Id=%d",  appointment.getId())
                ));
    }

    @Override
    public Appointment deleteAppointmentById(Long id) {
        return appointmentRepository.deleteById(id).orElseThrow(() ->
                new NonexisitingEntityException(
                        String.format("Cannot update non-existing appointment with id Id=%d", id)
                ));
    }

    @Override
    public long countAppointments() {
        return appointmentRepository.count();
    }

    @Override
    public List<Appointment> filterByDoctorPatientDate(Long docId, Long patId, LocalDate date) {
        return appointmentRepository.findByDoctorIdPatientIdDate(docId, patId, date);
    }
}
