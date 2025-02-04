package controller;

import dao.SystemActionHistoryDAO;
import database.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import model.SystemActionHistory;

import java.util.ArrayList;

public class SearchSystemActionHistory {

    // Phương thức khởi tạo đối tượng duy nhất
    public static SearchSystemActionHistory getInstance() {
        return new SearchSystemActionHistory();
    }

    // Tìm kiếm tất cả các hành động hệ thống theo các tiêu chí
    public ArrayList<SystemActionHistory> searchTatCaSystemActionHistory(String text) {
        ArrayList<SystemActionHistory> result = new ArrayList<>();
        ArrayList<SystemActionHistory> systemActionHistories = SystemActionHistoryDAO.getInstance().selectAll();

        for (var action : systemActionHistories) {
            // Kiểm tra nếu các trường trong SystemActionHistory chứa chuỗi tìm kiếm
            if (action.getActionType().toLowerCase().contains(text.toLowerCase()) ||
                action.getDescription().toLowerCase().contains(text.toLowerCase()) ||
                String.valueOf(action.getPerformedBy()).contains(text) || 
                action.getActionTime().toString().contains(text)) {
                result.add(action);
            }
        }
        return result;
    }

    // Tìm kiếm theo actionType
    public ArrayList<SystemActionHistory> searchByActionType(String actionType) {
        ArrayList<SystemActionHistory> result = new ArrayList<>();
        ArrayList<SystemActionHistory> systemActionHistories = SystemActionHistoryDAO.getInstance().selectAll();

        for (var action : systemActionHistories) {
            // Kiểm tra nếu actionType khớp
            if (action.getActionType().toLowerCase().contains(actionType.toLowerCase())) {
                result.add(action);
            }
        }
        return result;
    }

    // Tìm kiếm theo description
    public ArrayList<SystemActionHistory> searchByDescription(String description) {
        ArrayList<SystemActionHistory> result = new ArrayList<>();
        ArrayList<SystemActionHistory> systemActionHistories = SystemActionHistoryDAO.getInstance().selectAll();

        for (var action : systemActionHistories) {
            // Kiểm tra nếu description khớp
            if (action.getDescription().toLowerCase().contains(description.toLowerCase())) {
                result.add(action);
            }
        }
        return result;
    }

    // Tìm kiếm theo performedBy (người thực hiện hành động)
    public ArrayList<SystemActionHistory> searchByPerformedBy(String performedBy) {
        ArrayList<SystemActionHistory> result = new ArrayList<>();
        ArrayList<SystemActionHistory> systemActionHistories = SystemActionHistoryDAO.getInstance().selectAll();

        for (var action : systemActionHistories) {
            // Kiểm tra nếu performedBy khớp
            if (action.getPerformedBy().toLowerCase().contains(performedBy.toLowerCase())) {
                result.add(action);
            }
        }
        return result;
    }

    // Tìm kiếm theo thời gian hành động
    public ArrayList<SystemActionHistory> searchByActionTime(String actionTime) {
        ArrayList<SystemActionHistory> result = new ArrayList<>();
        ArrayList<SystemActionHistory> systemActionHistories = SystemActionHistoryDAO.getInstance().selectAll();

        for (var action : systemActionHistories) {
            // Kiểm tra nếu actionTime chứa chuỗi tìm kiếm
            if (action.getActionTime().toString().contains(actionTime)) {
                result.add(action);
            }
        }
        return result;
    }
}
