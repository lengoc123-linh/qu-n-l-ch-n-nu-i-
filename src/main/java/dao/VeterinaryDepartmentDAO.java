package dao;

import model.VeterinaryDepartment;
import database.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinaryDepartmentDAO {
    private static VeterinaryDepartmentDAO instance;

    public static VeterinaryDepartmentDAO getInstance() {
        if (instance == null) {
            instance = new VeterinaryDepartmentDAO();
        }
        return instance;
    }

    // Thêm mới chi cục thú y
    public int insert(VeterinaryDepartment dept) {
        String sql = "INSERT INTO vet_subdepartment (name, address, phone, email, region) VALUES (?, ?, ?, ?, ?)";
        return executeUpdate(sql, dept, false);
    }

    // Cập nhật chi cục thú y
    public int update(VeterinaryDepartment dept) {
        String sql = "UPDATE vet_subdepartment SET name=?, address=?, phone=?, email=?, region=? WHERE id=?";
        return executeUpdate(sql, dept, true);
    }

    // Xóa chi cục thú y theo ID
    public int delete(int departmentId) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM vet_subdepartment WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, departmentId);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    // Lấy tất cả chi cục thú y
    public List<VeterinaryDepartment> selectAll() {
        List<VeterinaryDepartment> departments = new ArrayList<>();
        String sql = "SELECT * FROM vet_subdepartment";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                departments.add(new VeterinaryDepartment(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("region")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    

    // Lấy chi cục thú y theo ID
    public VeterinaryDepartment selectById(int departmentId) {
        List<VeterinaryDepartment> result = queryWithConditions("id = ?", departmentId);
        return result.isEmpty() ? null : result.get(0);
    }

    // Tìm kiếm theo tên, địa chỉ, hoặc khu vực (phổ quát)
    public List<VeterinaryDepartment> searchWithKeyword(String keyword) {
        String condition = "LOWER(name) LIKE ? OR LOWER(address) LIKE ? OR LOWER(region) LIKE ?";
        String param = "%" + keyword.toLowerCase() + "%";
        return queryWithConditions(condition, param, param, param);
    }

    // Query with Dynamic Conditions
    public List<VeterinaryDepartment> queryWithConditions(String condition, Object... params) {
        List<VeterinaryDepartment> departments = new ArrayList<>();
        String sql = "SELECT * FROM vet_subdepartment";
        if (condition != null && !condition.trim().isEmpty()) {
            sql += " WHERE " + condition;
        }

        try (Connection con = JDBCUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] != null) {
                    pst.setObject(i + 1, params[i]);
                }
            }

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                departments.add(new VeterinaryDepartment(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("region")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return departments;
    }

    // Thực thi chung cho insert và update
    private int executeUpdate(String sql, VeterinaryDepartment dept, boolean isUpdate) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, dept.getName());
            pst.setString(2, dept.getAddress());
            pst.setString(3, dept.getPhone());
            pst.setString(4, dept.getEmail());
            pst.setString(5, dept.getRegion());
            if (isUpdate) {
                pst.setInt(6, dept.getId());
            }
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
