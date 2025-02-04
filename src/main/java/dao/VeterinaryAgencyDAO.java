package dao;

import model.VeterinaryAgency;
import database.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinaryAgencyDAO {
    private static VeterinaryAgencyDAO instance;

    public static VeterinaryAgencyDAO getInstance() {
        if (instance == null) instance = new VeterinaryAgencyDAO();
        return instance;
    }

    // Phương thức chung thực thi INSERT và UPDATE
    private int executeUpdate(String sql, VeterinaryAgency agency, boolean isUpdate) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, agency.getName());
            pst.setString(2, agency.getAddress());
            pst.setString(3, agency.getPhone());
            pst.setString(4, agency.getEmail());
            pst.setString(5, agency.getLicenseNumber());
            pst.setString(6, agency.getStatus());
            if (isUpdate) pst.setInt(7, agency.getId());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insert(VeterinaryAgency agency) {
        String sql = "INSERT INTO vet_medicine_agency (name, address, phone, email, license_number, status) VALUES (?, ?, ?, ?, ?, ?)";
        return executeUpdate(sql, agency, false);
    }

    public int update(VeterinaryAgency agency) {
        String sql = "UPDATE vet_medicine_agency SET name=?, address=?, phone=?, email=?, license_number=?, status=? WHERE id=?";
        return executeUpdate(sql, agency, true);
    }

    public int delete(int agencyId) {
        String sql = "DELETE FROM vet_medicine_agency WHERE id=?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, agencyId);
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Lấy tất cả đại lý thú y
    public List<VeterinaryAgency> selectAll() {
        List<VeterinaryAgency> agencies = new ArrayList<>();
        String sql = "SELECT * FROM vet_medicine_agency";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                agencies.add(new VeterinaryAgency(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("license_number"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agencies;
    }


    public VeterinaryAgency selectById(int agencyId) {
        List<VeterinaryAgency> result = queryWithConditions("id = ?", agencyId);
        return result.isEmpty() ? null : result.get(0);
    }

    public List<VeterinaryAgency> queryWithConditions(String condition, Object... params) {
        List<VeterinaryAgency> agencies = new ArrayList<>();
        String sql = "SELECT * FROM vet_medicine_agency";
        if (condition != null && !condition.trim().isEmpty()) {
            sql += " WHERE " + condition;
        }
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                agencies.add(mapResultSetToModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agencies;
    }

    private VeterinaryAgency mapResultSetToModel(ResultSet rs) throws SQLException {
        return new VeterinaryAgency(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getString("license_number"),
                rs.getString("status")
        );
    }
}
