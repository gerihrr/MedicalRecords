package medrecords.web;

import medrecords.dao.AppointmentRepository;
import medrecords.domain.AppointmentsService;
import medrecords.dto.ErrorResponse;
import medrecords.exception.InvalidEntityDataException;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentsRestController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentsService appointmentsService;

    @GetMapping("/filter")
    public List<Appointment> getAppointments(
            @RequestParam(value = "doctor", required = false) Long doctor,
            @RequestParam(value = "patient", required = false) Long patient,
            @RequestParam(value = "date", required = false) LocalDate date) {

        return appointmentsService.filterByDoctorPatientDate(doctor,patient,date);
    }

    @GetMapping()
    public List<Appointment> getAllAppointments() {
        return appointmentsService.findAllAppointments();
    }

    @GetMapping("/count")
    public long getAppointmentsCount() {
        return appointmentsService.countAppointments();
    }

    @GetMapping("/{id:\\d+}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentsService.findAppointmentById(id);
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentsService.createAppointment(appointment);
    }

    @PutMapping("/{id:\\d+}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment updated) {
        if (!id.equals(updated.getId())) {
            throw new InvalidEntityDataException(
                    String.format("Non-matching IDs in path '%s' and in body '%s'", id, updated.getId())
            );
        }
        return appointmentsService.updateAppointment(updated);
    }

    @DeleteMapping("/{id:\\d+}")
    public Appointment deleteAppointment(@PathVariable Long id) {
        return appointmentsService.deleteAppointmentById(id);
    }



    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEntityNotFound(NonexisitingEntityException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage()));

    };

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidRequestData (InvalidEntityDataException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage(), ex.getViolations()));

    };
}
