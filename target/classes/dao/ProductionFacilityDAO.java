package dao;

import model.ProductionFacility;
import database.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductionFacilityDAO {

    // Sử dụng Singleton cho lớp ProductionFacilityDAO
    public static ProductionFacilityDAO getInstance() {
        return new ProductionFacilityDAO();
    }

    // Thêm cơ sở sản xuất
    public int insert(ProductionFacility facility) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO production_facility (facility_name, address, district_id, commune_id, contact_person, contact_phone) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, facility.getFacilityName());
            pst.setString(2, facility.getAddress());
            pst.setInt(3, facility.getDistrictId());
            pst.setInt(4, facility.getCommuneId());
            pst.setString(5, facility.getContactPerson());
            pst.setString(6, facility.getContactPhone());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductionFacilityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Xóa cơ sở sản xuất theo ID
    public int delete(int facilityId) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM production_facility WHERE facility_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, facilityId);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductionFacilityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Cập nhật thông tin cơ sở sản xuất
    public int update(ProductionFacility facility) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE production_facility SET facility_name = ?, address = ?, district_id = ?, commune_id = ?, contact_person = ?, contact_phone = ? WHERE facility_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, facility.getFacilityName());
            pst.setString(2, facility.getAddress());
            pst.setInt(3, facility.getDistrictId());
            pst.setInt(4, facility.getCommuneId());
            pst.setString(5, facility.getContactPerson());
            pst.setString(6, facility.getContactPhone());
            pst.setInt(7, facility.getFacilityId());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductionFacilityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Lấy tất cả cơ sở sản xuất
    public List<ProductionFacility> selectAll() {
        List<ProductionFacility> facilityList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM production_facility";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ProductionFacility facility = new ProductionFacility(
                        rs.getInt("facility_id"),
                        rs.getString("facility_name"),
                        rs.getString("address"),
                        rs.getInt("district_id"),
                        rs.getInt("commune_id"),
                        rs.getString("contact_person"),
                        rs.getString("contact_phone")
                );
                facilityList.add(facility);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductionFacilityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facilityList;
    }

    // Lấy cơ sở sản xuất theo ID
    public ProductionFacility selectById(int facilityId) {
        ProductionFacility facility = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM production_facility WHERE facility_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, facilityId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                facility = new ProductionFacility(
                        rs.getInt("facility_id"),
                        rs.getString("facility_name"),
                        rs.getString("address"),
                        rs.getInt("district_id"),
                        rs.getInt("commune_id"),
                        rs.getString("contact_person"),
                        rs.getString("contact_phone")
                );
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductionFacilityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facility;
    }

    // Tìm kiếm cơ sở sản xuất theo huyện
    public List<ProductionFacility> searchByDistrict(int districtId) {
        List<ProductionFacility> facilityList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM production_facility WHERE district_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, districtId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ProductionFacility facility = new ProductionFacility(
                        rs.getInt("facility_id"),
                        rs.getString("facility_name"),
                        rs.getString("address"),
                        rs.getInt("district_id"),
                        rs.getInt("commune_id"),
                        rs.getString("contact_person"),
                        rs.getString("contact_phone")
                );
                facilityList.add(facility);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductionFacilityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facilityList;
    }

    // Tìm kiếm cơ sở sản xuất theo xã
    public List<ProductionFacility> searchByCommune(int communeId) {
        List<ProductionFacility> facilityList = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM production_facility WHERE commune_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, communeId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ProductionFacility facility = new ProductionFacility(
                        rs.getInt("facility_id"),
                        rs.getString("facility_name"),
                        rs.getString("address"),
                        rs.getInt("district_id"),
                        rs.getInt("commune_id"),
                        rs.getString("contact_person"),
                        rs.getString("contact_phone")
                );
                facilityList.add(facility);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductionFacilityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facilityList;
    }
    public List<ProductionFacility> searchByName(String name) {
    List<ProductionFacility> facilities = new ArrayList<>();
    try {
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM production_facility WHERE facility_name LIKE ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, "%" + name + "%");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            facilities.add(new ProductionFacility(
                rs.getInt("facility_id"),
                rs.getString("facility_name"),
                rs.getString("address"),
                rs.getInt("district_id"),
                rs.getInt("commune_id"),
                rs.getString("contact_person"),
                rs.getString("contact_phone")
            ));
        }
        JDBCUtil.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return facilities;
}

public List<ProductionFacility> searchByKeyword(String keyword) {
    List<ProductionFacility> facilities = new ArrayList<>();
    try {
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM production_facility WHERE facility_name LIKE ? OR address LIKE ? OR contact_person LIKE ?";
        PreparedStatement pst = con.prepareStatement(sql);
        String likeKeyword = "%" + keyword + "%";
        pst.setString(1, likeKeyword);
        pst.setString(2, likeKeyword);
        pst.setString(3, likeKeyword);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            facilities.add(new ProductionFacility(
                rs.getInt("facility_id"),
                rs.getString("facility_name"),
                rs.getString("address"),
                rs.getInt("district_id"),
                rs.getInt("commune_id"),
                rs.getString("contact_person"),
                rs.getString("contact_phone")
            ));
        }
        JDBCUtil.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return facilities;
}

}
