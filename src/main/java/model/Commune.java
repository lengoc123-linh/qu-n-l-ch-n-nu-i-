package model;

public class Commune {
    private int communeId;    // ID của xã
    private String communeName; // Tên xã
    private int districtId;    // ID của huyện (mỗi xã sẽ có một huyện tương ứng)

    // Constructor với các tham số
    public Commune(int communeId, String communeName, int districtId) {
        this.communeId = communeId;
        this.communeName = communeName;
        this.districtId = districtId;
    }
    public Commune(int communeId, String communeName) {
        this.communeId = communeId;
        this.communeName = communeName;
    }

    // Constructor mặc định
    public Commune() {}

    // Getter và Setter
    public int getCommuneId() {
        return communeId;
    }

    public void setCommuneId(int communeId) {
        this.communeId = communeId;
    }

    public String getCommuneName() {
        return communeName;
    }

    public void setCommuneName(String communeName) {
        this.communeName = communeName;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    @Override
    public String toString() {
        return communeName; // Sử dụng khi hiển thị tên xã trong ComboBox
    }
}
