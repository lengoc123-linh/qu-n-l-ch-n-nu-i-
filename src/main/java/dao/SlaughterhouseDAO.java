package dao;

import model.Slaughterhouse;
import database.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SlaughterhouseDAO {
    private static SlaughterhouseDAO instance;

    public static SlaughterhouseDAO getInstance() {
        if (instance == null) instance = new SlaughterhouseDAO();
        return instance;
    }

    private final Logger logger = Logger.getLogger(SlaughterhouseDAO.class.getName());

    // INSERT
    public int insert(Slaughterhouse sh) {
        String sql = "INSERT INTO slaughterhouse (name, address, phone, type, capacity, status) VALUES (?, ?, ?, ?, ?, ?)";
        return executeUpdate(sql, sh, false);
    }

    // UPDATE
    public int update(Slaughterhouse sh) {
        String sql = "UPDATE slaughterhouse SET name=?, address=?, phone=?, type=?, capacity=?, status=? WHERE id=?";
        return executeUpdate(sql, sh, true);
    }

    // DELETE
    public int delete(int id) {
        String sql = "DELETE FROM slaughterhouse WHERE id=?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting slaughterhouse: ", e);
        }
        return 0;
    }

   // Lấy tất cả lò giết mổ
    public List<Slaughterhouse> selectAll() {
        List<Slaughterhouse> slaughterhouses = new ArrayList<>();
        String sql = "SELECT * FROM slaughterhouse";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                slaughterhouses.add(new Slaughterhouse(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("type"),
                    rs.getInt("capacity"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slaughterhouses;
    }

    // SELECT BY ID
    public Slaughterhouse selectById(int id) {
        List<Slaughterhouse> result = queryWithConditions("id = ?", id);
        return result.isEmpty() ? null : result.get(0);
    }

    // SEARCH BY TYPE
    public List<Slaughterhouse> searchByType(String type) {
        return queryWithConditions("type = ?", type);
    }

    // SEARCH BY STATUS
    public List<Slaughterhouse> searchByStatus(String status) {
        return queryWithConditions("status = ?", status);
    }

    // GENERAL QUERY WITH CONDITIONS
    public List<Slaughterhouse> queryWithConditions(String condition, Object... params) {
        List<Slaughterhouse> list = new ArrayList<>();
        String sql = "SELECT * FROM slaughterhouse";
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
            logger.log(Level.SEVERE, "Error querying slaughterhouse: ", e);
        }
        return list;
    }

    // COMMON UPDATE EXECUTION
    private int executeUpdate(String sql, Slaughterhouse sh, boolean isUpdate) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, sh.getName());
            pst.setString(2, sh.getAddress());
            pst.setString(3, sh.getPhone());
            pst.setString(4, sh.getType());
            pst.setInt(5, sh.getCapacity());
            pst.setString(6, sh.getStatus());
            if (isUpdate) pst.setInt(7, sh.getId());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing update: ", e);
        }
        return result;
    }

    // MAP RESULTSET TO MODEL
    private Slaughterhouse mapResultSetToModel(ResultSet rs) throws SQLException {
        return new Slaughterhouse(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("address"),
            rs.getString("phone"),
            rs.getString("type"),
            rs.getInt("capacity"),
            rs.getString("status")
        );
    }
}
