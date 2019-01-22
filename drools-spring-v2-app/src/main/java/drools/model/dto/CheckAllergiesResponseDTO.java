package drools.model.dto;


import java.util.ArrayList;
import java.util.List;

public class CheckAllergiesResponseDTO {

    public boolean conditionSatisfied;

    public List<String> message = new ArrayList<>();

    public CheckAllergiesResponseDTO() {
    }

    public CheckAllergiesResponseDTO(boolean conditionSatisfied, List<String> message) {
        this.conditionSatisfied = conditionSatisfied;
        this.message = message;
    }

    public boolean isConditionSatisfied() {
        return conditionSatisfied;
    }

    public void setConditionSatisfied(boolean conditionSatisfied) {
        this.conditionSatisfied = conditionSatisfied;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
