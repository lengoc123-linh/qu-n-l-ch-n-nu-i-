package dao;

import database.JDBCUtil;
import model.TestingFacility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestingFacilityDAO {

    private static TestingFacilityDAO instance;

    // Singleton pattern
    public static TestingFacilityDAO getInstance() {
        if (instance == null) {
            instance = new TestingFacilityDAO();
        }
        return instance;
    }

    // Lấy tất cả TestingFacility
    public List<TestingFacility> selectAll() {
        List<TestingFacility> facilities = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM testing_facility";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                facilities.add(new TestingFacility(
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

    // Lấy TestingFacility theo ID
    public TestingFacility selectById(int facilityId) {
        TestingFacility facility = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM testing_facility WHERE facility_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, facilityId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                facility = new TestingFacility(
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facility;
    }

    // Thêm TestingFacility
    public int insert(TestingFacility facility) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO testing_facility (facility_name, address, district_id, commune_id, contact_person, contact_phone) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, facility.getFacilityName());
            pst.setString(2, facility.getAddress());
            pst.setInt(3, facility.getDistrictId());
            pst.setInt(4, facility.getCommuneId());
            pst.setString(5, facility.getContactPerson());
            pst.setString(6, facility.getContactPhone());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Cập nhật TestingFacility
    public int update(TestingFacility facility) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE testing_facility SET facility_name = ?, address = ?, district_id = ?, commune_id = ?, contact_person = ?, contact_phone = ? WHERE facility_id = ?";
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Xóa TestingFacility
    public int delete(int facilityId) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM testing_facility WHERE facility_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, facilityId);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Tìm TestingFacility theo từ khóa
    public List<TestingFacility> searchByKeyword(String keyword) {
        List<TestingFacility> facilities = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM testing_facility WHERE facility_name LIKE ? OR address LIKE ? OR contact_person LIKE ?";
            PreparedStatement pst = con.prepareStatement(sql);
            String likeKeyword = "%" + keyword + "%";
            pst.setString(1, likeKeyword);
            pst.setString(2, likeKeyword);
            pst.setString(3, likeKeyword);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                facilities.add(new TestingFacility(
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

    // Tìm TestingFacility theo ID huyện
    public List<TestingFacility> searchByDistrictId(int districtId) {
        List<TestingFacility> facilities = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM testing_facility WHERE district_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, districtId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                facilities.add(new TestingFacility(
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

    // Tìm TestingFacility theo ID xã
    public List<TestingFacility> searchByCommuneId(int communeId) {
        List<TestingFacility> facilities = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM testing_facility WHERE commune_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, communeId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                facilities.add(new TestingFacility(
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
