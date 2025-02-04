package controller;

import dao.UserAccessHistoryDAO;
import model.UserAccessHistory;

import java.util.ArrayList;

public class SearchUserAccessHistory {

    // Phương thức khởi tạo đối tượng duy nhất
    public static SearchUserAccessHistory getInstance() {
        return new SearchUserAccessHistory();
    }

    // Tìm kiếm tất cả các lịch sử truy cập theo các tiêu chí
    public ArrayList<UserAccessHistory> searchTatCaUserAccessHistory(String text) {
        ArrayList<UserAccessHistory> result = new ArrayList<>();
        ArrayList<UserAccessHistory> userAccessHistories = UserAccessHistoryDAO.getInstance().selectAll();

        for (var history : userAccessHistories) {
            // Kiểm tra nếu các trường trong UserAccessHistory chứa chuỗi tìm kiếm
            if (history.getUserName().toLowerCase().contains(text.toLowerCase()) ||
                String.valueOf(history.getFarmId()).contains(text) || // Kiểm tra farmId
                history.getAction().toLowerCase().contains(text.toLowerCase())) {
                result.add(history);
            }
        }
        return result;
    }

    // Tìm kiếm theo userName
    public ArrayList<UserAccessHistory> searchByUserName(String userName) {
        ArrayList<UserAccessHistory> result = new ArrayList<>();
        ArrayList<UserAccessHistory> userAccessHistories = UserAccessHistoryDAO.getInstance().selectAll();

        for (var history : userAccessHistories) {
            // Kiểm tra nếu userName khớp
            if (history.getUserName().toLowerCase().contains(userName.toLowerCase())) {
                result.add(history);
            }
        }
        return result;
    }

    // Tìm kiếm theo farmId
    public ArrayList<UserAccessHistory> searchByFarmId(Integer farmId) {
        ArrayList<UserAccessHistory> result = new ArrayList<>();
        ArrayList<UserAccessHistory> userAccessHistories = UserAccessHistoryDAO.getInstance().selectAll();

        for (var history : userAccessHistories) {
            // Kiểm tra nếu farmId khớp
            if (history.getFarmId().equals(farmId)) {
                result.add(history);
            }
        }
        return result;
    }

    // Tìm kiếm theo thời gian truy cập
    public ArrayList<UserAccessHistory> searchByAccessTime(String accessTime) {
        ArrayList<UserAccessHistory> result = new ArrayList<>();
        ArrayList<UserAccessHistory> userAccessHistories = UserAccessHistoryDAO.getInstance().selectAll();

        for (var history : userAccessHistories) {
            // Kiểm tra nếu accessTime chứa chuỗi tìm kiếm
            if (history.getAccessTime().toString().contains(accessTime)) {
                result.add(history);
            }
        }
        return result;
    }

    // Tìm kiếm theo action
    public ArrayList<UserAccessHistory> searchByAction(String action) {
        ArrayList<UserAccessHistory> result = new ArrayList<>();
        ArrayList<UserAccessHistory> userAccessHistories = UserAccessHistoryDAO.getInstance().selectAll();

        for (var history : userAccessHistories) {
            // Kiểm tra nếu action khớp
            if (history.getAction().toLowerCase().contains(action.toLowerCase())) {
                result.add(history);
            }
        }
        return result;
    }
}
