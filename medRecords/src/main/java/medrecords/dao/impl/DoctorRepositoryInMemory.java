package medrecords.dao.impl;

import medrecords.dao.DoctorRepository;
import medrecords.dao.IdGenerator;
import medrecords.model.Doctor;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorRepositoryInMemory extends RepositoryInMemory<Long, Doctor> implements DoctorRepository {
    public DoctorRepositoryInMemory(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }
}
