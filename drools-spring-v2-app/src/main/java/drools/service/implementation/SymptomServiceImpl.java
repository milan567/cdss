package drools.service.implementation;

import drools.model.Symptom;
import drools.repository.SymptomRepository;
import drools.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SymptomServiceImpl implements SymptomService {

    @Autowired
    SymptomRepository symptomRepository;


    @Override
    public  Symptom findByName(String s){
        return symptomRepository.findByText(s);
    }

    @Override
    public List<Symptom> findAllSymptoms() {
        return symptomRepository.findAll();
    }

    @Override
    public Symptom save(Symptom s) {
        return symptomRepository.save(s);
    }
}
