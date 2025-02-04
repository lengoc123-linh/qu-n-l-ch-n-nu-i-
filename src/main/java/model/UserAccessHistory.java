package model;

import java.sql.Timestamp;
import java.util.Objects;

public class UserAccessHistory {

    private int accessId;           // ID của lần truy cập
    private String userName;        // Tên người dùng
    private Timestamp accessTime;   // Thời gian truy cập
    private Integer farmId;         // Mã cơ sở farm
    private String ipAddress;       // Địa chỉ IP
    private String device;          // Thiết bị truy cập
    private String action;          // Hành động cụ thể

    // Constructor mặc định
    public UserAccessHistory() {
        super();
    }

    // Constructor đầy đủ
    public UserAccessHistory(int accessId, String userName, Timestamp accessTime, Integer farmId, String ipAddress, String device, String action) {
        this.accessId = accessId;
        this.userName = userName;
        this.accessTime = accessTime;
        this.farmId = farmId;
        this.ipAddress = ipAddress;
        this.device = device;
        this.action = action;
    }

    // Getter và Setter
    public int getAccessId() {
        return accessId;
    }

    public void setAccessId(int accessId) {
        this.accessId = accessId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Timestamp accessTime) {
        this.accessTime = accessTime;
    }

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        this.farmId = farmId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessId, userName, accessTime, farmId, ipAddress, device, action);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserAccessHistory that = (UserAccessHistory) obj;
        return accessId == that.accessId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(accessTime, that.accessTime) &&
                Objects.equals(farmId, that.farmId) &&
                Objects.equals(ipAddress, that.ipAddress) &&
                Objects.equals(device, that.device) &&
                Objects.equals(action, that.action);
    }

    @Override
    public String toString() {
        return "UserAccessHistory{" +
                "accessId=" + accessId +
                ", userName='" + userName + '\'' +
                ", accessTime=" + accessTime +
                ", farmId=" + farmId +
                ", ipAddress='" + ipAddress + '\'' +
                ", device='" + device + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
