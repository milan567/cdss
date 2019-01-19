package drools.model;

public class Salience {

    public boolean allSymptomsSatisfied = false;

    public int symptomsSatisfied = 0;

    public int symptomsNumber = 0;

    public Salience() {
    }

    public Salience(int symptomsNumber) {
        this.allSymptomsSatisfied = false;
        this.symptomsSatisfied = 0;
        this.symptomsNumber = symptomsNumber;
    }

    public void increaseSatisfiedSymptoms(){
        this.symptomsSatisfied += 1;
        if (this.symptomsSatisfied == this.symptomsNumber){
            System.out.println("True");
            this.allSymptomsSatisfied = true;
        }
    }

    public boolean isAllSymptomsSatisfied() {
        return allSymptomsSatisfied;
    }

    public void setAllSymptomsSatisfied(boolean allSymptomsSatisfied) {
        this.allSymptomsSatisfied = allSymptomsSatisfied;
    }

    public int getSymptomsSatisfied() {
        return symptomsSatisfied;
    }

    public void setSymptomsSatisfied(int symptomsSatisfied) {
        this.symptomsSatisfied = symptomsSatisfied;
    }

    public int getSymptomsNumber() {
        return symptomsNumber;
    }

    public void setSymptomsNumber(int symptomsNumber) {
        this.symptomsNumber = symptomsNumber;
    }
}
