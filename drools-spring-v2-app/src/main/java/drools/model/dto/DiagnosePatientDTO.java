package drools.model.dto;



import drools.model.Symptom;

import java.util.List;

public class DiagnosePatientDTO {

    private String id;
    private List<Symptom> symptoms;

    public DiagnosePatientDTO(String id, List<Symptom> symptoms) {
        this.id = id;
        this.symptoms = symptoms;
    }

    @Override
    public String toString() {
        return "DiagnosePatientDTO{" +
                "id='" + id + '\'' +
                ", symptoms=" + symptoms +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public DiagnosePatientDTO() {

    }
}
