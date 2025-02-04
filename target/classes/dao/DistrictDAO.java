package dao;

import model.District;
import database.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DistrictDAO {
    
    // Sử dụng Singleton cho DistrictDAO
    public static DistrictDAO getInstance() {
        return new DistrictDAO();
    }

    // Lấy tất cả các huyện từ cơ sở dữ liệu
    public List<District> selectAll() {
        List<District> districtList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM district";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                District district = new District(
                        rs.getInt("district_id"),
                        rs.getString("district_name")
                );
                districtList.add(district);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DistrictDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return districtList;
    }

    // Lấy thông tin huyện theo id
    public District selectById(int districtId) {
        District district = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM district WHERE district_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, districtId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                district = new District(
                        rs.getInt("district_id"),
                        rs.getString("district_name")
                );
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DistrictDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return district;
    }
     // Phương thức lấy ID của huyện từ tên huyện
    public District selectByName(String districtName) {
        District district = null;
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM district WHERE district_name = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, districtName);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    int districtId = rs.getInt("district_id");
                    String name = rs.getString("district_name");
                    district = new District(districtId, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return district;
    }
     // Phương thức lấy tên huyện từ districtId
    public String getDistrictNameById(int districtId) {
        String districtName = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT district_name FROM district WHERE district_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, districtId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                districtName = rs.getString("district_name");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return districtName;
    }

    public int selectIdByName(String districtName) {
    String sql = "SELECT district_id FROM district WHERE district_name = ?";
    try (Connection con = JDBCUtil.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, districtName);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt("district_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Trả về 0 nếu không tìm thấy
}

}
