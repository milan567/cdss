package drools.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public enum Authority {
    DOCTOR,
    ADMIN;

    @Override
    public String toString() {
        switch(this) {
            case DOCTOR: return "DOCTOR";
            case ADMIN: return "ADMIN";
            default: throw new IllegalArgumentException();
        }
    }
}
