package dao;

import model.LivestockHealth;
import database.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivestockHealthDAO {

    private static LivestockHealthDAO instance;

    public static LivestockHealthDAO getInstance() {
        if (instance == null) {
            instance = new LivestockHealthDAO();
        }
        return instance;
    }

    // Insert: Thêm dữ liệu
    public int insert(LivestockHealth health) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO livestock_health (farm_id, disease_name, reported_date, resolved_date, status, comments) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, health.getFarmId());
            pst.setString(2, health.getDiseaseName());
            pst.setString(3, health.getReportedDate());
            pst.setString(4, health.getResolvedDate());
            pst.setString(5, health.getStatus());
            pst.setString(6, health.getComments());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    // Update: Cập nhật dữ liệu
    public int update(LivestockHealth health) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "UPDATE livestock_health SET farm_id=?, disease_name=?, reported_date=?, resolved_date=?, status=?, comments=? " +
                         "WHERE health_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, health.getFarmId());
            pst.setString(2, health.getDiseaseName());
            pst.setString(3, health.getReportedDate());
            pst.setString(4, health.getResolvedDate());
            pst.setString(5, health.getStatus());
            pst.setString(6, health.getComments());
            pst.setInt(7, health.getHealthId());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    // Delete: Xóa dữ liệu
    public int delete(int healthId) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM livestock_health WHERE health_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, healthId);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    // Select All: Lấy toàn bộ dữ liệu
    public List<LivestockHealth> selectAll() {
            List<LivestockHealth> healthList = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM livestock_health";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                healthList.add(new LivestockHealth(
                    rs.getInt("health_id"),
                    rs.getInt("farm_id"),
                    rs.getString("disease_name"),
                    rs.getString("reported_date"),
                    rs.getString("resolved_date"),
                    rs.getString("status"),
                    rs.getString("comments")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return healthList;
    }

    // Select By ID: Lấy dữ liệu theo health_id
    public LivestockHealth selectById(int healthId) {
        List<LivestockHealth> result = queryWithConditions("health_id = ?", String.valueOf(healthId), null, null);
        return result.isEmpty() ? null : result.get(0);
    }

    // Query with Dynamic Conditions
    public List<LivestockHealth> queryWithConditions(String condition, Object... params) {
        List<LivestockHealth> healthList = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM livestock_health";
            if (condition != null && !condition.trim().isEmpty()) {
                sql += " WHERE " + condition;
            }

            PreparedStatement pst = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {  
                healthList.add(new LivestockHealth(
                    rs.getInt("health_id"),
                    rs.getInt("farm_id"),
                    rs.getString("disease_name"),
                    rs.getString("reported_date"),
                    rs.getString("resolved_date"),
                    rs.getString("status"),
                    rs.getString("comments")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return healthList;
    }

}
