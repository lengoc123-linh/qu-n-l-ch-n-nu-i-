package model;

public class VeterinaryAgency {
    private int id;                 // ID đại lý thuốc
    private String name;            // Tên đại lý
    private String address;         // Địa chỉ
    private String phone;           // Số điện thoại
    private String email;           // Email liên hệ
    private String licenseNumber;   // Số giấy phép
    private String status;          // Trạng thái (Active, Inactive)

    // Constructors
    public VeterinaryAgency(int id, String name, String address, String phone, String email, String licenseNumber, String status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.status = status;
    }

    public VeterinaryAgency(String name, String address, String phone, String email, String licenseNumber, String status) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.status = status;
    }

    public VeterinaryAgency() {}

    // Getters và Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "VeterinaryAgency [id=" + id + ", name=" + name + ", address=" + address +
               ", phone=" + phone + ", email=" + email + ", licenseNumber=" + licenseNumber + 
               ", status=" + status + "]";
    }
}
