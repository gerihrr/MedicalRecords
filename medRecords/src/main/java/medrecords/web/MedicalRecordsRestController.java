package medrecords.web;

import medrecords.dao.MedicalRecordRepository;
import medrecords.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/records")
public class MedicalRecordsRestController {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @GetMapping
    public List<MedicalRecord> getRecords(
            @RequestParam(value = "doctor", required = false) Long doctor,
            @RequestParam(value = "patient", required = false) Long patient,
            @RequestParam(value = "date", required = false) LocalDate date) {

        List<MedicalRecord> records = medicalRecordRepository.findAll();

        if (doctor != null) {
            records = records.stream()
                    .filter(r -> doctor.equals(r.getDoctorId()))
                    .toList();
        }

        if (patient != null) {
            records = records.stream()
                    .filter(r -> patient.equals(r.getPatientId()))
                    .toList();
        }

        if (date != null) {
            records = records.stream()
                    .filter(r -> date.equals(r.getDate()))
                    .toList();
        }

        return records;
    }

    @GetMapping("/{id}")
    public Optional<MedicalRecord> getRecordById(@PathVariable Long id) {
        return medicalRecordRepository.findById(id);
    }

    @PostMapping
    public MedicalRecord createRecord(@RequestBody MedicalRecord record) {
        return medicalRecordRepository.create(record);
    }

    @PutMapping("/{id}")
    public Optional<MedicalRecord> updateRecord(@PathVariable Long id, @RequestBody MedicalRecord updated) {
        updated.setId(id);
        return medicalRecordRepository.update(updated);
    }

    @DeleteMapping("/{id}")
    public Optional<MedicalRecord> deleteRecord(@PathVariable Long id) {
        return medicalRecordRepository.deleteById(id);
    }
}
