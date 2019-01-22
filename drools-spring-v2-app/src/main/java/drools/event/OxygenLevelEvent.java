package drools.event;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("firingTime")
@Expires("15m")
public class OxygenLevelEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer patientId;
    private Date firingTime;
    private Integer oxygenLevel;

    public OxygenLevelEvent() {
        this.firingTime = new Date();
    }

    public OxygenLevelEvent(Integer patientId, Integer oxygenLevel) {
        this.patientId = patientId;
        this.firingTime = new Date();
        this.oxygenLevel = oxygenLevel;
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

    public Integer getOxygenLevel() {
        return oxygenLevel;
    }

    public void setOxygenLevel(Integer oxygenLevel) {
        this.oxygenLevel = oxygenLevel;
    }
}
