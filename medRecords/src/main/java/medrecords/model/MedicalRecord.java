package medrecords.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class MedicalRecord implements  Identifiable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String description;
    private String treatment;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;

    public MedicalRecord() {
    }

    public MedicalRecord(Long id) {
        this.id = id;
    }

    public MedicalRecord(LocalDate date, String description, String treatment) {
        this.date = date;
        this.description = description;
        this.treatment = treatment;
    }

    public MedicalRecord(LocalDate date, String description, String treatment, Patient patient, Doctor doctor) {
        this.date = date;
        this.description = description;
        this.treatment = treatment;
        this.patient = patient;
        this.doctor = doctor;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof MedicalRecord that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

