package medrecords.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Prescription implements Identifiable<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String duraton;
    private LocalDate date;
    private Long doctorId;
    private Long patientId;

    public Prescription() {
    }

    public Prescription(String description, String duraton, LocalDate date, Long doctorId, Long patientId) {
        this();
        this.description = description;
        this.duraton = duraton;
        this.date = date;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public Prescription(Long id, String description, String duraton, LocalDate date, Long doctorId, Long patientId) {
        this(description, duraton, date, doctorId, patientId);
        this.id = id;
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

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
