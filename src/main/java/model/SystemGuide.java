package model;

import java.sql.Timestamp;

public class SystemGuide {

    private int guideId;            // ID của hướng dẫn
    private String title;           // Tiêu đề hướng dẫn
    private String content;         // Nội dung hướng dẫn
    private Timestamp createdAt;    // Thời gian tạo
    private Timestamp updatedAt;    // Thời gian cập nhật

    // Constructor
    public SystemGuide(int guideId, String title, String content, Timestamp createdAt, Timestamp updatedAt) {
        this.guideId = guideId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getter và Setter cho các trường dữ liệu

    public int getGuideId() {
        return guideId;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Override toString() nếu muốn hiển thị thông tin đối tượng dễ dàng hơn
    @Override
    public String toString() {
        return "SystemGuide{" +
                "guideId=" + guideId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}