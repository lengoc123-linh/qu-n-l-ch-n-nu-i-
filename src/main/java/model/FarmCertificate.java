package model;

public class FarmCertificate {
    private int certificateId;
    private int farmId;
    private String certificateType;
    private String issueDate;
    private String expiryDate;
    private String issuer;
    private int status;

    // Constructors
    public FarmCertificate(int certificateId, int farmId, String certificateType, String issueDate, String expiryDate, String issuer, int status) {
        this.certificateId = certificateId;
        this.farmId = farmId;
        this.certificateType = certificateType;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.issuer = issuer;
        this.status = status;
    }

    public FarmCertificate(int farmId, String certificateType, String issueDate, String expiryDate, String issuer, int status) {
        this.farmId = farmId;
        this.certificateType = certificateType;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.issuer = issuer;
        this.status = status;
    }

    public FarmCertificate() {}

    // Getter and Setter
    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FarmCertificate [certificateId=" + certificateId + ", farmId=" + farmId + ", certificateType=" + certificateType
                + ", issueDate=" + issueDate + ", expiryDate=" + expiryDate + ", issuer=" + issuer + ", status=" + status + "]";
    }
}
