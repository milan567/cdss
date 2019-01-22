package drools.model;

public enum SymptomType {
    SPECIFIC,
    NONSPECIFIC;

    @Override
    public String toString() {
        switch(this) {
            case SPECIFIC: return "SPECIFIC";
            case NONSPECIFIC: return "NON SPECIFIC";
            default: throw new IllegalArgumentException();
        }
    }
}
