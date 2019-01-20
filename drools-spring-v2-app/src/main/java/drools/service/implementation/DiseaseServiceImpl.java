package drools.service.implementation;

import drools.model.*;
import drools.model.dto.DiagnosePatientDTO;
import drools.repository.DiseaseRepository;
import drools.repository.PatientRepository;
import drools.service.DiseaseService;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

        kieSession.insert(new Salience());
        kieSession.insert(new DateChecker());

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
        release(kieSession);
        return e.getDisease();
    }

    @Override
    public List<Disease> getAll() {
        return diseaseRepository.findAll();
    }

    @Override
    public Disease getDiseaseByName(String name) {
        return diseaseRepository.findOneByDisease(name);
    }

    @Override
    public List<Disease> findPotentialDiseasesSorted(List<Symptom> symptomList, KieSession kieSession) {
        for (int i = 0; i  < symptomList.size(); i++){
            System.out.println("Simptom: " + symptomList.get(i));
        }
        QueryResults results = kieSession.getQueryResults( "Potencijalne bolesti sortirane po simptomima", new Object[] { symptomList } );
        System.out.println( "Pronadjeno: " + results.size() +" bolesti!");

        Map<Disease, Long> map = new HashMap<>();
        for ( QueryResultsRow row : results ) {
            Disease disease = ( Disease ) row.get("disease");
            Long num = (Long) row.get("sum");
            map.put(disease, num);
        }

        Map<Disease, Long> result = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        for (Disease d : result.keySet()){
            System.out.println(d);
        }
        release(kieSession);
        return null;
    }

    public void release(KieSession kieSession) {
        for (Object object : kieSession.getObjects()) {
            if (!object.getClass().equals(Disease.class))
                kieSession.delete(kieSession.getFactHandle(object));
        }
    }
}
