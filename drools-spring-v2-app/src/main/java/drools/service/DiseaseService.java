package drools.service;

import drools.model.Disease;
import drools.model.Symptom;
import drools.model.dto.DiagnosePatientDTO;
import org.kie.api.runtime.KieSession;

import java.util.List;

public interface DiseaseService {

    Disease save(Disease d);

    Disease diagnose(DiagnosePatientDTO diagnosePatientDTO, KieSession kieSesion);

    List<Disease> getAll();

    Disease getDiseaseByName(String name);

    List<Disease> findPotentialDiseasesSorted(List<Symptom> symptomList, KieSession kieSession);
}
