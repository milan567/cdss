package drools.model.dto;

import drools.model.Disease;

public class PotentialDiseasesResponseDTO {

    public Disease disease;

    public int satisfiedSymptoms;

    public PotentialDiseasesResponseDTO() {
    }

    public PotentialDiseasesResponseDTO(Disease disease, int satisfiedSymptoms) {
        this.disease = disease;
        this.satisfiedSymptoms = satisfiedSymptoms;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public int getSatisfiedSymptoms() {
        return satisfiedSymptoms;
    }

    public void setSatisfiedSymptoms(int satisfiedSymptoms) {
        this.satisfiedSymptoms = satisfiedSymptoms;
    }
}
