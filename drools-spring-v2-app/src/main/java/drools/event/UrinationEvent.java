package drools.event;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("firingTime")
@Expires("12h")
public class UrinationEvent {
    private static final long serialVersionUID = 1L;

    private Integer patientId;
    private Date firingTime;
    private Integer amount;

    public UrinationEvent() {
        this.firingTime = new Date();
    }

    public UrinationEvent(Integer patientId, Integer amount) {
        this.patientId = patientId;
        this.firingTime = new Date();
        this.amount = amount;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Date getFiringTime() {
        return firingTime;
    }

    public void setFiringTime(Date firingTime) {
        this.firingTime = firingTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
