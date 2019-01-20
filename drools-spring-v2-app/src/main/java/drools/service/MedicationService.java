package drools.service;

import drools.model.Medication;

public interface MedicationService {

    Medication save(Medication medication);

    Medication findMedicationByText(String text);
}
