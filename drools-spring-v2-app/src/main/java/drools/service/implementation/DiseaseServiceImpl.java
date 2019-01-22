package drools.service.implementation;

import drools.model.*;
import drools.model.dto.AddDiseaseDTO;
import drools.model.dto.DiagnosePatientDTO;
import drools.model.dto.PotentialDiseasesResponseDTO;
import drools.repository.DiseaseRepository;
import drools.repository.PatientRepository;
import drools.repository.SymptomRepository;
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

    @Autowired
    SymptomRepository symptomRepository;

    @Override
    public Disease save(Disease d)
    {
        return diseaseRepository.save(d);
    }

    @Override
    public Disease diagnose(DiagnosePatientDTO diagnosePatientDTO, KieSession kieSession) {
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
        kieSession.getAgenda().getAgendaGroup("bolesti").setFocus();
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
    public List<PotentialDiseasesResponseDTO> findPotentialDiseasesSorted(List<Symptom> symptomList, KieSession kieSession) {
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
        List<PotentialDiseasesResponseDTO> response = new ArrayList<>();
        System.out.println("-------------------");
        for (Disease d : result.keySet()){
            System.out.println(d);
            PotentialDiseasesResponseDTO pdrDTO = new PotentialDiseasesResponseDTO(d,Integer.parseInt(result.get(d).toString()));
            response.add(pdrDTO);
        }
        System.out.println("------------------");
        release(kieSession);
        return response;
    }

    @Override
    public List<Disease> findAllDiseases() {
        return diseaseRepository.findAll();
    }

    @Override
    public Disease findDisease(Integer id) {
        return diseaseRepository.getOne(id);
    }

    @Override
    public Disease deleteSymptomFromDisease(Integer disease, Integer symptom) {
        Disease d =  diseaseRepository.getOne(disease);
        Symptom s = symptomRepository.getOne(symptom);
        d.getSymptoms().remove(s);
        System.out.println(d);
        d = diseaseRepository.save(d);
        return d;
    }

    @Override
    public List<Disease> deleteDisease(Integer disease) {
        Disease d =  diseaseRepository.getOne(disease);
        diseaseRepository.delete(d);
        return diseaseRepository.findAll();
    }

    @Override
    public Disease addDisease(AddDiseaseDTO addDiseaseDTO) {
        Disease disease = new Disease();
        disease.setDisease(addDiseaseDTO.getDisease());
        disease.setGroup(addDiseaseDTO.getGroup());
        disease.setSymptoms(addDiseaseDTO.getSymptoms());
        return diseaseRepository.save(disease);
    }

    @Override
    public Disease editDisease(AddDiseaseDTO addDiseaseDTO) {
        Disease d = diseaseRepository.getOne(Integer.parseInt(addDiseaseDTO.getId()));
        d.setSymptoms(addDiseaseDTO.getSymptoms());
        d.setGroup(addDiseaseDTO.getGroup());
        d.setDisease(addDiseaseDTO.getDisease());
        return diseaseRepository.save(d);
    }

    @Override
    public List<Symptom> findDiseaseWithSortedSymptoms(Integer id, KieSession kieSession) {

        Disease disease = diseaseRepository.getOne(id);
        if (disease == null){
            return null;
        }

        QueryResults results = kieSession.getQueryResults("Simptomi bolesti sortirani po specificnosti"
                , new Object[] { disease });

        ArrayList<Symptom> symptoms = new ArrayList<>();

        for (QueryResultsRow row : results) {
            Collection<Symptom> nonSpecificSymptoms = (Collection<Symptom>) row.get("nonSpecificSymptoms");
            Collection<Symptom> specificSymptoms = (Collection<Symptom>) row.get("specificSymptoms");
            for (Symptom s : specificSymptoms) {
                symptoms.add(s);
            }
            for (Symptom s : nonSpecificSymptoms) {
                symptoms.add(s);
            }
        }

        for (Symptom s: symptoms){
        }
        return symptoms;
    }

    public void release(KieSession kieSession) {
        for (Object object : kieSession.getObjects()) {
            if (!object.getClass().equals(Disease.class))
                kieSession.delete(kieSession.getFactHandle(object));
        }
    }
}
