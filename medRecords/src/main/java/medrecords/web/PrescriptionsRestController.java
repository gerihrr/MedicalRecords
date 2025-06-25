package medrecords.web;

import medrecords.dao.PrescriptionRepository;
import medrecords.domain.PrescriptionService;
import medrecords.dto.ErrorResponse;
import medrecords.exception.InvalidEntityDataException;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.Appointment;
import medrecords.model.MedicalRecord;
import medrecords.model.Prescription;
import medrecords.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionsRestController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;
    
    @Autowired
    private PrescriptionService prescriptionsService;

    @GetMapping("/filter")
    public List<Prescription> getPrescription(
            @RequestParam(value = "doctor", required = false) Long doctor,
            @RequestParam(value = "patient", required = false) Long patient,
            @RequestParam(value = "date", required = false) LocalDate date) {

        return prescriptionsService.filterByDoctorPatientDate(doctor,patient,date);
    }
    @GetMapping
    public List<Prescription> getPrescriptions() {
        return prescriptionsService.findAllPrescriptions();
    }

    @GetMapping("/count")
    public long getPrescriptionsCount() {
        return prescriptionsService.countPrescriptions();
    }

    @GetMapping("/{id:\\d+}")
    public Prescription getPrescriptionById(@PathVariable Long id) {
        return prescriptionsService.findPrescriptionById(id);
    }

    @PostMapping
    public Prescription createPrescription(@RequestBody Prescription prescription) {
        return prescriptionsService.createPrescription(prescription);
    }

    @PutMapping("/{id:\\d+}")
    public Prescription updatePrescription(@PathVariable Long id, @RequestBody Prescription updated) {
        if (!id.equals(updated.getId())) {
            throw new InvalidEntityDataException(
                    String.format("Non-matching IDs in path '%s' and in body '%s'", id, updated.getId())
            );
        }
        return prescriptionsService.updatePrescription(updated);
    }

    @DeleteMapping("/{id:\\d+}")
    public Prescription deletePrescription(@PathVariable Long id) {
        return prescriptionsService.deletePrescriptionById(id);
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
