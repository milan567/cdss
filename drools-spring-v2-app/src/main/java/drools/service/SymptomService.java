package drools.service;

import drools.model.Symptom;

public interface SymptomService {
    Symptom save(Symptom s);

    Symptom findByName(String s);
}
