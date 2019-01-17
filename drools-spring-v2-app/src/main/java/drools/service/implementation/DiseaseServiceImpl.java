package drools.service.implementation;

import drools.model.Disease;
import drools.repository.DiseaseRepository;
import drools.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    DiseaseRepository diseaseRepository;

    @Override
    public Disease save(Disease d)
    {
        return diseaseRepository.save(d);
    }
}
