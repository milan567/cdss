package drools.service;


import drools.model.Patient;

import java.util.List;

public interface PatientService {

    Patient save(Patient p);

    List<Patient> getAll();

    Patient findPatient(Integer id);

    Patient findPatientBySurname(String surname);
}