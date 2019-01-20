package drools.model;

public enum MedicationType {

    ANTIBIOTIC,
    ANALGETIC,
    OTHER;

    @Override
    public String toString() {
        switch(this) {
            case ANTIBIOTIC: return "ANTIBIOTIC";
            case ANALGETIC: return "ANALGETIC";
            case OTHER: return "OTHER";
            default: throw new IllegalArgumentException();
        }
    }
}