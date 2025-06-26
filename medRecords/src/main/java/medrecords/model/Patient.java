package medrecords.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Patient extends User{
    private String address;
    private LocalDate birthDate;

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
}
