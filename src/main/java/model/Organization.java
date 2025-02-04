package model;

public class Organization {
    private int organizationId;
    private String name;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;          // Thêm thuộc tính address
    private String organizationType;
    private int status;              // Sửa kiểu dữ liệu của status thành int

    // Constructors
    public Organization(int organizationId, String name, String contactPerson, String phone, String email, String address, String organizationType, int status) {
        this.organizationId = organizationId;
        this.name = name;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.organizationType = organizationType;
        this.status = status;
    }

    public Organization(String name, String contactPerson, String phone, String email, String address, String organizationType, int status) {
        this.name = name;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.organizationType = organizationType;
        this.status = status;
    }

    public Organization() {}

    // Getters và Setters
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public int getStatus() {  // Sửa kiểu dữ liệu của status
        return status;
    }

    public void setStatus(int status) {  // Setter cho status
        this.status = status;
    }

    @Override
    public String toString() {
        return "Organization [organizationId=" + organizationId + ", name=" + name + ", contactPerson=" + contactPerson
                + ", phone=" + phone + ", email=" + email + ", address=" + address + ", organizationType=" 
                + organizationType + ", status=" + status + "]";
    }
}
