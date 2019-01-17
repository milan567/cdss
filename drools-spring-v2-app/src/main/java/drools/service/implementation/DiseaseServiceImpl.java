package drools.service.implementation;

import drools.model.Disease;
import drools.model.dto.DiagnosePatientDTO;
import drools.repository.DiseaseRepository;
import drools.service.DiseaseService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    DiseaseRepository diseaseRepository;

    @Override
    public Disease save(Disease d)
    {
        return diseaseRepository.save(d);
    }

    @Override
    public Disease diagnose(DiagnosePatientDTO diagnosePatientDTO, KieSession kieSession) {
        for (int i = 0; i < diagnosePatientDTO.getSymptoms().size(); i++) {
            System.out.println(diagnosePatientDTO.getSymptoms().get(i));
            kieSession.insert(diagnosePatientDTO.getSymptoms().get(i));
        }
        System.out.println("AAA");
        kieSession.setGlobal("patient", diagnosePatientDTO.getId());
        System.out.println("A");
        Disease first = new Disease("first",1);
        Disease second = new Disease("second", 2);
        Disease third = new Disease("third", 3);

        kieSession.insert(first);
        kieSession.insert(second);
        kieSession.insert(third);
        System.out.println("B");
        kieSession.fireAllRules();

        System.out.println("Output1: " + first.getDisease());
        System.out.println("Output2: " + second.getDisease());
        System.out.println("Output3: " + third.getDisease());
        return first;
    }
}
