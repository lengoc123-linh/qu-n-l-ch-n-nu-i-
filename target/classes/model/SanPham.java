/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

public class SanPham {
    
    private String maSP;
    private String tenSP;
    private int soLuong;
    private double donGia;
    private String xuatXu;
    private int trangThai;
    

    public SanPham() {
        
    }


    public SanPham(String maSP, String tenSP, int soLuong, double donGia, String xuatXu,int trangThai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia=donGia;
        this.xuatXu = xuatXu;
        this.trangThai = trangThai;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setGia(double donGia) {
        this.donGia = donGia;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }
    
    public void xuatHang(int sl) {
        this.soLuong -= sl;
    }
    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
    @Override
    public String toString() {
        return "MayTinh{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", soLuong=" + soLuong + ", donGia=" + donGia + ", xuatXu=" + xuatXu +  "trangThai=" + trangThai +'}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.maSP);
        hash = 37 * hash + Objects.hashCode(this.tenSP);
        hash = 37 * hash + this.soLuong;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.donGia) ^ (Double.doubleToLongBits(this.donGia) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.xuatXu);
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
//        final SanPham other = (SanPham) obj;
//        if (this.soLuong != other.soLuong) {
//            return false;
//        }
//        if (Double.doubleToLongBits(this.donGia) != Double.doubleToLongBits(other.donGia)) {
//            return false;
//        }
//        if (!Objects.equals(this.maSP, other.maSP)) {
//            return false;
//        }
//        if (!Objects.equals(this.tenSP, other.tenSP)) {
//            return false;
//        }
//        return Objects.equals(this.xuatXu, other.xuatXu);
//    }
}
