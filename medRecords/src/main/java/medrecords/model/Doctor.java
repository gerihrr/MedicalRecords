package medrecords.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Collections;
import java.util.List;

@Entity
public class Doctor extends User{
    private String speciality;
    private String title;
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Appointment> appointments = Collections.emptyList();
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<MedicalRecord> medicalRecords = Collections.emptyList();
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Prescription> prescriptions = Collections.emptyList();



    public Doctor() {
        super();
    }

    public Doctor(String username, String password, String firstName, String lastName, String speciality, String title, String phoneNumber) {
        super(username, password, firstName, lastName, phoneNumber);
        this.speciality = speciality;
        this.title = title;
    }

    public Doctor(Long id, String username, String password, String firstName, String lastName, String speciality, String title, String phoneNumber) {
        super(id, username, password, firstName, lastName, phoneNumber);
        this.speciality = speciality;
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Doctor{");
        sb.append("firstName='").append(getFirstName()).append('\'');
        sb.append("lastName='").append(getLastName()).append('\'');
        sb.append("speciality='").append(speciality).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
