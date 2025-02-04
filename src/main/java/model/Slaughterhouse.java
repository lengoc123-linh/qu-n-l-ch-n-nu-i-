package model;

public class Slaughterhouse {
    private int id;                // ID của lò mổ
    private String name;           // Tên lò mổ
    private String address;        // Địa chỉ lò mổ
    private String phone;          // Số điện thoại liên hệ
    private String type;           // Loại lò mổ (Gia súc, Gia cầm, Cả hai)
    private int capacity;          // Sức chứa (số lượng gia súc/gia cầm)
    private String status;         // Trạng thái (Operational, Under Maintenance)

    // Constructors
    public Slaughterhouse(int id, String name, String address, String phone, String type, int capacity, String status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.type = type;
        this.capacity = capacity;
        this.status = status;
    }

    public Slaughterhouse(String name, String address, String phone, String type, int capacity, String status) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.type = type;
        this.capacity = capacity;
        this.status = status;
    }

    public Slaughterhouse() {}

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

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Slaughterhouse [id=" + id + ", name=" + name + ", address=" + address +
               ", phone=" + phone + ", type=" + type + ", capacity=" + capacity + ", status=" + status + "]";
    }
}
