package medrecords.domain.impl;

import medrecords.dao.PrescriptionRepository;
import medrecords.domain.PrescriptionService;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.MedicalRecord;
import medrecords.model.Prescription;
import medrecords.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionImpl implements PrescriptionService {

    private PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }


    @Override
    public List<Prescription> findAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @Override
    public Prescription findPrescriptionById (Long id) {
        return prescriptionRepository.findById(id).orElseThrow(() -> new NonexisitingEntityException(
                String.format("Prescription with ID='%s' does not exist.", id)
        ));
    }

    @Override
    public Prescription createPrescription(Prescription prescription) {
        return prescriptionRepository.create(prescription);
    }

    @Override
    public Prescription updatePrescription(Prescription prescription) {
        return prescriptionRepository.update(prescription).orElseThrow(() ->
                new NonexisitingEntityException(
                        String.format("Cannot update non-existing prescription  id Id=%d",  prescription.getId())
                ));
    }

    @Override
    public Prescription deletePrescriptionById(Long id) {
        return prescriptionRepository.deleteById(id).orElseThrow(() ->
                new NonexisitingEntityException(
                        String.format("Cannot update non-existing prescription with id Id=%d", id)
                ));
    }

    @Override
    public long countPrescriptions() {
        return prescriptionRepository.count();
    }

    @Override
    public List<Prescription> filterByDoctorPatientDate(Long docId, Long patId, LocalDate date) {
        return prescriptionRepository.findByDoctorIdPatientIdDate(docId, patId, date);
    }
}
