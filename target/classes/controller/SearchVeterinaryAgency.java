package controller;

import dao.VeterinaryAgencyDAO;
import model.VeterinaryAgency;

import java.util.List;

public class SearchVeterinaryAgency {

    private static SearchVeterinaryAgency instance;

    public static SearchVeterinaryAgency getInstance() {
        if (instance == null) instance = new SearchVeterinaryAgency();
        return instance;
    }

    // Tìm kiếm theo tên
    public List<VeterinaryAgency> searchByName(String name) {
        String condition = "LOWER(name) LIKE ?";
        return VeterinaryAgencyDAO.getInstance().queryWithConditions(condition, "%" + name.toLowerCase() + "%");
    }

    // Tìm kiếm theo địa chỉ
    public List<VeterinaryAgency> searchByAddress(String address) {
        String condition = "LOWER(address) LIKE ?";
        return VeterinaryAgencyDAO.getInstance().queryWithConditions(condition, "%" + address.toLowerCase() + "%");
    }

    // Tìm kiếm theo số điện thoại
    public List<VeterinaryAgency> searchByPhone(String phone) {
        String condition = "phone = ?";
        return VeterinaryAgencyDAO.getInstance().queryWithConditions(condition, phone);
    }

    // Tìm kiếm tổng quát
    public List<VeterinaryAgency> searchByKeyword(String keyword) {
        String condition = "LOWER(name) LIKE ? OR LOWER(address) LIKE ? OR phone LIKE ?";
        return VeterinaryAgencyDAO.getInstance().queryWithConditions(
                condition,
                "%" + keyword.toLowerCase() + "%",
                "%" + keyword.toLowerCase() + "%",
                "%" + keyword + "%"
        );
    }

    // Tìm kiếm nâng cao (kết hợp nhiều tiêu chí)
    public List<VeterinaryAgency> advancedSearch(String name, String address, String phone) {
        StringBuilder condition = new StringBuilder();
        if (name != null && !name.isEmpty()) {
            condition.append("LOWER(name) LIKE ? ");
        }
        if (address != null && !address.isEmpty()) {
            if (condition.length() > 0) condition.append("AND ");
            condition.append("LOWER(address) LIKE ? ");
        }
        if (phone != null && !phone.isEmpty()) {
            if (condition.length() > 0) condition.append("AND ");
            condition.append("phone = ? ");
        }
        return VeterinaryAgencyDAO.getInstance().queryWithConditions(
                condition.toString(),
                name != null ? "%" + name.toLowerCase() + "%" : null,
                address != null ? "%" + address.toLowerCase() + "%" : null,
                phone
        );
    }

    public List<VeterinaryAgency> searchByAgencyName(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<VeterinaryAgency> searchByRegion(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<VeterinaryAgency> searchByStatus(String active) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
