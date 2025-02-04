package model;

import java.sql.Timestamp;
import java.util.Objects;

public class SystemActionHistory {

    private int actionId;            // ID của hành động hệ thống
    private Timestamp actionTime;    // Thời gian thực hiện hành động
    private String actionType;      // Loại hành động (Cập nhật, Xóa, Thêm mới)
    private String description;     // Mô tả chi tiết hành động
    private String performedBy;     // Người thực hiện hành động (tham chiếu đến Account)

    // Constructor mặc định và Constructor đầy đủ
    public SystemActionHistory() {
    }

    public SystemActionHistory(int actionId, Timestamp actionTime, String actionType, String description, String performedBy) {
        this.actionId = actionId;
        this.actionTime = actionTime;
        this.actionType = actionType;
        this.description = description;
        this.performedBy = performedBy;
    }
     public SystemActionHistory( Timestamp actionTime, String actionType, String description, String performedBy) {
        this.actionTime = actionTime;
        this.actionType = actionType;
        this.description = description;
        this.performedBy = performedBy;
    }

    // Getter and Setter methods
    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public Timestamp getActionTime() {
        return actionTime;
    }

    public void setActionTime(Timestamp actionTime) {
        this.actionTime = actionTime;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionId, actionTime, actionType, description, performedBy);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SystemActionHistory that = (SystemActionHistory) obj;
        return actionId == that.actionId &&
                Objects.equals(actionTime, that.actionTime) &&
                Objects.equals(actionType, that.actionType) &&
                Objects.equals(description, that.description) &&
                Objects.equals(performedBy, that.performedBy);
    }

    @Override
    public String toString() {
        return "SystemActionHistory{" +
                "actionId=" + actionId +
                ", actionTime=" + actionTime +
                ", actionType='" + actionType + '\'' +
                ", description='" + description + '\'' +
                ", performedBy='" + performedBy + '\'' +
                '}';
    }
}
