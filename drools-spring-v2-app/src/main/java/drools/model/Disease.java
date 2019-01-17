package drools.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Disease {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "disease")
    private String disease;

    @Column(name = "disease_group")
    private Integer group;

    @ManyToMany
    private List<Symptom> specificSymptoms;

    @ManyToMany
    private List<Symptom> nonSpecificSymptoms;

    @Transient
    private Long matchingSymptoms;


    public Long getMatchingSymptoms() {
        return matchingSymptoms;
    }

    public void setMatchingSymptoms(Long matchingSymptoms) {
        this.matchingSymptoms = matchingSymptoms;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getGroup() {
        return group;
    }

    public Disease(String disease, Integer group, List<Symptom> specificSymptoms, List<Symptom> nonSpecificSymptoms) {
        this.disease = disease;
        this.group = group;
        this.specificSymptoms = specificSymptoms;
        this.nonSpecificSymptoms = nonSpecificSymptoms;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }


    public Integer getId() {
        return id;
    }

    public String getDisease() {
        return disease;
    }

    public Disease(String disease, Integer group) {
        this.disease = disease;
        this.group = group;
        this.nonSpecificSymptoms = new ArrayList<>();
        this.specificSymptoms = new ArrayList<>();
    }

    public Disease() {
        nonSpecificSymptoms = new ArrayList<>();
        specificSymptoms = new ArrayList<>();
    }

    public Disease(String disease, List<Symptom> specificSymptoms, List<Symptom> nonSpecificSymptoms) {
        this.disease = disease;
        this.specificSymptoms = specificSymptoms;
        this.nonSpecificSymptoms = nonSpecificSymptoms;
    }

    public List<Symptom> getSpecificSymptoms() {
        return specificSymptoms;
    }

    public void setSpecificSymptoms(List<Symptom> specificSymptoms) {
        this.specificSymptoms = specificSymptoms;
    }

    public List<Symptom> getNonSpecificSymptoms() {
        return nonSpecificSymptoms;
    }

    public void setNonSpecificSymptoms(List<Symptom> nonSpecificSymptoms) {
        this.nonSpecificSymptoms = nonSpecificSymptoms;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id=" + id +
                ", disease='" + disease + '\'' +
                ", group=" + group +
                ", specificSymptoms=" + specificSymptoms +
                ", nonSpecificSymptoms=" + nonSpecificSymptoms +
                '}';
    }
}
