package medrecords.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Entity
public class Patient extends User{
    private String address;
    private LocalDate birthDate;
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Appointment> appointments = Collections.emptyList();
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<MedicalRecord> medicalRecords = Collections.emptyList();
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Prescription> prescriptions = Collections.emptyList();

    public Patient() {
        super();
    }

    public Patient(String address, LocalDate birthDate) {
        this();
        this.address = address;
        this.birthDate = birthDate;
    }

    public Patient(String username, String password, String firstName, String lastName, String phoneNumber, String address, LocalDate birthDate) {
        super(username, password, firstName, lastName, phoneNumber);
        this.address = address;
        this.birthDate = birthDate;
    }

    public Patient(Long id, String username, String password, String firstName, String lastName, String phoneNumber, String address, LocalDate birthDate) {
        super(id, username, password, firstName, lastName, phoneNumber);
        this.address = address;
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
        final StringBuilder sb = new StringBuilder("Patient{");
        sb.append("FirstName='").append(getFirstName()).append('\'');
        sb.append("LastName='").append(getLastName()).append('\'');
        sb.append("address='").append(address).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append('}');
        return sb.toString();
    }
}
