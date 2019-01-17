package drools.service.implementation;

import drools.model.Symptom;
import drools.model.User;
import drools.model.dto.LoginRequestDTO;
import drools.repository.SymptomRepository;
import drools.repository.UserRepository;
import drools.service.SymptomService;
import drools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import drools.service.UserService;


@Service
public class SymptomServiceImpl implements SymptomService {

    @Autowired
    SymptomRepository symptomRepository;


    @Override
    public  Symptom findByName(String s){
        return symptomRepository.findByText(s);
    }

    @Override
    public Symptom save(Symptom s) {
        return symptomRepository.save(s);
    }
}
