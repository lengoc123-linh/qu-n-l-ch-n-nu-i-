package model;

public class AnimalQuarantine {
    private int quarantineId;
    private String locationName;
    private String address;
    private String type; // Thêm trường loại
    private int capacity; // Sức chứa tối đa
    private String status;

    // Constructors
    public AnimalQuarantine(int quarantineId, String locationName, String address, String type, int capacity, String status) {
        this.quarantineId = quarantineId;
        this.locationName = locationName;
        this.address = address;
        this.type = type; // Gán giá trị loại
        this.capacity = capacity;
        this.status = status;
    }

    public AnimalQuarantine(String locationName, String address, String type, int capacity, String status) {
        this.locationName = locationName;
        this.address = address;
        this.type = type; // Gán giá trị loại
        this.capacity = capacity;
        this.status = status;
    }

    public AnimalQuarantine() {}

    // Getter and Setter
    public int getQuarantineId() { return quarantineId; }
    public void setQuarantineId(int quarantineId) { this.quarantineId = quarantineId; }

    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getType() { return type; } // Getter cho loại
    public void setType(String type) { this.type = type; } // Setter cho loại

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "AnimalQuarantine [quarantineId=" + quarantineId + ", locationName=" + locationName +
               ", address=" + address + ", type=" + type + ", capacity=" + capacity + ", status=" + status + "]";
    }
}
