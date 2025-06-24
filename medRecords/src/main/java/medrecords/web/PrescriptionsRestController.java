package medrecords.web;

import medrecords.dao.PrescriptionRepository;
import medrecords.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionsRestController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @GetMapping
    public List<Prescription> getPrescriptions(
            @RequestParam(value = "doctor", required = false) Long doctor,
            @RequestParam(value = "patient", required = false) Long patient,
            @RequestParam(value = "date", required = false) LocalDate date) {

        List<Prescription> prescriptions = prescriptionRepository.findAll();

        if (doctor != null) {
            prescriptions.retainAll(prescriptionRepository.findByDoctorId(doctor));
        }

        if (patient != null) {
            prescriptions.retainAll(prescriptionRepository.findByPatientId(patient));
        }

        if (date != null) {
            prescriptions.retainAll(prescriptionRepository.findByDate(date));
        }

        return prescriptions;
    }

    @GetMapping("/{id}")
    public Optional<Prescription> getPrescriptionById(@PathVariable Long id) {
        return prescriptionRepository.findById(id);
    }

    @PostMapping
    public Prescription createPrescription(@RequestBody Prescription prescription) {
        return prescriptionRepository.create(prescription);
    }

    @PutMapping("/{id}")
    public Optional<Prescription> updatePrescription(@PathVariable Long id, @RequestBody Prescription updated) {
        updated.setId(id);
        return prescriptionRepository.update(updated);
    }

    @DeleteMapping("/{id}")
    public Optional<Prescription> deletePrescription(@PathVariable Long id) {
        return prescriptionRepository.deleteById(id);
    }
}
