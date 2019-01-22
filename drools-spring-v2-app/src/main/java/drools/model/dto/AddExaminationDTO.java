package drools.model.dto;

import drools.model.Disease;
import drools.model.Medication;
import drools.model.Symptom;

import java.util.List;

public class AddExaminationDTO {

    private String patientId;
    private List<Symptom> symptoms;
    private Disease disease;
    private List<Medication> medications;

    public AddExaminationDTO() {
    }

    public AddExaminationDTO(String patientId, List<Symptom> symptoms, Disease disease, List<Medication> medications) {
        this.patientId = patientId;
        this.symptoms = symptoms;
        this.disease = disease;
        this.medications = medications;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        return "AddExaminationDTO{" +
                "patientId='" + patientId + '\'' +
                ", symptoms=" + symptoms +
                ", disease=" + disease +
                ", medications=" + medications +
                '}';
    }
}
