package medrecords.web;

import medrecords.dao.MedicalRecordRepository;
import medrecords.domain.MedicalRecordsService;
import medrecords.dto.ErrorResponse;
import medrecords.exception.InvalidEntityDataException;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.Appointment;
import medrecords.model.MedicalRecord;
import medrecords.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/records")
public class MedicalRecordsRestController {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    
    @Autowired
    private  MedicalRecordsService medicalRecordsService;

    @GetMapping("/filter")
    public List<MedicalRecord> getMdicalRecords(
            @RequestParam(value = "doctor", required = false) Long doctor,
            @RequestParam(value = "patient", required = false) Long patient,
            @RequestParam(value = "date", required = false) LocalDate date) {

        return medicalRecordsService.filterByDoctorPatientDate(doctor,patient,date);
    }

    @GetMapping
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecordsService.findAllMedicalRecords();
    }

    @GetMapping("/count")
    public long getMedicalRecordsCount() {
        return medicalRecordsService.countMedicalRecords();
    }

    @GetMapping("/{id:\\d+}")
    public MedicalRecord getMedicalRecordById(@PathVariable Long id) {
        return medicalRecordsService.findMedicalRecordById(id);
    }

    @PostMapping
    public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordsService.createMedicalRecord(medicalRecord);
    }

    @PutMapping("/{id:\\d+}")
    public MedicalRecord updateMedicalRecord(@PathVariable Long id, @RequestBody MedicalRecord updated) {
        if (!id.equals(updated.getId())) {
            throw new InvalidEntityDataException(
                    String.format("Non-matching IDs in path '%s' and in body '%s'", id, updated.getId())
            );
        }
        return medicalRecordsService.updateMedicalRecord(updated);
    }

    @DeleteMapping("/{id:\\d+}")
    public MedicalRecord deleteMedicalRecord(@PathVariable Long id) {
        return medicalRecordsService.deleteMedicalRecordById(id);
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
