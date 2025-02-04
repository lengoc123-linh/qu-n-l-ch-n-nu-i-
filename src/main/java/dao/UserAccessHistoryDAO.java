package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.JDBCUtil;
import java.sql.Timestamp;
import java.time.LocalDate;
import model.UserAccessHistory;

public class UserAccessHistoryDAO implements DAOInterface<UserAccessHistory> {

    // Singleton pattern
    public static UserAccessHistoryDAO getInstance() {
        return new UserAccessHistoryDAO();
    }

    @Override
    public int insert(UserAccessHistory t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO user_access_history (user_name, access_time, farm_id, ip_address, device, action) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getUserName());
            pst.setTimestamp(2, t.getAccessTime());
            pst.setInt(3, t.getFarmId());
            pst.setString(4, t.getIpAddress());
            pst.setString(5, t.getDevice());
            pst.setString(6, t.getAction());
            
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(UserAccessHistory t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE user_access_history SET user_name=?, access_time=?, farm_id=?, ip_address=?, device=?, action=? WHERE access_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getUserName());
            pst.setTimestamp(2, t.getAccessTime());
            pst.setInt(3, t.getFarmId());
            pst.setString(4, t.getIpAddress());
            pst.setString(5, t.getDevice());
            pst.setString(6, t.getAction());
            pst.setInt(7, t.getAccessId());
            
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(UserAccessHistory t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM user_access_history WHERE access_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getAccessId());
            
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<UserAccessHistory> selectAll() {
        ArrayList<UserAccessHistory> ketQua = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM user_access_history";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int accessId = rs.getInt("access_id");
                String userName = rs.getString("user_name");
                Timestamp accessTime = rs.getTimestamp("access_time");
                int farmId = rs.getInt("farm_id");
                String ipAddress = rs.getString("ip_address");
                String device = rs.getString("device");
                String action = rs.getString("action");
                
                UserAccessHistory history = new UserAccessHistory(accessId, userName, accessTime, farmId, ipAddress, device, action);
                ketQua.add(history);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public UserAccessHistory selectById(String t) {
        UserAccessHistory history = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM user_access_history WHERE access_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int accessId = rs.getInt("access_id");
                String userName = rs.getString("user_name");
                Timestamp accessTime = rs.getTimestamp("access_time");
                int farmId = rs.getInt("farm_id");
                String ipAddress = rs.getString("ip_address");
                String device = rs.getString("device");
                String action = rs.getString("action");
                
                history = new UserAccessHistory(accessId, userName, accessTime, farmId, ipAddress, device, action);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return history;
    }

    // Thực hiện thêm một truy vấn để lấy lịch sử truy cập của một người dùng theo thời gian
    public ArrayList<UserAccessHistory> selectByUserName(String userName) {
        ArrayList<UserAccessHistory> ketQua = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM user_access_history WHERE user_name=? ORDER BY access_time DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int accessId = rs.getInt("access_id");
                Timestamp accessTime = rs.getTimestamp("access_time");
                int farmId = rs.getInt("farm_id");
                String ipAddress = rs.getString("ip_address");
                String device = rs.getString("device");
                String action = rs.getString("action");
                
                UserAccessHistory history = new UserAccessHistory(accessId, userName, accessTime, farmId, ipAddress, device, action);
                ketQua.add(history);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    // Phương thức đếm số lần truy cập trong 7 ngày qua
    public int countAccessInLastWeek() {
        int count = 0;
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection con = JDBCUtil.getConnection();
            
            // Câu lệnh SQL để đếm số truy cập trong tuần qua
            String sql = "SELECT COUNT(*) FROM user_access_history WHERE access_time >= NOW() - INTERVAL 7 DAY";
            
            // Chuẩn bị câu lệnh SQL
            PreparedStatement pst = con.prepareStatement(sql);
            
            // Thực thi câu lệnh SQL và lấy kết quả
            ResultSet rs = pst.executeQuery();
            
            // Nếu có kết quả, lấy số lượng truy cập
            if (rs.next()) {
                count = rs.getInt(1);  // Lấy số lượng truy cập từ cột đầu tiên
            }
            
            // Đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
