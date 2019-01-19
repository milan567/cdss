package drools;

import drools.model.*;
import drools.service.DiseaseService;
import drools.service.PatientService;
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

    @Autowired
    PatientService patientService;

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
        addPatients();
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
        Symptom s7 = new Symptom("Bol koji se siri do usiju");
        Symptom s8 = new Symptom("Temperatura od 40 do 41");
        Symptom s9 = new Symptom("Gubitak apetita");
        Symptom s10 = new Symptom("Umor");
        Symptom s11 = new Symptom("Zuti sekret iz nosa");
        Symptom s12 = new Symptom("Oticanje oko ociju");
        Symptom s13 = new Symptom("U poslednjih 6 mjeseci zabiljezeno je bar 10 slucajeva u " +
                "kojima je korisnik imao visok pritisak");
        Symptom s14 = new Symptom("Cesto uriniranje");
        Symptom s15 = new Symptom("Gubitak tjelesne tezine");
        Symptom s16 = new Symptom("Zamor");
        Symptom s17 = new Symptom("Mucnina i povracanje");
        Symptom s18 = new Symptom("Nocturia");
        Symptom s19 = new Symptom("Otoci nogu i zglobova");
        Symptom s20 = new Symptom("Gusenje");
        Symptom s21 = new Symptom("Bol u grudima");
        Symptom s22 = new Symptom("Dijareja");

        symptomService.save(s);
        symptomService.save(s1);
        symptomService.save(s2);
        symptomService.save(s3);
        symptomService.save(s4);
        symptomService.save(s5);
        symptomService.save(s6);
        symptomService.save(s7);
        symptomService.save(s8);
        symptomService.save(s9);
        symptomService.save(s10);
        symptomService.save(s11);
        symptomService.save(s12);
        symptomService.save(s13);
        symptomService.save(s14);
        symptomService.save(s15);
        symptomService.save(s16);
        symptomService.save(s17);
        symptomService.save(s18);
        symptomService.save(s19);
        symptomService.save(s20);
        symptomService.save(s21);
        symptomService.save(s22);

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

        Symptom s7 = symptomService.findByName("Bol koji se siri do usiju");
        Symptom s8 = symptomService.findByName("Temperatura od 40 do 41");
        Symptom s9 = symptomService.findByName("Gubitak apetita");
        Symptom s10 = symptomService.findByName("Umor");
        Symptom s11 = symptomService.findByName("Zuti sekret iz nosa");
        Disease d2 = new Disease("Upala krajnika",1);
        d2.getNonSpecificSymptoms().add(s1);
        d2.getNonSpecificSymptoms().add(s7);
        d2.getNonSpecificSymptoms().add(s2);
        d2.getNonSpecificSymptoms().add(s8);
        d2.getNonSpecificSymptoms().add(s6);
        d2.getNonSpecificSymptoms().add(s9);
        d2.getNonSpecificSymptoms().add(s10);
        d2.getNonSpecificSymptoms().add(s11);
        diseaseService.save(d2);

        Symptom s12 = symptomService.findByName("Oticanje oko ociju");
        Disease d3 = new Disease("Sinusna infekcija",1);
        d3.getNonSpecificSymptoms().add(s2);
        d3.getNonSpecificSymptoms().add(s11);
        d3.getNonSpecificSymptoms().add(s1);
        d3.getNonSpecificSymptoms().add(s5);
        d3.getNonSpecificSymptoms().add(s4);
        diseaseService.save(d3);

        Symptom s13 = symptomService.findByName("U poslednjih 6 mjeseci zabiljezeno je bar 10 slucajeva u " +
                "kojima je korisnik imao visok pritisak");
        Disease d4 = new Disease("Hipertenzija",2);
        d4.getNonSpecificSymptoms().add(s13);
        diseaseService.save(d4);

        Symptom s14 = symptomService.findByName("Cesto uriniranje");
        Symptom s15 = symptomService.findByName("Gubitak tjelesne tezine");
        Symptom s16 = symptomService.findByName("Zamor");
        Symptom s17 = symptomService.findByName("Mucnina i povracanje");
        Disease d5 = new Disease("Dijabetes",2);
        d5.getNonSpecificSymptoms().add(s14);
        d5.getNonSpecificSymptoms().add(s15);
        d5.getNonSpecificSymptoms().add(s16);
        d5.getNonSpecificSymptoms().add(s17);
        diseaseService.save(d5);

        Symptom s18 = symptomService.findByName("Nocturia");
        Symptom s19 = symptomService.findByName("Otoci nogu i zglobova");
        Symptom s20 = symptomService.findByName("Gusenje");
        Symptom s21 = symptomService.findByName("Bol u grudima");
        Disease d6 = new Disease("Hronicna bubrezna bolest",3);
        d6.getNonSpecificSymptoms().add(s16);
        d6.getNonSpecificSymptoms().add(s18);
        d6.getNonSpecificSymptoms().add(s19);
        d6.getNonSpecificSymptoms().add(s20);
        d6.getNonSpecificSymptoms().add(s21);
        diseaseService.save(d6);

        Symptom s22 = symptomService.findByName("Dijareja");
        Disease d7 = new Disease("Akutna bubrezna povreda",3);
        d7.getNonSpecificSymptoms().add(s16);
        d7.getNonSpecificSymptoms().add(s20);
        d7.getNonSpecificSymptoms().add(s19);
        d7.getNonSpecificSymptoms().add(s22);
        diseaseService.save(d7);

    }

    void addPatients(){
        Patient p1 = new Patient("Nemanja","Matic");
        Patient p2 = new Patient("Luka","Jovic");
        Patient p3 = new Patient("Luka","Milivojevic");
        Patient p4 = new Patient("Aleksandar","Mitrovic");
        Patient p5 = new Patient("Dusan","Jovancic");
        Patient p6 = new Patient("Milan","Borjan");
        Patient p7 = new Patient("Filip","Stojkovic");
        Patient p8 = new Patient("Vujadin","Savic");
        Patient p9 = new Patient("El Fardu","Ben");
      //  Patient p10 = new Patient("Marko","Marin");
      //  Patient p11 = new Patient("Nikola","Stojiljkovic");
      //  Patient p12 = new Patient("Marko","Gobeljic");
      //  Patient p13 = new Patient("Milos","Degenek");


        patientService.save(p1);
        patientService.save(p2);
        patientService.save(p3);
        patientService.save(p4);
        patientService.save(p5);
        patientService.save(p6);
        patientService.save(p7);
        patientService.save(p8);
        patientService.save(p9);
  //      patientService.save(p10);
  //      patientService.save(p11);
  //      patientService.save(p12);
  //      patientService.save(p13);
    }

}
