package dao;

import model.AnimalQuarantine;
import database.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalQuarantineDAO {
    private static AnimalQuarantineDAO instance;

    public static AnimalQuarantineDAO getInstance() {
        if (instance == null) instance = new AnimalQuarantineDAO();
        return instance;
    }

    // INSERT
    public int insert(AnimalQuarantine aq) {
        String sql = "INSERT INTO animal_quarantine (location_name, address, type, capacity, status) VALUES (?, ?, ?, ?, ?)";
        return executeUpdate(sql, aq, false);
    }

    // UPDATE
    public int update(AnimalQuarantine aq) {
        String sql = "UPDATE animal_quarantine SET location_name = ?, address = ?, type = ?, capacity = ?, status = ? WHERE id = ?";
        return executeUpdate(sql, aq, true);
    }

    // DELETE
    public int delete(int id) {
        String sql = "DELETE FROM animal_quarantine WHERE id = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

        // Lấy tất cả khu vực kiểm dịch
      public List<AnimalQuarantine> selectAll() {
          List<AnimalQuarantine> quarantines = new ArrayList<>();
          String sql = "SELECT * FROM animal_quarantine";
          try (Connection con = JDBCUtil.getConnection();
               PreparedStatement pst = con.prepareStatement(sql);
               ResultSet rs = pst.executeQuery()) {
              while (rs.next()) {
                  quarantines.add(new AnimalQuarantine(
                      rs.getInt("id"),
                      rs.getString("location_name"),
                      rs.getString("address"),
                      rs.getString("type"),
                      rs.getInt("capacity"),
                      rs.getString("status")
                  ));
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
          return quarantines;
      }

    // SELECT BY ID
    public AnimalQuarantine selectById(int id) {
        List<AnimalQuarantine> result = queryWithConditions("id = ?", id);
        return result.isEmpty() ? null : result.get(0);
    }

    // SEARCH BY DYNAMIC CONDITIONS
    public List<AnimalQuarantine> queryWithConditions(String condition, Object... params) {
        List<AnimalQuarantine> list = new ArrayList<>();
        String sql = "SELECT * FROM animal_quarantine";
        if (condition != null && !condition.trim().isEmpty()) {
            sql += " WHERE " + condition;
        }
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Common method for INSERT and UPDATE
    private int executeUpdate(String sql, AnimalQuarantine aq, boolean isUpdate) {
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, aq.getLocationName());
            pst.setString(2, aq.getAddress());
            pst.setString(3, aq.getType()); // Gán giá trị type
            pst.setInt(4, aq.getCapacity());
            pst.setString(5, aq.getStatus());
            if (isUpdate) pst.setInt(6, aq.getQuarantineId());
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Map ResultSet to Model
    private AnimalQuarantine mapResultSetToModel(ResultSet rs) throws SQLException {
        return new AnimalQuarantine(
                rs.getInt("id"),
                rs.getString("location_name"),
                rs.getString("address"),
                rs.getString("type"), // Đọc giá trị type từ ResultSet
                rs.getInt("capacity"),
                rs.getString("status")
        );
    }
}
