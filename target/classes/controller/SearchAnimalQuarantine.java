package controller;

import dao.AnimalQuarantineDAO;
import model.AnimalQuarantine;

import java.util.List;

public class SearchAnimalQuarantine {

    // Tìm kiếm theo tên địa điểm
    public List<AnimalQuarantine> searchByLocationName(String locationName) {
        String condition = "LOWER(location_name) LIKE ?";
        return AnimalQuarantineDAO.getInstance().queryWithConditions(condition, "%" + locationName.toLowerCase() + "%");
    }

    // Tìm kiếm theo sức chứa (lớn hơn hoặc bằng)
    public List<AnimalQuarantine> searchByCapacity(int capacity) {
        String condition = "capacity >= ?";
        return AnimalQuarantineDAO.getInstance().queryWithConditions(condition, capacity);
    }

    // Tìm kiếm theo trạng thái
    public List<AnimalQuarantine> searchByStatus(String status) {
        String condition = "LOWER(status) = ?";
        return AnimalQuarantineDAO.getInstance().queryWithConditions(condition, status.toLowerCase());
    }

    // Tìm kiếm theo loại (type)
    public List<AnimalQuarantine> searchByType(String type) {
        String condition = "LOWER(type) = ?";
        return AnimalQuarantineDAO.getInstance().queryWithConditions(condition, type.toLowerCase());
    }

    // Tìm kiếm tổng quát theo từ khóa
    public List<AnimalQuarantine> searchByKeyword(String keyword) {
        String condition = "LOWER(location_name) LIKE ? OR LOWER(address) LIKE ? OR LOWER(type) LIKE ? OR LOWER(status) LIKE ?";
        return AnimalQuarantineDAO.getInstance().queryWithConditions(
                condition,
                "%" + keyword.toLowerCase() + "%",
                "%" + keyword.toLowerCase() + "%",
                "%" + keyword.toLowerCase() + "%",
                "%" + keyword.toLowerCase() + "%"
        );
    }

    // Tìm kiếm nâng cao
    public List<AnimalQuarantine> advancedSearch(String locationName, Integer capacity, String type, String status) {
        StringBuilder condition = new StringBuilder();
        if (locationName != null && !locationName.isEmpty()) {
            condition.append("LOWER(location_name) LIKE ? ");
        }
        if (capacity != null) {
            if (condition.length() > 0) condition.append("AND ");
            condition.append("capacity >= ? ");
        }
        if (type != null && !type.isEmpty()) {
            if (condition.length() > 0) condition.append("AND ");
            condition.append("LOWER(type) = ? ");
        }
        if (status != null && !status.isEmpty()) {
            if (condition.length() > 0) condition.append("AND ");
            condition.append("LOWER(status) = ? ");
        }
        return AnimalQuarantineDAO.getInstance().queryWithConditions(
                condition.toString(),
                locationName != null ? "%" + locationName.toLowerCase() + "%" : null,
                capacity,
                type != null ? type.toLowerCase() : null,
                status != null ? status.toLowerCase() : null
        );
    }
}
