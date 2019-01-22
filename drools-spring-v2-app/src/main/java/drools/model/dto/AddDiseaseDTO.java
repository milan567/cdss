package drools.model.dto;

import drools.model.Symptom;

import java.util.List;


public class AddDiseaseDTO
{
    private String id;
    private String disease;
    private Integer group;
    private List<Symptom> symptoms;

    public AddDiseaseDTO() {
    }

    public AddDiseaseDTO(String id, String disease, Integer group, List<Symptom> symptoms) {
        this.id = id;
        this.disease = disease;
        this.group = group;
        this.symptoms = symptoms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }
}

