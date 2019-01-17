package drools.service;

import drools.model.Symptom;

import java.util.List;

public interface SymptomService {
    Symptom save(Symptom s);

    Symptom findByName(String s);

    List<Symptom> findAllSymptoms();
}
