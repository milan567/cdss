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

    @ManyToMany(fetch =FetchType.EAGER)
    private List<Symptom> symptoms;

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

    public Disease(String disease, Integer group, List<Symptom> symptoms) {
        this.disease = disease;
        this.group = group;
        this.symptoms = symptoms;
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
        this.symptoms = new ArrayList<>();
    }

    public Disease() {
        this.symptoms = new ArrayList<>();
    }

    public Disease(String disease, List<Symptom> symptoms) {
        this.disease = disease;
        this.symptoms = symptoms;
    }

    public List<Symptom> getSymptoms() {
        return this.symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id=" + id +
                ", disease='" + disease + '\'' +
                ", group=" + group +
                ", symptoms=" + symptoms +
                '}';
    }
}
