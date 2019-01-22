package drools;

import drools.model.*;
import drools.service.*;
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

    @Autowired
    ExaminationService examinationService;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    MedicationService medicationService;

    @Override
    @SuppressWarnings("unchecked")
    public void run(ApplicationArguments args) throws Exception {
        initializeData();
    }

    private void initializeData() throws ParseException {
        clearData();
        addAdmins();
        addDoctors();
        addSymptoms();
        addDiseases();
        addMedications();
        addPatients();
        addExaminations();
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
        Symptom s = new Symptom("Curenje iz nosa",SymptomType.NONSPECIFIC);
        Symptom s1 = new Symptom("Bol u grlu",SymptomType.NONSPECIFIC);
        Symptom s2 = new Symptom("Glavobolja",SymptomType.NONSPECIFIC);
        Symptom s3 = new Symptom("Kijanje",SymptomType.NONSPECIFIC);
        Symptom s4 = new Symptom("Kasalj",SymptomType.NONSPECIFIC);
        Symptom s5 = new Symptom("Temperatura veca od 38",SymptomType.NONSPECIFIC);
        Symptom s6 = new Symptom("Drhtavica",SymptomType.NONSPECIFIC);
        Symptom s7 = new Symptom("Bol koji se siri do usiju",SymptomType.NONSPECIFIC);
        Symptom s8 = new Symptom("Temperatura od 40 do 41",SymptomType.NONSPECIFIC);
        Symptom s9 = new Symptom("Gubitak apetita",SymptomType.NONSPECIFIC);
        Symptom s10 = new Symptom("Umor",SymptomType.NONSPECIFIC);
        Symptom s11 = new Symptom("Zuti sekret iz nosa",SymptomType.NONSPECIFIC);
        Symptom s12 = new Symptom("Oticanje oko ociju",SymptomType.NONSPECIFIC);
        Symptom s13 = new Symptom("Visok krvni pritisak",SymptomType.NONSPECIFIC);
        Symptom s14 = new Symptom("Cesto uriniranje",SymptomType.NONSPECIFIC);
        Symptom s15 = new Symptom("Gubitak tjelesne tezine",SymptomType.NONSPECIFIC);
        Symptom s16 = new Symptom("Zamor",SymptomType.NONSPECIFIC);
        Symptom s17 = new Symptom("Mucnina i povracanje",SymptomType.NONSPECIFIC);
        Symptom s18 = new Symptom("Nocturia",SymptomType.NONSPECIFIC);
        Symptom s19 = new Symptom("Otoci nogu i zglobova",SymptomType.NONSPECIFIC);
        Symptom s20 = new Symptom("Gusenje",SymptomType.NONSPECIFIC);
        Symptom s21 = new Symptom("Bol u grudima",SymptomType.NONSPECIFIC);
        Symptom s22 = new Symptom("Dijareja",SymptomType.NONSPECIFIC);
        Symptom s23 = new Symptom("Oporavak od operacije",SymptomType.SPECIFIC);

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
        symptomService.save(s23);
    }

    private void addDiseases() {
        Symptom s = symptomService.findByName("Curenje iz nosa");
        Symptom s1 = symptomService.findByName("Bol u grlu");
        Symptom s2 = symptomService.findByName("Glavobolja");
        Symptom s3 = symptomService.findByName("Kijanje");
        Symptom s4 = symptomService.findByName("Kasalj");
        Disease d = new Disease("Prehlada",1);
        d.getSymptoms().add(s);
        d.getSymptoms().add(s1);
        d.getSymptoms().add(s2);
        d.getSymptoms().add(s3);
        d.getSymptoms().add(s4);
        diseaseService.save(d);

        Symptom s5 = symptomService.findByName("Temperatura veca od 38");
        Symptom s6 = symptomService.findByName("Drhtavica");
        Disease d1 = new Disease("Groznica",1);
        d1.getSymptoms().add(s);
        d1.getSymptoms().add(s1);
        d1.getSymptoms().add(s2);
        d1.getSymptoms().add(s3);
        d1.getSymptoms().add(s4);
        d1.getSymptoms().add(s5);
        d1.getSymptoms().add(s6);
        diseaseService.save(d1);

        Symptom s7 = symptomService.findByName("Bol koji se siri do usiju");
        Symptom s8 = symptomService.findByName("Temperatura od 40 do 41");
        Symptom s9 = symptomService.findByName("Gubitak apetita");
        Symptom s10 = symptomService.findByName("Umor");
        Symptom s11 = symptomService.findByName("Zuti sekret iz nosa");
        Disease d2 = new Disease("Upala krajnika",1);
        d2.getSymptoms().add(s1);
        d2.getSymptoms().add(s7);
        d2.getSymptoms().add(s2);
        d2.getSymptoms().add(s8);
        d2.getSymptoms().add(s6);
        d2.getSymptoms().add(s9);
        d2.getSymptoms().add(s10);
        d2.getSymptoms().add(s11);
        diseaseService.save(d2);

        Symptom s12 = symptomService.findByName("Oticanje oko ociju");
        Disease d3 = new Disease("Sinusna infekcija",1);
        d3.getSymptoms().add(s2);
        d3.getSymptoms().add(s11);
        d3.getSymptoms().add(s1);
        d3.getSymptoms().add(s5);
        d3.getSymptoms().add(s4);
        diseaseService.save(d3);

        Symptom s13 = symptomService.findByName("Visok krvni pritisak");
        Disease d4 = new Disease("Hipertenzija",2);
        d4.getSymptoms().add(s13);
        diseaseService.save(d4);

        Symptom s14 = symptomService.findByName("Cesto uriniranje");
        Symptom s15 = symptomService.findByName("Gubitak tjelesne tezine");
        Symptom s16 = symptomService.findByName("Zamor");
        Symptom s17 = symptomService.findByName("Mucnina i povracanje");
        Disease d5 = new Disease("Dijabetes",2);
        d5.getSymptoms().add(s14);
        d5.getSymptoms().add(s15);
        d5.getSymptoms().add(s16);
        d5.getSymptoms().add(s17);
        diseaseService.save(d5);

        Symptom s18 = symptomService.findByName("Nocturia");
        Symptom s19 = symptomService.findByName("Otoci nogu i zglobova");
        Symptom s20 = symptomService.findByName("Gusenje");
        Symptom s21 = symptomService.findByName("Bol u grudima");
        Disease d6 = new Disease("Hronicna bubrezna bolest",3);
        d6.getSymptoms().add(s16);
        d6.getSymptoms().add(s18);
        d6.getSymptoms().add(s19);
        d6.getSymptoms().add(s20);
        d6.getSymptoms().add(s21);
        diseaseService.save(d6);

        Symptom s22 = symptomService.findByName("Dijareja");
        Symptom s23 = symptomService.findByName("Oporavak od operacije");
        Disease d7 = new Disease("Akutna bubrezna povreda",3);
        d7.getSymptoms().add(s16);
        d7.getSymptoms().add(s20);
        d7.getSymptoms().add(s19);
        d7.getSymptoms().add(s22);
        d7.getSymptoms().add(s23);
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
        Patient p10 = new Patient("Marko","Marin");
        Patient p11 = new Patient("Nikola","Stojiljkovic");
        Patient p12 = new Patient("Marko","Gobeljic");
        Patient p13 = new Patient("Milos","Degenek");
        Patient p14 = new Patient("Nemanja","Radonjic");
        Patient p15 = new Patient("Mijat","Gacinovic");

        Ingredient i = ingredientService.findIngredientByText("Sastojak 1");
        Ingredient i1 = ingredientService.findIngredientByText("Sastojak 2");
        Ingredient i2 = ingredientService.findIngredientByText("Sastojak 3");
        Ingredient i3 = ingredientService.findIngredientByText("Sastojak 4");

        Medication m = medicationService.findMedicationByText("Amoksicilin");
        Medication m1 = medicationService.findMedicationByText("Motrin");

        p8.getIngredientAllergies().add(i);
        p8.getIngredientAllergies().add(i2);

        p9.getIngredientAllergies().add(i1);
        p9.getIngredientAllergies().add(i3);

        p8.getMedicationAllergies().add(m);
        p9.getMedicationAllergies().add(m1);

        patientService.save(p1);
        patientService.save(p2);
        patientService.save(p3);
        patientService.save(p4);
        patientService.save(p5);
        patientService.save(p6);
        patientService.save(p7);
        patientService.save(p8);
        patientService.save(p9);
        patientService.save(p10);
        patientService.save(p11);
        patientService.save(p12);
        patientService.save(p13);
        patientService.save(p14);
        patientService.save(p15);
    }

    private void addMedications() {
        Ingredient ingr1 = new Ingredient("Sastojak 1");
        Ingredient ingr2 = new Ingredient("Sastojak 2");
        Ingredient ingr3 = new Ingredient("Sastojak 3");
        Ingredient ingr4 = new Ingredient("Sastojak 4");
        Ingredient ingr5 = new Ingredient("Sastojak 5");
        Ingredient ingr6 = new Ingredient("Sastojak 6");
        Ingredient ingr7 = new Ingredient("Sastojak 7");
        Ingredient ingr8 = new Ingredient("Sastojak 8");
        Ingredient ingr9 = new Ingredient("Sastojak 9");
        Ingredient ingr10 = new Ingredient("Sastojak 10");

        ingredientService.save(ingr1);
        ingredientService.save(ingr2);
        ingredientService.save(ingr3);
        ingredientService.save(ingr4);
        ingredientService.save(ingr5);
        ingredientService.save(ingr6);
        ingredientService.save(ingr7);
        ingredientService.save(ingr8);
        ingredientService.save(ingr9);
        ingredientService.save(ingr10);

        Medication medication = new Medication("Ibuprofen", MedicationType.ANALGETIC);
        medication.getIngredients().add(ingr1);
        medication.getIngredients().add(ingr2);
        medication.getIngredients().add(ingr3);
        medication.getIngredients().add(ingr4);

        Medication medication1 = new Medication("Amoksicilin", MedicationType.ANTIBIOTIC);
        medication.getIngredients().add(ingr5);
        medication.getIngredients().add(ingr6);
        medication.getIngredients().add(ingr7);
        medication.getIngredients().add(ingr8);

        Medication medication2 = new Medication("Motrin", MedicationType.OTHER);
        medication.getIngredients().add(ingr7);
        medication.getIngredients().add(ingr8);
        medication.getIngredients().add(ingr9);
        medication.getIngredients().add(ingr10);

        medicationService.save(medication);
        medicationService.save(medication1);
        medicationService.save(medication2);
    }

    private void addExaminations() throws ParseException {
        Patient p = patientService.findPatientBySurname("Jovic");
        User doctor = userService.findDoctorByUsername("doktor1");
        List<Medication> medications = new ArrayList<>();
        List<Symptom> symptoms = new ArrayList<>();
        Symptom symptom = symptomService.findByName("Visok krvni pritisak");
        symptoms.add(symptom);
        Disease disease = diseaseService.getDiseaseByName("Prehlada");
        Examination e = new Examination(new Date(),doctor,disease,symptoms,medications);
        examinationService.saveExamination(e);
        Examination e1 = new Examination(new Date(),doctor,disease,symptoms,medications);
        examinationService.saveExamination(e1);
        Examination e2 = new Examination(new Date(),doctor,disease,symptoms,medications);
        examinationService.saveExamination(e2);
        Examination e3 = new Examination(new Date(),doctor,disease,symptoms,medications);
        examinationService.saveExamination(e3);
        Examination e4 = new Examination(new Date(),doctor,disease,symptoms,medications);
        examinationService.saveExamination(e4);
        Examination e5 = new Examination(new Date(),doctor,disease,symptoms,medications);
        examinationService.saveExamination(e5);
        Examination e6 = new Examination(new Date(),doctor,disease,symptoms,medications);
        examinationService.saveExamination(e6);
        Examination e7 = new Examination(new Date(),doctor,disease,symptoms,medications);
        examinationService.saveExamination(e7);
        Examination e8 = new Examination(new Date(),doctor,disease,symptoms,medications);
        examinationService.saveExamination(e8);
        Examination e9 = new Examination(new Date(),doctor,disease,symptoms,medications);
        examinationService.saveExamination(e9);
        Examination e10 = new Examination(new Date(),doctor,disease,symptoms,medications);
        examinationService.saveExamination(e10);
        p.getExaminations().add(e);
        p.getExaminations().add(e1);
        p.getExaminations().add(e2);
        p.getExaminations().add(e3);
        p.getExaminations().add(e4);
        p.getExaminations().add(e5);
        p.getExaminations().add(e6);
        p.getExaminations().add(e7);
        p.getExaminations().add(e8);
        p.getExaminations().add(e9);
        p.getExaminations().add(e10);
        patientService.save(p);

        Patient p1 = patientService.findPatientBySurname("Matic");
        Examination e11 = new Examination(new Date(),doctor,disease,symptoms,medications);
        examinationService.saveExamination(e11);
        p1.getExaminations().add(e11);
        patientService.save(p1);

        Patient p2 = patientService.findPatientBySurname("Milivojevic");
        Disease disease1 = diseaseService.getDiseaseByName("Hipertenzija");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
        Date date = dateFormat.parse("05.07.2017.");
        Examination e12 = new Examination(date,doctor,disease1,symptoms,medications);
        examinationService.saveExamination(e12);
        p2.getExaminations().add(e12);
        patientService.save(p2);


        Patient p3 = patientService.findPatientBySurname("Jovancic");
        Disease disease2 = diseaseService.getDiseaseByName("Dijabetes");
        Examination e13 = new Examination(new Date(),doctor,disease2,symptoms,medications);
        examinationService.saveExamination(e13);
        p3.getExaminations().add(e13);
        patientService.save(p3);


        Patient p4 = patientService.findPatientBySurname("Mitrovic");
        Disease disease3 = diseaseService.getDiseaseByName("Groznica");
        Date date1 = dateFormat.parse("19.01.2019.");
        Examination e14 = new Examination(date1,doctor,disease3,symptoms,medications);
        examinationService.saveExamination(e14);
        p4.getExaminations().add(e14);
        patientService.save(p4);


        Patient p5 = patientService.findPatientBySurname("Borjan");
        Medication medication = medicationService.findMedicationByText("Ibuprofen");
        ArrayList<Medication> medications1 = new ArrayList<>();
        medications1.add(medication);
        Examination e15 = new Examination(date1,doctor,disease3,symptoms,medications1);
        examinationService.saveExamination(e15);
        p5.getExaminations().add(e15);
        patientService.save(p5);

        Patient p6 = patientService.findPatientBySurname("Gobeljic");
        Disease disease4 = diseaseService.getDiseaseByName("Hronicna bubrezna bolest");
        Date date2 = dateFormat.parse("19.01.2018.");
        Examination e16 = new Examination(date2,doctor,disease4,symptoms,medications);
        examinationService.saveExamination(e16);
        Date date3 = dateFormat.parse("23.11.2018.");
        Examination e17 = new Examination(date3,doctor,disease4,symptoms,medications);
        examinationService.saveExamination(e17);
        Date date4 = dateFormat.parse("19.01.2018.");
        Examination e18 = new Examination(date4,doctor,disease4,symptoms,medications);
        examinationService.saveExamination(e18);
        Date date5 = dateFormat.parse("25.01.2018.");
        Examination e19 = new Examination(date5,doctor,disease4,symptoms,medications);
        examinationService.saveExamination(e19);
        Date date6 = dateFormat.parse("31.12.2018.");
        Examination e20 = new Examination(date6,doctor,disease4,symptoms,medications);
        examinationService.saveExamination(e20);
        Date date7 = dateFormat.parse("20.07.2017.");
        Examination e21 = new Examination(date7,doctor,disease4,symptoms,medications);
        examinationService.saveExamination(e21);
        p6.getExaminations().add(e16);
        p6.getExaminations().add(e17);
        p6.getExaminations().add(e18);
        p6.getExaminations().add(e19);
        p6.getExaminations().add(e20);
        p6.getExaminations().add(e21);
        patientService.save(p6);

        Patient p7 = patientService.findPatientBySurname("Marin");
        Disease disease5 = diseaseService.getDiseaseByName("Dijabetes");
        Examination e22 = new Examination(date2,doctor,disease5,symptoms,medications);
        examinationService.saveExamination(e22);
        Examination e23 = new Examination(date3,doctor,disease5,symptoms,medications);
        examinationService.saveExamination(e23);
        Examination e24 = new Examination(date4,doctor,disease5,symptoms,medications);
        examinationService.saveExamination(e24);
        Examination e25 = new Examination(date5,doctor,disease5,symptoms,medications);
        examinationService.saveExamination(e25);
        Examination e26 = new Examination(date6,doctor,disease5,symptoms,medications);
        examinationService.saveExamination(e26);
        Examination e27 = new Examination(date7,doctor,disease5,symptoms,medications);
        examinationService.saveExamination(e27);
        p7.getExaminations().add(e22);
        p7.getExaminations().add(e23);
        p7.getExaminations().add(e24);
        p7.getExaminations().add(e25);
        p7.getExaminations().add(e26);
        p7.getExaminations().add(e27);
        patientService.save(p7);

        Patient p8 = patientService.findPatientBySurname("Stojkovic");
        User doctor1 = userService.findDoctorByUsername("doktor2");
        User doctor2 = userService.findDoctorByUsername("doktor3");
        User doctor3 = userService.findDoctorByUsername("doktor4");
        List<Medication> medications2 = new ArrayList<>();
        Medication medication1 = medicationService.findMedicationByText("Ibuprofen");
        medications2.add(medication1);
        Examination e28 = new Examination(date6,doctor1,disease5,symptoms,medications2);
        examinationService.saveExamination(e28);
        Examination e29 = new Examination(date6,doctor2,disease5,symptoms,medications2);
        examinationService.saveExamination(e29);
        Examination e30 = new Examination(date6,doctor3,disease5,symptoms,medications2);
        examinationService.saveExamination(e30);
        Examination e31 = new Examination(date6,doctor3,disease5,symptoms,medications2);
        examinationService.saveExamination(e31);
        Examination e32 = new Examination(date6,doctor2,disease5,symptoms,medications2);
        examinationService.saveExamination(e32);
        Examination e33 = new Examination(date6,doctor1,disease5,symptoms,medications2);
        examinationService.saveExamination(e33);
        p8.getExaminations().add(e28);
        p8.getExaminations().add(e29);
        p8.getExaminations().add(e30);
        p8.getExaminations().add(e31);
        p8.getExaminations().add(e32);
        p8.getExaminations().add(e33);
        patientService.save(p8);


        Patient p9 = patientService.findPatientBySurname("Degenek");
        Examination e34 = new Examination(date3,doctor1,disease4,symptoms,medications2);
        examinationService.saveExamination(e34);
        Examination e35 = new Examination(date3,doctor2,disease2,symptoms,medications2);
        examinationService.saveExamination(e35);
        Examination e36 = new Examination(date3,doctor3,disease2,symptoms,medications2);
        examinationService.saveExamination(e36);
        Examination e37 = new Examination(date3,doctor3,disease2,symptoms,medications2);
        examinationService.saveExamination(e37);
        Examination e38 = new Examination(date3,doctor2,disease4,symptoms,medications2);
        examinationService.saveExamination(e38);
        Examination e39 = new Examination(date3,doctor1,disease4,symptoms,medications2);
        examinationService.saveExamination(e39);
        p9.getExaminations().add(e34);
        p9.getExaminations().add(e35);
        p9.getExaminations().add(e36);
        p9.getExaminations().add(e37);
        p9.getExaminations().add(e38);
        p9.getExaminations().add(e39);
        patientService.save(p9);

        Patient p10 = patientService.findPatientBySurname("Gacinovic");
        Medication medication3 = medicationService.findMedicationByText("Amoksicilin");
        List<Medication> medications3 = new ArrayList<>();
        medications3.add(medication3);
        Examination e40 = new Examination(date3,doctor1,disease4,symptoms,medications3);
        Examination e41 = new Examination(date3,doctor1,disease4,symptoms,medications3);
        Examination e42 = new Examination(date3,doctor1,disease4,symptoms,medications3);
        Examination e43 = new Examination(date3,doctor1,disease4,symptoms,medications3);
        Examination e44 = new Examination(date3,doctor1,disease4,symptoms,medications3);
        Examination e45 = new Examination(date3,doctor1,disease4,symptoms,medications3);
        Examination e46 = new Examination(date3,doctor1,disease3,symptoms,medications3);
        Examination e47 = new Examination(date3,doctor1,disease3,symptoms,medications3);
        Examination e48 = new Examination(date3,doctor1,disease3,symptoms,medications3);
        Examination e49 = new Examination(date3,doctor1,disease3,symptoms,medications3);
        Examination e50 = new Examination(date3,doctor1,disease3,symptoms,medications3);
        examinationService.saveExamination(e40);
        examinationService.saveExamination(e41);
        examinationService.saveExamination(e42);
        examinationService.saveExamination(e43);
        examinationService.saveExamination(e44);
        examinationService.saveExamination(e45);
        examinationService.saveExamination(e46);
        examinationService.saveExamination(e47);
        examinationService.saveExamination(e48);
        examinationService.saveExamination(e49);
        examinationService.saveExamination(e50);
        p10.getExaminations().add(e40);
        p10.getExaminations().add(e41);
        p10.getExaminations().add(e42);
        p10.getExaminations().add(e43);
        p10.getExaminations().add(e44);
        p10.getExaminations().add(e45);
        p10.getExaminations().add(e46);
        p10.getExaminations().add(e47);
        p10.getExaminations().add(e48);
        p10.getExaminations().add(e49);
        p10.getExaminations().add(e50);
        patientService.save(p10);


        Patient p11 = patientService.findPatientBySurname("Radonjic");
        Examination e51 = new Examination(date3,doctor1,disease3,symptoms,medications3);
        Examination e52 = new Examination(date3,doctor1,disease4,symptoms,medications3);
        Examination e53 = new Examination(date3,doctor1,disease4,symptoms,medications3);
        Examination e54 = new Examination(date3,doctor1,disease4,symptoms,medications3);
        Examination e55 = new Examination(date3,doctor1,disease4,symptoms,medications3);
        Examination e56 = new Examination(date3,doctor1,disease4,symptoms,medications3);
        Examination e57 = new Examination(date3,doctor1,disease4,symptoms,medications3);
        Examination e58 = new Examination(date3,doctor1,disease3,symptoms,medications3);
        Examination e59 = new Examination(date3,doctor1,disease3,symptoms,medications3);
        Examination e60 = new Examination(date3,doctor1,disease3,symptoms,medications3);
        Examination e61 = new Examination(date3,doctor1,disease3,symptoms,medications3);
        examinationService.saveExamination(e51);
        examinationService.saveExamination(e52);
        examinationService.saveExamination(e53);
        examinationService.saveExamination(e54);
        examinationService.saveExamination(e55);
        examinationService.saveExamination(e56);
        examinationService.saveExamination(e57);
        examinationService.saveExamination(e58);
        examinationService.saveExamination(e59);
        examinationService.saveExamination(e60);
        examinationService.saveExamination(e61);
        p11.getExaminations().add(e51);
        p11.getExaminations().add(e52);
        p11.getExaminations().add(e53);
        p11.getExaminations().add(e54);
        p11.getExaminations().add(e55);
        p11.getExaminations().add(e56);
        p11.getExaminations().add(e57);
        p11.getExaminations().add(e58);
        p11.getExaminations().add(e59);
        p11.getExaminations().add(e60);
        p11.getExaminations().add(e61);
        patientService.save(p11);


    }

}
