package drools.service;

import drools.model.Disease;
import drools.model.dto.DiagnosePatientDTO;
import org.kie.api.runtime.KieSession;

public interface DiseaseService {

    Disease save(Disease d);

    Disease diagnose(DiagnosePatientDTO diagnosePatientDTO, KieSession kieSesion);
}
