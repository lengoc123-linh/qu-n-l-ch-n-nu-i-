package dao;

import model.FarmCertificate;
import database.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FarmCertificateDAO {

    private static FarmCertificateDAO instance;

    // Singleton pattern: Chỉ một thể hiện của DAO được tạo ra
    public static FarmCertificateDAO getInstance() {
        if (instance == null) {
            instance = new FarmCertificateDAO();
        }
        return instance;
    }

    // Insert new certificate
    public int insert(FarmCertificate certificate) {
        String sql = "INSERT INTO farm_certificates (farm_id, certificate_type, issue_date, expiry_date, issuer, status) VALUES (?, ?, ?, ?, ?, ?)";
        return executeUpdate(sql, certificate, false);
    }

    // Update existing certificate
    public int update(FarmCertificate certificate) {
        String sql = "UPDATE farm_certificates SET farm_id = ?, certificate_type = ?, issue_date = ?, expiry_date = ?, issuer = ?, status = ? WHERE certificate_id = ?";
        return executeUpdate(sql, certificate, true);
    }

    // Delete certificate by ID
    public int delete(int certificateId) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("DELETE FROM farm_certificates WHERE certificate_id = ?")) {
            pst.setInt(1, certificateId);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmCertificateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Select all certificates
    public List<FarmCertificate> selectAll() {
        List<FarmCertificate> certificateList = new ArrayList<>();
        String sql = "SELECT * FROM farm_certificates";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                certificateList.add(mapResultSetToCertificate(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmCertificateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return certificateList;
    }

    // Select a certificate by ID
    public FarmCertificate selectById(int certificateId) {
        FarmCertificate certificate = null;
        String sql = "SELECT * FROM farm_certificates WHERE certificate_id = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, certificateId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    certificate = mapResultSetToCertificate(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FarmCertificateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return certificate;
    }

    // Utility method to execute INSERT and UPDATE queries
    private int executeUpdate(String sql, FarmCertificate certificate, boolean isUpdate) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, certificate.getFarmId());
            pst.setString(2, certificate.getCertificateType());
            pst.setString(3, certificate.getIssueDate());
            pst.setString(4, certificate.getExpiryDate());
            pst.setString(5, certificate.getIssuer());
            pst.setInt(6, certificate.getStatus());
            if (isUpdate) {
                pst.setInt(7, certificate.getCertificateId());
            }
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FarmCertificateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Utility method to map ResultSet to FarmCertificate object
    private FarmCertificate mapResultSetToCertificate(ResultSet rs) throws SQLException {
        return new FarmCertificate(
            rs.getInt("certificate_id"),
            rs.getInt("farm_id"),
            rs.getString("certificate_type"),
            rs.getString("issue_date"),
            rs.getString("expiry_date"),
            rs.getString("issuer"),
            rs.getInt("status")
        );
    }
}
