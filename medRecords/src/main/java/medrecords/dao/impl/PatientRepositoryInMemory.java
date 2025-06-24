package medrecords.dao.impl;

import medrecords.dao.IdGenerator;
import medrecords.dao.PatientRepository;
import medrecords.model.Patient;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepositoryInMemory extends RepositoryInMemory<Long, Patient> implements PatientRepository {
    public PatientRepositoryInMemory(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }
}
