/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import database.JDBCUtil;
import java.sql.*;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class ThongKeProduct {
    private String maSP;
    private String tenSP;
    private int slNhap;
    private int slXuat;

    public ThongKeProduct() {
    }

    public ThongKeProduct(String maSP, String tenSP, int slNhap, int slXuat) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.slNhap = slNhap;
        this.slXuat = slXuat;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSlNhap() {
        return slNhap;
    }

    public void setSlNhap(int slNhap) {
        this.slNhap = slNhap;
    }

    public int getSlXuat() {
        return slXuat;
    }

    public void setSlXuat(int slXuat) {
        this.slXuat = slXuat;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.maSP);
        hash = 53 * hash + Objects.hashCode(this.tenSP);
        hash = 53 * hash + this.slNhap;
        hash = 53 * hash + this.slXuat;
        return hash;
    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final ThongKeProduct other = (ThongKeProduct) obj;
//        if (this.slNhap != other.slNhap) {
//            return false;
//        }
//        if (this.slXuat != other.slXuat) {
//            return false;
//        }
//        if (!Objects.equals(this.maSP, other.maSP)) {
//            return false;
//        }
//        return Objects.equals(this.tenSP, other.tenSP);
//    }

    @Override
    public String toString() {
        return "ThongKeProduct{" + "maMay=" + maSP + ", tenMay=" + tenSP + ", slNhap=" + slNhap + ", slXuat=" + slXuat + '}';
    }
    
    
}
