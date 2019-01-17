package drools;

import drools.model.*;
import drools.service.DiseaseService;
import drools.service.SymptomService;
import drools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Initializer implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    SymptomService symptomService;

    @Autowired
    DiseaseService diseaseService;

    @Override
    @SuppressWarnings("unchecked")
    public void run(ApplicationArguments args) throws Exception {
        initializeData();
    }

    private void initializeData() {
        clearData();
        addAdmins();
        addDoctors();
        addSymptoms();
        addDiseases();
    }

    private void clearData() {
        userService.deleteAll();
    }

    private void addAdmins() {
        User user = new User("admin","admin","Milan",
                "Salic","admin",Authority.ADMIN);
        userService.save(user);
    }

    private void addDoctors() {
        User doktor1 = new User("doktor1", "doktor1", "Nikola",
                "Bartula","doktor", Authority.DOCTOR);
        User doktor2 = new User("doktor2", "doktor2", "Nikola",
                "Sarac","doktor", Authority.DOCTOR);
        User doktor3 = new User("doktor3", "doktor3", "Sreten",
                "Stokic","doktor", Authority.DOCTOR);
        User doktor4 = new User("doktor4", "doktor4", "Rados",
                "Acimovic","doktor", Authority.DOCTOR);
        User doktor5 = new User("doktor5", "doktor5", "Ivana",
                "Salic","doktor", Authority.DOCTOR);
        User doktor6 = new User("doktor6", "doktor6", "Jovana",
                "Salic","doktor", Authority.DOCTOR);
        userService.save(doktor1);
        userService.save(doktor2);
        userService.save(doktor3);
        userService.save(doktor4);
        userService.save(doktor5);
        userService.save(doktor6);
    }

    private void addSymptoms() {
        Symptom s = new Symptom("Curenje iz nosa");
        Symptom s1 = new Symptom("Bol u grlu");
        Symptom s2 = new Symptom("Glavobolja");
        Symptom s3 = new Symptom("Kijanje");
        Symptom s4 = new Symptom("Kasalj");
        Symptom s5 = new Symptom("Temperatura veca od 38");
        Symptom s6 = new Symptom("Drhtavica");
        symptomService.save(s);
        symptomService.save(s1);
        symptomService.save(s2);
        symptomService.save(s3);
        symptomService.save(s4);
        symptomService.save(s5);
        symptomService.save(s6);
    }

    private void addDiseases() {
        Symptom s = symptomService.findByName("Curenje iz nosa");
        Symptom s1 = symptomService.findByName("Bol u grlu");
        Symptom s2 = symptomService.findByName("Glavobolja");
        Symptom s3 = symptomService.findByName("Kijanje");
        Symptom s4 = symptomService.findByName("Kasalj");
        Disease d = new Disease("Prehlada",1);
        d.getNonSpecificSymptoms().add(s);
        d.getNonSpecificSymptoms().add(s1);
        d.getNonSpecificSymptoms().add(s2);
        d.getNonSpecificSymptoms().add(s3);
        d.getNonSpecificSymptoms().add(s4);
        diseaseService.save(d);

        Symptom s5 = symptomService.findByName("Temperatura veca od 38");
        Symptom s6 = symptomService.findByName("Drhtavica");
        Disease d1 = new Disease("Groznica",1);
        d1.getNonSpecificSymptoms().add(s);
        d1.getNonSpecificSymptoms().add(s1);
        d1.getNonSpecificSymptoms().add(s2);
        d1.getNonSpecificSymptoms().add(s3);
        d1.getNonSpecificSymptoms().add(s4);
        d1.getNonSpecificSymptoms().add(s5);
        d1.getNonSpecificSymptoms().add(s6);
        diseaseService.save(d1);

    }

}
