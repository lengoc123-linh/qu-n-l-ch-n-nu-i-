package dao;

import model.Role;
import java.sql.*;
import java.util.ArrayList;
import database.JDBCUtil;

public class RoleDAO implements DAOInterface<Role> {

    public static RoleDAO getInstance() {
        return new RoleDAO();
    }

    @Override
    public int insert(Role t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO Role (roleName, description) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getRoleName());
            pst.setString(2, t.getDescription());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(Role t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE Role SET description=? WHERE roleName=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getDescription());
            pst.setString(2, t.getRoleName());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(Role t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM Role WHERE roleName=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getRoleName());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<Role> selectAll() {
        ArrayList<Role> ketQua = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM Role";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String roleName = rs.getString("roleName");
                String description = rs.getString("description");
                Role role = new Role(roleName, description);
                ketQua.add(role);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public Role selectById(String roleName) {
        Role role = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM Role WHERE roleName=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, roleName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String description = rs.getString("description");
                role = new Role(roleName, description);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
