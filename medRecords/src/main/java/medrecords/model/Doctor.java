package medrecords.model;

public class Doctor extends User{
    private String speciality;
    private String title;


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
}
