package drools.service;

import drools.model.Disease;
import drools.model.Symptom;
import drools.model.dto.AddDiseaseDTO;
import drools.model.dto.DiagnosePatientDTO;
import drools.model.dto.PotentialDiseasesResponseDTO;
import org.kie.api.runtime.KieSession;

import java.util.List;

public interface DiseaseService {

    Disease save(Disease d);

    Disease diagnose(DiagnosePatientDTO diagnosePatientDTO, KieSession kieSesion);

    List<Disease> getAll();

    Disease getDiseaseByName(String name);

    List<PotentialDiseasesResponseDTO> findPotentialDiseasesSorted(List<Symptom> symptomList, KieSession kieSession);

    List<Disease> findAllDiseases();

    Disease findDisease(Integer id);

    Disease deleteSymptomFromDisease(Integer disease, Integer symptom);

    List<Disease> deleteDisease(Integer disease);

    Disease addDisease(AddDiseaseDTO addDiseaseDTO);

    Disease editDisease(AddDiseaseDTO addDiseaseDTO);

    List<Symptom> findDiseaseWithSortedSymptoms(Integer id, KieSession kieSession);
}
