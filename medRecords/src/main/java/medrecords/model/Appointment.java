package medrecords.model;

import java.time.LocalDate;

public class Appointment implements Identifiable<Long>{
    private Long id;
    private LocalDate date;
    private String hour;
    private Long patientId;
    private Long doctorId;

    public Appointment() {
    }

    public Appointment(LocalDate date, String hour, Long patientId, Long doctorId) {
        this();
        this.date = date;
        this.hour = hour;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public Appointment(Long id, LocalDate date, String hour, Long patientId, Long doctorId) {
        this(date, hour, patientId, doctorId);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
