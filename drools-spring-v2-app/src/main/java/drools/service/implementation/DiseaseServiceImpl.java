package drools.service.implementation;

import drools.model.Disease;
import drools.model.Examination;
import drools.model.Patient;
import drools.model.Salience;
import drools.model.dto.DiagnosePatientDTO;
import drools.repository.DiseaseRepository;
import drools.repository.PatientRepository;
import drools.service.DiseaseService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    DiseaseRepository diseaseRepository;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Disease save(Disease d)
    {
        return diseaseRepository.save(d);
    }

    @Override
    public Disease diagnose(DiagnosePatientDTO diagnosePatientDTO, KieSession kieSession) {
        List<Disease> diseases = diseaseRepository.findAll();
        for (int i = 0; i <diseases.size(); i++) {
            kieSession.insert(diseases.get(i));
        }
        for (int i = 0; i < diagnosePatientDTO.getSymptoms().size(); i++) {
            kieSession.insert(diagnosePatientDTO.getSymptoms().get(i));
        }
        Salience s = new Salience();
        kieSession.insert(s);

        Patient p = patientRepository.getOne(Integer.parseInt(diagnosePatientDTO.getId()));
        kieSession.insert(p);
        for (int i = 0; i < p.getExaminations().size(); i++){
            kieSession.insert(p.getExaminations().get(i));
        }
        Examination e = new Examination();
        e.setDisease(null);
        e.setSymptoms(diagnosePatientDTO.getSymptoms());
        kieSession.insert(e);
        kieSession.getAgenda().getAgendaGroup("diseases-group").setFocus();
        kieSession.fireAllRules();
        System.out.println("Output: " + e.getDisease());
        return e.getDisease();
    }

    @Override
    public List<Disease> getAll() {
        return diseaseRepository.findAll();
    }
}
