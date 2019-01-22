package drools.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Examination {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "examination_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    private User doctor;

    @ManyToOne
    private Disease disease;

    @ManyToMany
    private List<Symptom> symptoms;

    @ManyToMany
    private List<Medication> medications;

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Examination{" +
                "id=" + id +
                ", date=" + date +
                ", disease=" + disease +
                ", symptoms=" + symptoms +
                ", medications=" + medications +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public Examination(Date date, User doctor, Disease disease, List<Symptom> symptoms, List<Medication> medications) {
        this.date = date;
        this.doctor = doctor;
        this.disease = disease;
        this.symptoms = symptoms;
        this.medications = medications;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public Examination(Date date, Patient patient, Disease disease, List<Symptom> symptoms, List<Medication> medications) {

        this.date = date;
        this.disease = disease;
        this.symptoms = symptoms;
        this.medications = medications;
    }

    public Examination() {

    }
}
