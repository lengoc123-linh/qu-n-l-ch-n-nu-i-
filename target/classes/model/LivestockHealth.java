package model;

public class LivestockHealth {
    private int healthId;
    private int farmId;
    private String diseaseName;
    private String reportedDate;
    private String resolvedDate;
    private String status;
    private String comments;

    // Constructors
    public LivestockHealth(int healthId, int farmId, String diseaseName, String reportedDate, String resolvedDate, String status, String comments) {
        this.healthId = healthId;
        this.farmId = farmId;
        this.diseaseName = diseaseName;
        this.reportedDate = reportedDate;
        this.resolvedDate = resolvedDate;
        this.status = status;
        this.comments = comments;
    }

    public LivestockHealth(int farmId, String diseaseName, String reportedDate, String resolvedDate, String status, String comments) {
        this.farmId = farmId;
        this.diseaseName = diseaseName;
        this.reportedDate = reportedDate;
        this.resolvedDate = resolvedDate;
        this.status = status;
        this.comments = comments;
    }

    public LivestockHealth() {}

    // Getter and Setter
    public int getHealthId() {
        return healthId;
    }

    public void setHealthId(int healthId) {
        this.healthId = healthId;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(String reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(String resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "LivestockHealth [healthId=" + healthId + ", farmId=" + farmId + ", diseaseName=" + diseaseName
                + ", reportedDate=" + reportedDate + ", resolvedDate=" + resolvedDate + ", status=" + status
                + ", comments=" + comments + "]";
    }
}
