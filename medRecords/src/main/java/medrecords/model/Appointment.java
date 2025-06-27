package medrecords.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Appointment implements Identifiable<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String hour;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;

    public Appointment() {
    }

    public Appointment(Long id) {
        this.id = id;
    }

    public Appointment(LocalDate date, String hour, Patient patient, Doctor doctor) {
        this.date = date;
        this.hour = hour;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Appointment(LocalDate date, String hour) {
        this.date = date;
        this.hour = hour;
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
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
        if (!(o instanceof Appointment that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
