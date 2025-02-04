package model;

public class VeterinaryDepartment {
    private int id;             // ID chi cục thú y
    private String name;        // Tên chi cục thú y
    private String address;     // Địa chỉ
    private String phone;       // Số điện thoại
    private String email;       // Email liên hệ
    private String region;      // Khu vực (Miền Bắc, Miền Nam...)

    // Constructors
    public VeterinaryDepartment(int id, String name, String address, String phone, String email, String region) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.region = region;
    }

    public VeterinaryDepartment(String name, String address, String phone, String email, String region) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.region = region;
    }

    public VeterinaryDepartment() {}

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    @Override
    public String toString() {
        return "VeterinaryDepartment [id=" + id + ", name=" + name + ", address=" + address +
               ", phone=" + phone + ", email=" + email + ", region=" + region + "]";
    }
}
