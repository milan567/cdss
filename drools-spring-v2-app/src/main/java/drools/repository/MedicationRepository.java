package drools.repository;

import drools.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication,Integer> {

    Medication findMedicationByText(String text);

}
