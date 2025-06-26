package medrecords.init;

import medrecords.dao.*;
import medrecords.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DbInitializer implements ApplicationRunner {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;



    private static final List<Doctor> DOCTORS = List.of(
            new Doctor("drIvanov", "par123", "Hristo", "Ivanov", "Cardiology", "Doctor", "+35988887777"),
            new Doctor("drPetrov", "par1234", "Mitko", "Petrov", "Family medicine", "Doctor", "+3598888766")
    );

    private static final List<Patient> PATIENTS = List.of(
            new Patient("mariap", "m123", "Maria", "Panayotova", "+359666666", "Sofia", LocalDate.of(1985, 02, 03)),
            new Patient("getiT", "g123", "Gergana", "Tihomirova", "+359665446", "Sofia", LocalDate.of(1995, 12, 13))
    );

    private static final List<MedicalRecord> RECORDS = List.of(
            new MedicalRecord(LocalDate.of(2025,06,23), 1L, 1L, "cardiological problem", "medicine"),
            new MedicalRecord(LocalDate.of(2025, 06, 22), 2L, 1L, "flu", "medicine")
    );

    private static final List<Appointment> APPOINTMENTS = List.of(
            new Appointment( LocalDate.of(2025, 06, 23), "13:00", 1L, 1L)
    );

    private static final List<Prescription> PRESCRIPTIONS = List.of(
            new Prescription( "Mixodil", "1 week", LocalDate.of(2025, 06, 22), 1L, 1L)
    );


    @Override
    public void run(ApplicationArguments args) throws Exception {
        DOCTORS.forEach(doctorRepository::save);
        PATIENTS.forEach(patientRepository::save);
        RECORDS.forEach(medicalRecordRepository::save);
        PRESCRIPTIONS.forEach(prescriptionRepository::save);
        APPOINTMENTS.forEach(appointmentRepository::save);
    }
}
