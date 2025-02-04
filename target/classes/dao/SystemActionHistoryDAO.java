package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.JDBCUtil;
import model.SystemActionHistory;

public class SystemActionHistoryDAO implements DAOInterface<SystemActionHistory> {

    // Singleton pattern
    public static SystemActionHistoryDAO getInstance() {
        return new SystemActionHistoryDAO();
    }
      // Phương thức để ghi lại hành động của người dùng vào bảng system_action_history
    public void logUserAction(String actionType, String description, String performedBy) {
        SystemActionHistory actionHistory = new SystemActionHistory(
            new java.sql.Timestamp(System.currentTimeMillis()),  // Thời gian hành động
            actionType,  // Loại hành động (Thêm, Sửa, Xóa)
            description,  // Mô tả hành động
            performedBy   // Người thực hiện
        );
        // Gọi phương thức insert để ghi lại hành động vào cơ sở dữ liệu
        insert(actionHistory);
    }

    @Override
    public int insert(SystemActionHistory t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO system_action_history (action_time, action_type, description, performed_by) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, t.getActionTime());
            pst.setString(2, t.getActionType());
            pst.setString(3, t.getDescription());
            pst.setString(4, t.getPerformedBy());
            
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(SystemActionHistory t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE system_action_history SET action_time=?, action_type=?, description=?, performed_by=? WHERE action_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, t.getActionTime());
            pst.setString(2, t.getActionType());
            pst.setString(3, t.getDescription());
            pst.setString(4, t.getPerformedBy());
            pst.setInt(5, t.getActionId());
            
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(SystemActionHistory t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM system_action_history WHERE action_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getActionId());
            
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<SystemActionHistory> selectAll() {
        ArrayList<SystemActionHistory> ketQua = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM system_action_history";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int actionId = rs.getInt("action_id");
                java.sql.Timestamp actionTime = rs.getTimestamp("action_time");
                String actionType = rs.getString("action_type");
                String description = rs.getString("description");
                String performedBy = rs.getString("performed_by");
                
                SystemActionHistory actionHistory = new SystemActionHistory(actionId, actionTime, actionType, description, performedBy);
                ketQua.add(actionHistory);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public SystemActionHistory selectById(String t) {
        SystemActionHistory actionHistory = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM system_action_history WHERE action_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int actionId = rs.getInt("action_id");
                java.sql.Timestamp actionTime = rs.getTimestamp("action_time");
                String actionType = rs.getString("action_type");
                String description = rs.getString("description");
                String performedBy = rs.getString("performed_by");
                
                actionHistory = new SystemActionHistory(actionId, actionTime, actionType, description, performedBy);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actionHistory;
    }

    // Thực hiện thêm một truy vấn để lấy lịch sử hành động của hệ thống theo người thực hiện
    public ArrayList<SystemActionHistory> selectByPerformedBy(String performedBy) {
        ArrayList<SystemActionHistory> ketQua = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM system_action_history WHERE performed_by=? ORDER BY action_time DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, performedBy);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int actionId = rs.getInt("action_id");
                java.sql.Timestamp actionTime = rs.getTimestamp("action_time");
                String actionType = rs.getString("action_type");
                String description = rs.getString("description");
                String performedByValue = rs.getString("performed_by");
                
                SystemActionHistory actionHistory = new SystemActionHistory(actionId, actionTime, actionType, description, performedByValue);
                ketQua.add(actionHistory);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
 // Phương thức đếm số tác vụ trong tuần qua
    public int countActionsInLastWeek() {
        int count = 0;
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection con = JDBCUtil.getConnection();
            
            // Câu lệnh SQL để đếm số tác vụ trong tuần qua
            String sql = "SELECT COUNT(*) FROM system_action_history WHERE action_time >= NOW() - INTERVAL 7 DAY";
            
            // Chuẩn bị câu lệnh SQL
            PreparedStatement pst = con.prepareStatement(sql);
            
            // Thực thi câu lệnh SQL và lấy kết quả
            ResultSet rs = pst.executeQuery();
            
            // Nếu có kết quả, lấy số lượng tác vụ
            if (rs.next()) {
                count = rs.getInt(1);  // Lấy số lượng tác vụ từ cột đầu tiên
            }
            
            // Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
}
