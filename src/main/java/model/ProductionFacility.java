package model;

public class ProductionFacility {
    private int facilityId;
    private String facilityName;
    private String address;
    private int districtId;
    private int communeId;
    private String contactPerson;
    private String contactPhone;

    // Constructor có tham số đầy đủ
    public ProductionFacility(int facilityId, String facilityName, String address, int districtId, int communeId, String contactPerson, String contactPhone) {
        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.address = address;
        this.districtId = districtId;
        this.communeId = communeId;
        this.contactPerson = contactPerson;
        this.contactPhone = contactPhone;
    }

    // Constructor không có ID (cho trường hợp thêm mới)
    public ProductionFacility(String facilityName, String address, int districtId, int communeId, String contactPerson, String contactPhone) {
        this.facilityName = facilityName;
        this.address = address;
        this.districtId = districtId;
        this.communeId = communeId;
        this.contactPerson = contactPerson;
        this.contactPhone = contactPhone;
    }

    // Constructor mặc định
    public ProductionFacility() {}

    // Getters and Setters
    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getCommuneId() {
        return communeId;
    }

    public void setCommuneId(int communeId) {
        this.communeId = communeId;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public String toString() {
        return "ProductionFacility [facilityId=" + facilityId + ", facilityName=" + facilityName + ", address=" + address 
                + ", districtId=" + districtId + ", communeId=" + communeId + ", contactPerson=" + contactPerson 
                + ", contactPhone=" + contactPhone + "]";
    }
}
