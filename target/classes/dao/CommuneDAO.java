package dao;

import model.Commune;
import database.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommuneDAO {

    // Sử dụng Singleton cho CommuneDAO
    public static CommuneDAO getInstance() {
        return new CommuneDAO();
    }

    // Lấy tất cả các xã từ cơ sở dữ liệu
    public List<Commune> selectAll() {
        List<Commune> communeList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM commune";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Commune commune = new Commune(
                        rs.getInt("commune_id"),
                        rs.getString("commune_name"),
                        rs.getInt("district_id")
                );
                communeList.add(commune);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CommuneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return communeList;
    }

    // Lấy thông tin xã theo id
    public Commune selectById(int communeId) {
        Commune commune = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM commune WHERE commune_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, communeId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                commune = new Commune(
                        rs.getInt("commune_id"),
                        rs.getString("commune_name"),
                        rs.getInt("district_id")
                );
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CommuneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commune;
    }
    
    // Phương thức lấy tên xã từ communeId
    public String getCommuneNameById(int communeId) {
        String communeName = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT commune_name FROM commune WHERE commune_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, communeId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                communeName = rs.getString("commune_name");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return communeName;
    }

   // Phương thức lấy danh sách xã theo ID huyện
    public ArrayList<Commune> selectByDistrictId(int districtId) {
        ArrayList<Commune> communes = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM commune WHERE district_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, districtId);  // Gán districtId vào câu truy vấn
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Commune commune = new Commune();
                commune.setCommuneId(rs.getInt("commune_id"));
                commune.setCommuneName(rs.getString("commune_name"));
                commune.setDistrictId(rs.getInt("district_id"));
                communes.add(commune);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return communes;
    }
   
    // Phương thức lấy ID của xã từ tên xã
    public Commune selectByName(String communeName) {
        Commune commune = null;
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM commune WHERE commune_name = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, communeName);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    int communeId = rs.getInt("commune_id");
                    String name = rs.getString("commune_name");
                    commune = new Commune(communeId, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commune;
    }
    public int selectIdByName(String communeName) {
    String sql = "SELECT commune_id FROM commune WHERE commune_name = ?";
    try (Connection con = JDBCUtil.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, communeName);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt("commune_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Trả về 0 nếu không tìm thấy
}

}
