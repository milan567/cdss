package drools.service.implementation;

import drools.model.Disease;
import drools.model.Ingredient;
import drools.model.Medication;
import drools.model.Patient;
import drools.model.dto.CheckAllergiesResponseDTO;
import drools.repository.MedicationRepository;
import drools.repository.PatientRepository;
import drools.service.MedicationService;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Medication save(Medication medication) {
        return medicationRepository.save(medication);
    }

    @Override
    public Medication findMedicationByText(String text) {
        return medicationRepository.findMedicationByText(text);
    }

    @Override
    public List<Medication> findAllMedications() {
        return medicationRepository.findAll();
    }

    @Override
    public CheckAllergiesResponseDTO checkAlergiest(Integer patientId, Integer medicationId, KieSession kieSession) {
        CheckAllergiesResponseDTO carDTO = new CheckAllergiesResponseDTO();

        kieSession.setGlobal("patientId", patientId);
        Patient p = patientRepository.getOne(patientId);
        if (p == null){
            return null;
        }
        kieSession.insert(p);

        Medication m = medicationRepository.getOne(medicationId);
        if (m == null){
            return null;
        }

        kieSession.insert(m);
        for (Ingredient i : m.getIngredients()) {
            kieSession.insert(i);
        }

        kieSession.getAgenda().getAgendaGroup("alergije").setFocus();
        kieSession.fireAllRules();
        Collection<String> allergies = (Collection<String>) kieSession
                .getObjects(new ClassObjectFilter(String.class));
        System.out.println("-------");
        Iterator<String> iter = allergies.iterator();
        int allergiesCount = 0;

        while (iter.hasNext()) {
            carDTO.getMessage().add("Korisnik je alergican:" + iter.next());
            allergiesCount+= 1;
        }

        boolean condition = false;
        if (allergiesCount == 0) {
            condition = true;
        }
        System.out.println(condition);
        release(kieSession);
        carDTO.setConditionSatisfied(condition);

        return carDTO;
    }

    public void release(KieSession kieSession) {
        for (Object object : kieSession.getObjects()) {
            if (!object.getClass().equals(Disease.class))
                kieSession.delete(kieSession.getFactHandle(object));
        }
    }
}

