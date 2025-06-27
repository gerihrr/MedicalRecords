package medrecords.dao;

import medrecords.model.Appointment;
import medrecords.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByDoctor_IdAndPatient_IdAndDate(Long doctorId, Long patientId, LocalDate date);

}
