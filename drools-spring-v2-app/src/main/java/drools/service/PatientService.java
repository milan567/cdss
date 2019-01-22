package drools.service;


import drools.model.Patient;
import org.kie.api.runtime.KieSession;

import java.util.List;

public interface PatientService {

    Patient save(Patient p);

    List<Patient> getAll();

    Patient findPatient(Integer id);

    Patient findPatientBySurname(String surname);

    List<Patient> getPatientsWithChronicDiseases(KieSession kieSession);

    List<Patient> getPotentialAddicts(KieSession kieSession);

    List<Patient> getLowImmunityPatients(KieSession kieSession);
}