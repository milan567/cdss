package drools.service.implementation;

import drools.model.Examination;
import drools.model.Patient;
import drools.model.User;
import drools.model.dto.AddExaminationDTO;
import drools.repository.ExaminationRepository;
import drools.repository.PatientRepository;
import drools.service.ExaminationService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    @Autowired
    ExaminationRepository examinationRepository;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Examination saveExamination(Examination ex) {
        return examinationRepository.save(ex);
    }

    @Override
    public Examination saveExamination(AddExaminationDTO addExaminationDTO, User user){
        Examination e = new Examination(new Date(),user,addExaminationDTO.getDisease()
                ,addExaminationDTO.getSymptoms(),addExaminationDTO.getMedications());
        e = examinationRepository.save(e);
        Patient p = patientRepository.getOne(Integer.parseInt(addExaminationDTO.getPatientId()));
        p.getExaminations().add(e);
        patientRepository.save(p);
        return e;
    }
}
