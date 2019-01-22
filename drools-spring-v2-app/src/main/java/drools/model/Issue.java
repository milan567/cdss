package drools.model;

public class Issue {
    private IssueType issueType;
    private Integer patientId;

    public Issue() {}

    public Issue(IssueType issueType, Integer patientId) {
        this.issueType = issueType;
        this.patientId = patientId;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}
