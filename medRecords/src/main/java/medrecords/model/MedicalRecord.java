package medrecords.model;

import java.time.LocalDate;

public class MedicalRecord implements  Identifiable<Long>{
    private Long id;
    private LocalDate date;
    private Long patientId;
    private Long doctorId;
    private String description;
    private String treatment;

    public MedicalRecord() {
    }

    public MedicalRecord(LocalDate date, Long patientId, Long doctorId, String description, String treatment) {
        this();
        this.date = date;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.description = description;
        this.treatment = treatment;
    }

    public MedicalRecord(Long id, LocalDate date, Long patientId, Long doctorId, String description, String treatment) {
        this(date, patientId, doctorId, description, treatment);
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
}
