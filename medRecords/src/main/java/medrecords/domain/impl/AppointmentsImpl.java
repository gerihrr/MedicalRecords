package medrecords.domain.impl;

import medrecords.dao.AppointmentRepository;
import medrecords.dao.DoctorRepository;
import medrecords.dao.PatientRepository;
import medrecords.dao.AppointmentRepository;
import medrecords.domain.AppointmentsService;
import medrecords.exception.InvalidEntityDataException;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.Appointment;
import medrecords.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional

public class AppointmentsImpl implements AppointmentsService {


    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    @Autowired
    public AppointmentsImpl(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
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
        if(appointment.getDoctor() == null || appointment.getDoctor().getId() ==null) {
            throw new InvalidEntityDataException(String.format("Doctor with valid user ID is required"));
        }
        var doctor = doctorRepository.findById(appointment.getDoctor().getId()).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Doctor does not exist")));


        if(appointment.getPatient() == null || appointment.getPatient().getId() ==null) {
            throw new InvalidEntityDataException(String.format("Patient with valid user ID is required"));
        }
        var patient = patientRepository.findById(appointment.getPatient().getId()).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Patient does not exist")));

        appointment.setId(null);
        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment);
        appointment.setPatient(patient);
        patient.getAppointments().add(appointment);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        var old = findAppointmentById(appointment.getId());

        if(appointment.getDoctor() == null || appointment.getDoctor().getId() ==null) {
            throw new InvalidEntityDataException(String.format("Doctor with valid user ID is required"));
        }
        var doctor = doctorRepository.findById(appointment.getDoctor().getId()).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Doctor does not exist")));


        if(appointment.getPatient() == null || appointment.getPatient().getId() ==null) {
            throw new InvalidEntityDataException(String.format("Patient with valid user ID is required"));
        }
        var patient = patientRepository.findById(appointment.getPatient().getId()).orElseThrow(() ->
                new InvalidEntityDataException(String.format("Patient does not exist")));

        if(!(old.getDoctor().equals(appointment.getDoctor()) && old.getPatient().equals(appointment.getPatient()))){
            throw new InvalidEntityDataException(String.format("Patient and Doctor data cannot be changed"));
        }

        appointment.setDate(appointment.getDate());
        appointment.setHour(appointment.getHour());

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment deleteAppointmentById(Long id) {
        var old = findAppointmentById(id);
        old.getDoctor().getAppointments().remove(old);
        old.getPatient().getAppointments().remove(old);
        appointmentRepository.deleteById(id);

        return old;
    }

    @Override
    public long countAppointments() {
        return appointmentRepository.count();
    }

    @Override
    public List<Appointment> filterByDoctorPatientDate(Long docId, Long patId, LocalDate date) {
        if(docId!=null){
            doctorRepository.findById(docId).orElseThrow(() ->
                    new InvalidEntityDataException(String.format("Doctor does not exist")));
        }



        if(patId!=null){
            patientRepository.findById(patId).orElseThrow(() ->
                    new InvalidEntityDataException(String.format("Patient does not exist")));
        }


        return appointmentRepository.findAllByDoctor_IdAndPatient_IdAndDate(docId, patId, date);
    }
}
