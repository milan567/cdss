package drools.service.implementation;

import drools.model.Patient;
import drools.repository.PatientRepository;
import drools.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient save(Patient p) {
        return patientRepository.save(p);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findPatient(Integer id) {
        return patientRepository.getOne(id);
    }


}
