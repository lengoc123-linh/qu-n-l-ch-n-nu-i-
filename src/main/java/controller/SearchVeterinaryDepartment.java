package controller;

import dao.VeterinaryDepartmentDAO;
import model.VeterinaryDepartment;

import java.util.List;

public class SearchVeterinaryDepartment {

    // Singleton Instance
    private static SearchVeterinaryDepartment instance;

    public static SearchVeterinaryDepartment getInstance() {
        if (instance == null) {
            instance = new SearchVeterinaryDepartment();
        }
        return instance;
    }

    // Tìm kiếm theo tên
    public List<VeterinaryDepartment> searchByName(String name) {
        String condition = "LOWER(name) LIKE ?";
        String param = "%" + name.toLowerCase() + "%";
        return VeterinaryDepartmentDAO.getInstance().queryWithConditions(condition, param);
    }

    // Tìm kiếm theo khu vực
    public List<VeterinaryDepartment> searchByRegion(String region) {
        String condition = "region = ?";
        return VeterinaryDepartmentDAO.getInstance().queryWithConditions(condition, region);
    }

    // Tìm kiếm theo địa chỉ
    public List<VeterinaryDepartment> searchByAddress(String address) {
        String condition = "LOWER(address) LIKE ?";
        String param = "%" + address.toLowerCase() + "%";
        return VeterinaryDepartmentDAO.getInstance().queryWithConditions(condition, param);
    }

    // Tìm kiếm với từ khóa tổng quát
    public List<VeterinaryDepartment> searchByKeyword(String keyword) {
        String condition = "LOWER(name) LIKE ? OR LOWER(address) LIKE ? OR LOWER(region) LIKE ?";
        String param = "%" + keyword.toLowerCase() + "%";
        return VeterinaryDepartmentDAO.getInstance().queryWithConditions(condition, param, param, param);
    }

    // Tìm kiếm nâng cao (kết hợp nhiều tiêu chí)
    public List<VeterinaryDepartment> advancedSearch(String name, String region, String address) {
        StringBuilder condition = new StringBuilder();
        if (name != null && !name.isEmpty()) {
            condition.append("LOWER(name) LIKE ? ");
        }
        if (region != null && !region.isEmpty()) {
            if (condition.length() > 0) condition.append("AND ");
            condition.append("region = ? ");
        }
        if (address != null && !address.isEmpty()) {
            if (condition.length() > 0) condition.append("AND ");
            condition.append("LOWER(address) LIKE ? ");
        }

        return VeterinaryDepartmentDAO.getInstance().queryWithConditions(
            condition.toString(),
            name != null ? "%" + name.toLowerCase() + "%" : null,
            region,
            address != null ? "%" + address.toLowerCase() + "%" : null
        );
    }
}
