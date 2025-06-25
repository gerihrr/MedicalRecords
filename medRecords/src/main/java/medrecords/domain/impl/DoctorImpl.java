package medrecords.domain.impl;

import medrecords.dao.DoctorRepository;
import medrecords.domain.DoctorService;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.Doctor;
import medrecords.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorImpl implements DoctorService {
private DoctorRepository doctorRepository;

    @Autowired
    public DoctorImpl (DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    @Override
    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findDoctorById (Long id) {
            return doctorRepository.findById(id).orElseThrow(() -> new NonexisitingEntityException(
                    String.format("User with ID='%s' does not exist.", id)
            ));
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.create(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
         return doctorRepository.update(doctor).orElseThrow(() ->
                new NonexisitingEntityException(
                        String.format("Cannot update non-existing user '%s' with id Id=%d", doctor.getUsername(), doctor.getId())
                ));
    }

    @Override
    public Doctor deleteDoctorById(Long id) {
        return doctorRepository.deleteById(id).orElseThrow(() ->
                new NonexisitingEntityException(
                        String.format("Cannot update non-existing with id Id=%d", id)
                ));
    }

    @Override
    public long countDoctors() {
        return doctorRepository.count();
    }
}
