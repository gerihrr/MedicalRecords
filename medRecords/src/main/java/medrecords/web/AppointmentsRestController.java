package medrecords.web;

import medrecords.dao.AppointmentRepository;
import medrecords.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentsRestController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping
    public List<Appointment> getAppointments(
            @RequestParam(value = "doctor", required = false) Long doctor,
            @RequestParam(value = "patient", required = false) Long patient,
            @RequestParam(value = "date", required = false) LocalDate date) {

        List<Appointment> appointments = appointmentRepository.findAll();

        if (doctor != null) {
            appointments = appointments.stream()
                    .filter(a -> doctor.equals(a.getDoctorId()))
                    .toList();
        }

        if (patient != null) {
            appointments = appointments.stream()
                    .filter(a -> patient.equals(a.getPatientId()))
                    .toList();
        }

        if (date != null) {
            appointments = appointments.stream()
                    .filter(a -> date.equals(a.getDate()))
                    .toList();
        }

        return appointments;
    }

    @GetMapping("/{id}")
    public Optional<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentRepository.findById(id);
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentRepository.create(appointment);
    }

    @PutMapping("/{id}")
    public Optional<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment updated) {
        updated.setId(id);
        return appointmentRepository.update(updated);
    }

    @DeleteMapping("/{id}")
    public Optional<Appointment> deleteAppointment(@PathVariable Long id) {
        return appointmentRepository.deleteById(id);
    }
}
