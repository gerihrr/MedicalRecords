package medrecords.domain.impl;

import medrecords.dao.DoctorRepository;
import medrecords.domain.DoctorService;
import medrecords.exception.InvalidEntityDataException;
import medrecords.exception.NonexisitingEntityException;
import medrecords.model.Doctor;
import medrecords.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional


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
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        doctorRepository.findById(doctor.getId());
         return doctorRepository.save(doctor);
    }

    @Override
    public Doctor deleteDoctorById(Long id) {
        var old = findDoctorById(id);
        if(old.getAppointments().isEmpty() && old.getMedicalRecords().isEmpty() && old.getPrescriptions().isEmpty()){
            doctorRepository.deleteById(id);
            return old;
        }
        else {
            throw new InvalidEntityDataException(String.format("There are documents associated with this doctor."));
        }

    }

    @Override
    public List<Doctor> findDoctorBySpeciality(String speciality) {
        return doctorRepository.findBySpecialityContainingIgnoreCase(speciality);
    }


    @Override
    public long countDoctors() {
        return doctorRepository.count();
    }
}
