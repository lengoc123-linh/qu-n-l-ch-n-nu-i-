package dao;

import model.Farm;
import database.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FarmDAO {

    private static FarmDAO instance;

    public static FarmDAO getInstance() {
        if (instance == null) {
            instance = new FarmDAO();
        }
        return instance;
    }

    private final Logger logger = Logger.getLogger(FarmDAO.class.getName());

    // INSERT
    public int insert(Farm farm) {
        String sql = "INSERT INTO farm (farm_name, address, district_id, commune_id, owner, latitude, longitude, organization_id) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, farm.getFarmName());
            pst.setString(2, farm.getAddress());
            pst.setInt(3, farm.getDistrictId());
            pst.setInt(4, farm.getCommuneId());
            pst.setString(5, farm.getOwner());
            pst.setDouble(6, farm.getLatitude());
            pst.setDouble(7, farm.getLongitude());
            pst.setInt(8, farm.getOrganizationId());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error inserting farm: ", ex);
        }
        return 0;
    }

    // UPDATE
    public int update(Farm farm) {
        String sql = "UPDATE farm SET farm_name = ?, address = ?, district_id = ?, commune_id = ?, owner = ?, latitude = ?, longitude = ?, organization_id = ? WHERE farm_id = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, farm.getFarmName());
            pst.setString(2, farm.getAddress());
            pst.setInt(3, farm.getDistrictId());
            pst.setInt(4, farm.getCommuneId());
            pst.setString(5, farm.getOwner());
            pst.setDouble(6, farm.getLatitude());
            pst.setDouble(7, farm.getLongitude());
            pst.setInt(8, farm.getOrganizationId());
            pst.setInt(9, farm.getFarmId());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating farm: ", ex);
        }
        return 0;
    }

    // DELETE
    public int delete(int farmId) {
        String sql = "DELETE FROM farm WHERE farm_id = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, farmId);
            return pst.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error deleting farm: ", ex);
        }
        return 0;
    }

    // SELECT ALL
    public List<Farm> selectAll() {
        List<Farm> farmList = new ArrayList<>();
        String sql = "SELECT * FROM farm";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                farmList.add(mapResultSetToFarm(rs));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error selecting all farms: ", ex);
        }
        return farmList;
    }

    // SELECT BY ID
    public Farm selectById(int farmId) {
        String sql = "SELECT * FROM farm WHERE farm_id = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, farmId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return mapResultSetToFarm(rs);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error selecting farm by ID: ", ex);
        }
        return null;
    }

    // SEARCH BY DISTRICT NAME
    public List<Farm> searchByDistrictName(String districtName) {
        List<Farm> farms = new ArrayList<>();
        String sql = "SELECT f.* FROM farm f JOIN district d ON f.district_id = d.district_id WHERE d.district_name LIKE ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, "%" + districtName + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                farms.add(mapResultSetToFarm(rs));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error searching farms by district name: ", ex);
        }
        return farms;
    }

    // SEARCH BY COMMUNE NAME
    public List<Farm> searchByCommuneName(String communeName) {
        List<Farm> farms = new ArrayList<>();
        String sql = "SELECT f.* FROM farm f JOIN commune c ON f.commune_id = c.commune_id WHERE c.commune_name LIKE ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, "%" + communeName + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                farms.add(mapResultSetToFarm(rs));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error searching farms by commune name: ", ex);
        }
        return farms;
    }

    // MAPPING RESULTSET TO FARM OBJECT
    private Farm mapResultSetToFarm(ResultSet rs) throws SQLException {
        return new Farm(
                rs.getInt("farm_id"),
                rs.getString("farm_name"),
                rs.getString("address"),
                rs.getInt("district_id"),
                rs.getInt("commune_id"),
                rs.getString("owner"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude"),
                rs.getInt("organization_id")
        );
    }
    
}
