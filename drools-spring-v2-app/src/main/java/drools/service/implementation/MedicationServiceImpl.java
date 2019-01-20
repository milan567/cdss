package drools.service.implementation;

import drools.model.Medication;
import drools.repository.MedicationRepository;
import drools.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    MedicationRepository medicationRepository;

    @Override
    public Medication save(Medication medication) {
        return medicationRepository.save(medication);
    }

    @Override
    public Medication findMedicationByText(String text) {
        return medicationRepository.findMedicationByText(text);
    }
}

