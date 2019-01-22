package drools.event;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("firingTime")
@Expires("10s")
public class HeartBeatEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer patientId;
    private Date firingTime;

    public HeartBeatEvent() {
        this.firingTime = new Date();
    }

    public HeartBeatEvent(Integer patientId) {
        this.patientId = patientId;
        this.firingTime = new Date();
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
}
