package medrecords.web;

import medrecords.dao.DoctorRepository;
import medrecords.dao.PatientRepository;
import medrecords.domain.DoctorService;
import medrecords.domain.PatientService;
import medrecords.domain.impl.DoctorImpl;
import medrecords.dto.ErrorResponse;
import medrecords.exception.InvalidEntityDataException;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.Doctor;
import medrecords.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersRestController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.findAllDoctors();
    }

    @GetMapping("/doctors/count")
    public long getCount() {
        return doctorService.countDoctors();
    }

    @GetMapping("/doctors/{id:\\d+}")
    public Doctor getDoctorById(@PathVariable Long id) {
    return doctorService.findDoctorById(id);
    }

    @GetMapping("doctors/speciality/{speciality}")
    public List<Doctor> getDoctorBySpeciality(@PathVariable String speciality) {

        return doctorService.findDoctorBySpeciality(speciality);
    }

    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @PutMapping("/doctors/{id:\\d+}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor updated) {
        if (!id.equals(updated.getId())) {
            throw new InvalidEntityDataException(
                    String.format("Non-matching IDs in path '%s' and in body '%s'", id, updated.getId())
            );
        }
        return doctorService.updateDoctor(updated);
    }

    @DeleteMapping("/doctors/{id:\\d+}")
    public Doctor deleteDoctor(@PathVariable Long id) {
        return doctorService.deleteDoctorById(id);
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.findAllPatients();
    }

    @GetMapping("/patients/count")
    public long getCountPatients() {
        return patientService.countPatients();
    }

    @GetMapping("/patients/{id:\\d+}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.findPatientById(id);
    }

    @PostMapping("/patients")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @PutMapping("/patients/{id:\\d+}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient updated) {
        if (!id.equals(updated.getId())) {
            throw new InvalidEntityDataException(
                    String.format("Non-matching IDs in path '%s' and in body '%s'", id, updated.getId())
            );
        }
        return patientService.updatePatient(updated);
    }

    @DeleteMapping("/patients/{id:\\d+}")
    public Patient deletePatient(@PathVariable Long id) {
        return patientService.deletePatientById(id);
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
