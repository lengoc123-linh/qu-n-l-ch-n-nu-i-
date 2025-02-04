package controller;

import dao.LivestockHealthDAO;
import model.LivestockHealth;

import java.util.ArrayList;
import java.util.List;

public class SearchLivestockHealth {

    // Tìm kiếm theo tên bệnh
    public List<LivestockHealth> searchByDiseaseName(String diseaseName) {
        if (diseaseName == null || diseaseName.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String condition = "disease_name LIKE ?";
        return LivestockHealthDAO.getInstance().queryWithConditions(condition, "%" + diseaseName.trim() + "%");
    }

    // Tìm kiếm theo trạng thái
    public List<LivestockHealth> searchByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String condition = "status = ?";
        return LivestockHealthDAO.getInstance().queryWithConditions(condition, status.trim());
    }

    // Tìm kiếm theo ngày báo cáo (Exact Match)
    public List<LivestockHealth> searchByReportedDate(String reportedDate) {
        if (reportedDate == null || reportedDate.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String condition = "reported_date = ?";
        return LivestockHealthDAO.getInstance().queryWithConditions(condition, reportedDate.trim());
    }

    // Tìm kiếm theo khoảng ngày
    public List<LivestockHealth> searchByDateRange(String startDate, String endDate) {
        if (startDate == null || endDate == null || startDate.trim().isEmpty() || endDate.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String condition = "reported_date BETWEEN ? AND ?";
        return LivestockHealthDAO.getInstance().queryWithConditions(condition, startDate.trim(), endDate.trim());
    }

    // Tìm kiếm kết hợp nhiều tiêu chí
    public List<LivestockHealth> advancedSearch(String diseaseName, String status, String startDate, String endDate) {
        List<String> conditions = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (diseaseName != null && !diseaseName.trim().isEmpty()) {
            conditions.add("disease_name LIKE ?");
            params.add("%" + diseaseName.trim() + "%");
        }
        if (status != null && !status.trim().isEmpty()) {
            conditions.add("status = ?");
            params.add(status.trim());
        }
        if (startDate != null && endDate != null && !startDate.trim().isEmpty() && !endDate.trim().isEmpty()) {
            conditions.add("reported_date BETWEEN ? AND ?");
            params.add(startDate.trim());
            params.add(endDate.trim());
        }

        String conditionString = String.join(" AND ", conditions);
        return LivestockHealthDAO.getInstance().queryWithConditions(
            conditionString,
            params.toArray()
        );
    }
}
