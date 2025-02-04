package model;

public class District {
    private int districtId;
    private String districtName;

    // Constructor
    public District(int districtId, String districtName) {
        this.districtId = districtId;
        this.districtName = districtName;
    }

    // Getters and Setters
    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public String toString() {
        return districtName; // dùng khi cần hiển thị tên huyện trong các combo box
    }
}
