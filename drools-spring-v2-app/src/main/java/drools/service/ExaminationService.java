package drools.service;

import drools.model.Examination;
import drools.model.User;
import drools.model.dto.AddExaminationDTO;
import org.kie.api.runtime.KieSession;

public interface ExaminationService {

    Examination saveExamination(Examination ex);

    Examination saveExamination(AddExaminationDTO addExaminationDTO, User user);
}
