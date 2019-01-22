package drools.model;

public enum IssueType {
    OXYGEN_PROBLEM,
    RAPID_HEART_RATE,
    URGENT_DIALYSIS;

    @Override
    public String toString() {
        switch(this) {
            case OXYGEN_PROBLEM: return "OXYGEN_PROBLEM";
            case RAPID_HEART_RATE: return "RAPID_HEART_RATE";
            case URGENT_DIALYSIS: return "URGENT_DIALYSIS";
            default: throw new IllegalArgumentException();
        }
    }
}
