package drools.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id" , unique = true, nullable = false)
    private Integer id;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "patient_surname")
    private String patientSurame;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Examination> examinations = new ArrayList<>();

    @ManyToMany
    private List<Ingredient> ingredientAllergies = new ArrayList<>();

    @ManyToMany
    private List<Medication> medicationAllergies = new ArrayList<>();

    public List<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<Examination> examinations) {
        this.examinations = examinations;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", patientName='" + patientName + '\'' +
                ", patientSurame='" + patientSurame + '\'' +
                '}';
    }

    public Patient() {
    }

    public Patient(String patientName, String patientSurame) {

        this.patientName = patientName;
        this.patientSurame = patientSurame;
    }

    public Patient(String patientName, String patientSurame, List<Examination> examinations,
                   List<Ingredient> ingredientAllergies, List<Medication> medicationAllergies) {
        this.patientName = patientName;
        this.patientSurame = patientSurame;
        this.examinations = examinations;
        this.ingredientAllergies = ingredientAllergies;
        this.medicationAllergies = medicationAllergies;
    }

    public List<Ingredient> getIngredientAllergies() {
        return ingredientAllergies;
    }

    public void setIngredientAllergies(List<Ingredient> ingredientAllergies) {
        this.ingredientAllergies = ingredientAllergies;
    }

    public List<Medication> getMedicationAllergies() {
        return medicationAllergies;
    }

    public void setMedicationAllergies(List<Medication> medicationAllergies) {
        this.medicationAllergies = medicationAllergies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurame() {
        return patientSurame;
    }

    public void setPatientSurame(String patientSurame) {
        this.patientSurame = patientSurame;
    }
}
