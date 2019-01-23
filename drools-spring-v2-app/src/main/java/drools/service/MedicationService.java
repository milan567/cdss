package drools.service;

import drools.model.Medication;
import drools.model.dto.CheckAllergiesResponseDTO;
import org.kie.api.runtime.KieSession;

import java.util.List;

public interface MedicationService {

    Medication save(Medication medication);

    Medication findMedicationByText(String text);

    List<Medication> findAllMedications();

    CheckAllergiesResponseDTO checkAlergiest(Integer patientId, Integer medicationId, KieSession kieSession);

    List<Medication> deleteMedication(Integer id);

    Medication getMedication(Integer id);
}
