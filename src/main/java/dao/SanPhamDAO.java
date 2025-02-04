/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.DAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import database.JDBCUtil;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.SanPham;

public class SanPhamDAO implements DAOInterface<SanPham> {

    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }

    @Override
    public int insert(SanPham t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO SanPham (maSP, tenSP, soLuong, donGia, xuatXu, trangThai) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaSP());
            pst.setString(2, t.getTenSP());
            pst.setInt(3, t.getSoLuong());
            pst.setDouble(4, t.getDonGia());
            pst.setString(5, t.getXuatXu());
            pst.setInt(6, t.getTrangThai());
            ketQua = pst.executeUpdate();
            
            
        // Ghi lại hành động người dùng khi thêm sản phẩm mới
        SystemActionHistoryDAO.getInstance().logUserAction(
            "Thêm sản phẩm",  // Loại hành động
            "Thêm sản phẩm mới: " + t.getMaSP() + " - " + t.getTenSP(),  // Mô tả hành động
            "admin"  // Người thực hiện (Giả sử là admin)
        );
        
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
 

    @Override
    public int update(SanPham t) {
        int ketqua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE SanPham SET tenSP = ?,soLuong=?,donGia=?,xuatXu=?,trangThai=? WHERE maSP=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenSP());
            pst.setInt(2, t.getSoLuong());
            pst.setDouble(3, t.getDonGia());
            pst.setString(4, t.getXuatXu());
            pst.setInt(5, t.getTrangThai());
            pst.setString(6, t.getMaSP());
            ketqua = pst.executeUpdate(sql);
            
             // Ghi lại hành động người dùng khi cập nhật sản phẩm
        SystemActionHistoryDAO.getInstance().logUserAction(
            "Cập nhật sản phẩm",  // Loại hành động
            "Cập nhật sản phẩm: " + t.getMaSP() + " - " + t.getTenSP(),  // Mô tả hành động
            "admin"  // Người thực hiện
        );
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    @Override
    public int delete(SanPham t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM SanPham WHERE maSP=? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaSP());
            ketQua = pst.executeUpdate();

        // Ghi lại hành động người dùng khi xóa sản phẩm
        SystemActionHistoryDAO.getInstance().logUserAction(
            "Xóa sản phẩm",  // Loại hành động
            "Xóa sản phẩm: " + t.getMaSP() + " - " + t.getTenSP(),  // Mô tả hành động
            "admin"  // Người thực hiện
        );
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<SanPham> selectAll() {
        ArrayList<SanPham> ketQua = new ArrayList<SanPham>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT maSP,tenSP,soLuong,donGia,xuatXu,trangThai FROM SanPham";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maSP = rs.getString("maSP");
                String tenSP = rs.getString("tenSP");
                int soLuong = rs.getInt("soLuong");
                double gia = rs.getDouble("donGia");
                String xuatXu = rs.getString("xuatXu");
                int trangThai =rs.getInt("trangThai");
                SanPham sp = new SanPham(maSP, tenSP, soLuong, gia, xuatXu,trangThai);
                ketQua.add(sp);
            }
            JDBCUtil.closeConnection(con);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public SanPham selectById(String t) {
        SanPham ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT maSP,tenSP,soLuong,donGia,xuatXu,trangThai FROM SanPham WHERE maSP = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               String maSP = rs.getString("maSP");
                String tenSP = rs.getString("tenSP");
                int soLuong = rs.getInt("soLuong");
                double gia = rs.getDouble("donGia");
                String xuatXu = rs.getString("xuatXu");
                int trangThai =rs.getInt("trangThai");
                ketQua = new SanPham(maSP, tenSP, soLuong, gia, xuatXu,trangThai);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    public int updateSoLuong(String maSP, int soluong) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE SanPham SET soLuong=? WHERE maSP=? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, soluong);
            pst.setString(2, maSP);
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public int deleteTrangThai(String maSP){
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE SanPham SET trangThai=0 WHERE maSP=? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maSP);
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public ArrayList<SanPham> selectAllE() {
        ArrayList<SanPham> ketQua = new ArrayList<SanPham>();
        ArrayList<SanPham> ketQuaTonKho = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT maSP,tenSP,soLuong,donGia,xuatXu,trangThai FROM SanPham";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               String maSP = rs.getString("maSP");
                String tenSP = rs.getString("tenSP");
                int soLuong = rs.getInt("soLuong");
                double gia = rs.getDouble("donGia");
                String xuatXu = rs.getString("xuatXu");
                int trangThai =rs.getInt("trangThai");
                SanPham sp = new SanPham(maSP, tenSP, soLuong, gia, xuatXu,trangThai);
                ketQua.add(sp);
            }
            for (SanPham sanPham : ketQua) {
                if (sanPham.getSoLuong() > 0) {
                    ketQuaTonKho.add(sanPham);
                }
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQuaTonKho;
    }
    
        public ArrayList<SanPham> selectAllExist() {
        ArrayList<SanPham> ketQua = new ArrayList<SanPham>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT maSP,tenSP,soLuong,donGia,xuatXu,trangThai FROM SanPham WHERE trangThai = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               String maSP = rs.getString("maSP");
                String tenSP = rs.getString("tenSP");
                int soLuong = rs.getInt("soLuong");
                double gia = rs.getDouble("donGia");
                String xuatXu = rs.getString("xuatXu");
                int trangThai =rs.getInt("trangThai");
                SanPham sp = new SanPham(maSP, tenSP, soLuong, gia, xuatXu,trangThai);
                ketQua.add(sp);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
        
    public int getSl() {
        int soluong = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM SanPham WHERE trangThai = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                soluong++;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return soluong;
    }
}
