package drools.service.implementation;

import drools.model.Examination;
import drools.repository.ExaminationRepository;
import drools.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    @Autowired
    ExaminationRepository examinationRepository;

    @Override
    public Examination saveExamination(Examination ex) {
        return examinationRepository.save(ex);
    }
}
