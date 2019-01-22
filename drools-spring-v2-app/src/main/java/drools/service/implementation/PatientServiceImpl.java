package drools.service.implementation;

import drools.model.*;
import drools.repository.PatientRepository;
import drools.service.PatientService;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient save(Patient p) {
        return patientRepository.save(p);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findPatient(Integer id) {
        return patientRepository.getOne(id);
    }

    @Override
    public Patient findPatientBySurname(String surname) {
        return patientRepository.findOneByPatientSurame(surname);
    }

    @Override
    public List<Patient> getPatientsWithChronicDiseases(KieSession kieSession) {

        List<Patient> patients = patientRepository.findAll();
        for (Patient p : patients) {
            kieSession.insert(p);
        }

        kieSession.insert(new DateChecker());

        QueryResults results = kieSession
                .getQueryResults("Pacijent sa hronicnom bolescu", "list");
        System.out.println(results.size());

        List<Patient> foundPatients = new ArrayList<>();
        List<Disease> foundDiseases = new ArrayList<>();
        for (QueryResultsRow row : results) {

            Patient p = (Patient) row.get("p");
            Disease d = (Disease) row.get("d");

            foundPatients.add(p);
            foundDiseases.add(d);
        }

        release(kieSession);
        return foundPatients;
    }

    @Override
    public List<Patient> getPotentialAddicts(KieSession kieSession) {

        List<Patient> patients = patientRepository.findAll();
        for (Patient p : patients) {
            kieSession.insert(p);
        }

        kieSession.insert(new DateChecker());

        Medication m = new Medication();
        m.setMedicationType(MedicationType.ANALGETIC);
        kieSession.insert(m);

        QueryResults results = kieSession
                .getQueryResults("Potencijalni zavisnik");
        System.out.println(results.size());

        List<Patient> foundPatients = new ArrayList<>();
        for (QueryResultsRow row : results) {
            System.out.println("----------");
            Patient p = (Patient) row.get("p");

            foundPatients.add(p);
        }
        System.out.println(foundPatients.size());
        release(kieSession);
        return foundPatients;
    }

    @Override
    public List<Patient> getLowImmunityPatients(KieSession kieSession) {

        List<Patient> patients = patientRepository.findAll();
        for (Patient p : patients) {
            kieSession.insert(p);
        }

        kieSession.insert(new DateChecker());

        QueryResults results = kieSession
                .getQueryResults("Pacijent sa oslabljenim imunitetom");
        System.out.println(results.size());

        List<Patient> foundPatients = new ArrayList<>();
        for (QueryResultsRow row : results) {
            Patient p = (Patient) row.get("p");

            foundPatients.add(p);
        }
        System.out.println(foundPatients.size());
        release(kieSession);
        return foundPatients;
    }

    public void release(KieSession kieSession) {
        for (Object object : kieSession.getObjects()) {
            if (!object.getClass().equals(Disease.class))
                kieSession.delete(kieSession.getFactHandle(object));
        }
    }



    }
