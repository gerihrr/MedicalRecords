package medrecords.web;

import medrecords.dao.DoctorRepository;
import medrecords.dao.PatientRepository;
import medrecords.dto.ErrorResponse;
import medrecords.exception.InvalidEntityDataException;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.Doctor;
import medrecords.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersRestController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @GetMapping("/doctors/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new NonexisitingEntityException(
                String.format("User with ID='%s' does not exist.", id)
        ));
    }

    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.create(doctor);
    }

    @PutMapping("/doctors/{id}")
    public Optional<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor updated) {
        if (!id.equals(updated.getId())) {
            throw new InvalidEntityDataException(
                    String.format("Non-matching IDs in path '%s' and in body '%s'", id, updated.getId())
            );
        }
        return doctorRepository.update(updated);
    }

    @DeleteMapping("/doctors/{id}")
    public Doctor deleteDoctor(@PathVariable Long id) {
        return doctorRepository.deleteById(id).orElseThrow(() -> new NonexisitingEntityException(
                String.format("User with ID='%s' does not exist.", id)
        ));
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new NonexisitingEntityException(
                String.format("User with ID='%s' does not exist.", id)
        ));
    }

    @PostMapping("/patients")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.create(patient);
    }

    @PutMapping("/patients/{id}")
    public Optional<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updated) {
        if (!id.equals(updated.getId())) {
            throw new InvalidEntityDataException(
                    String.format("Non-matching IDs in path '%s' and in body '%s'", id, updated.getId())
            );
        }
        return patientRepository.update(updated);
    }

    @DeleteMapping("/patients/{id}")
    public Optional<Patient> deletePatient(@PathVariable Long id) {
        return patientRepository.deleteById(id);
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
