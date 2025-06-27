package medrecords.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Prescription implements Identifiable<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String duraton;
    private LocalDate date;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;

    public Prescription() {
    }

    public Prescription(Long id) {
        this.id = id;
    }

    public Prescription(String description, String duraton, LocalDate date) {
        this.description = description;
        this.duraton = duraton;
        this.date = date;
    }

    public Prescription(String description, String duraton, LocalDate date, Patient patient, Doctor doctor) {
        this.description = description;
        this.duraton = duraton;
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Prescription(Long id, String description, String duraton, LocalDate date, Patient patient, Doctor doctor) {
        this.id = id;
        this.description = description;
        this.duraton = duraton;
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuraton() {
        return duraton;
    }

    public void setDuraton(String duraton) {
        this.duraton = duraton;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
        if (!(o instanceof Prescription that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
