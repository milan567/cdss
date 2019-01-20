package drools.repository;

import drools.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {

    Patient findOneByPatientSurame(String surname);
}
