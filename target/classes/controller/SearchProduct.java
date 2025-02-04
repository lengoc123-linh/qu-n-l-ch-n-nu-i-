package controller;

import dao.SanPhamDAO;
import java.util.ArrayList;
import model.SanPham;

/**
 *
 * @author Admin
 */
public class SearchProduct {

    public static SearchProduct getInstance() {
        return new SearchProduct();
    }

    public ArrayList<SanPham> searchTatCa(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> arsp = SanPhamDAO.getInstance().selectAllExist();
        for (var sp : arsp) {
                if (sp.getMaSP().toLowerCase().contains(text.toLowerCase()) || sp.getTenSP().toLowerCase().contains(text.toLowerCase())
                        || sp.getXuatXu().toLowerCase().contains(text.toLowerCase())) {
                    result.add(sp);
                }
        }
        return result;
    }

    public ArrayList<SanPham> searchMaSP(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> arsp = SanPhamDAO.getInstance().selectAllExist();
        for (var sp : arsp) {
                if (sp.getMaSP().toLowerCase().contains(text.toLowerCase())) {
                    result.add(sp);
                }
        }
        return result;
    }

    public ArrayList<SanPham> searchTenSP(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> arsp = SanPhamDAO.getInstance().selectAllExist();
        for (var sp : arsp) {
                if (sp.getTenSP().toLowerCase().contains(text.toLowerCase())) {
                    result.add(sp);
                }
        }
        return result;
    }

    public ArrayList<SanPham> searchSoLuong(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> arsp = SanPhamDAO.getInstance().selectAllExist();
        for (var sp : arsp) {
            if (text.length() != 0) {
                    if (sp.getSoLuong() > Integer.parseInt(text)) {
                        result.add(sp);
                    }
            }
        }
        return result;
    }

    public ArrayList<SanPham> searchDonGia(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> arsp = SanPhamDAO.getInstance().selectAllExist();
        for (var sp : arsp) {
                if (text.length() != 0) {
                    if (sp.getDonGia() > Integer.parseInt(text)) {
                        result.add(sp);
                    }
                }
        }
        return result;
    }
   

    public ArrayList<SanPham> searchXuatXu(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> arsp = SanPhamDAO.getInstance().selectAllExist();
        for (var sp : arsp) {
            if (sp.getXuatXu().toLowerCase().contains(text.toLowerCase())) {
                result.add(sp);
            }
        }
        return result;
    }

    public ArrayList<SanPham> searchDaXoa(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> arsp = SanPhamDAO.getInstance().selectAll();
        for (var sp : arsp) {
                if (sp.getMaSP().toLowerCase().contains(text.toLowerCase())) {
                    result.add(sp);
            }
        }
        return result;
    }

    public SanPham searchId(String text) {
        SanPham result = new SanPham();
        ArrayList<SanPham> arsp = SanPhamDAO.getInstance().selectAllExist();
        for (var sp : arsp) {
            if (sp.getMaSP().toLowerCase().contains(text.toLowerCase())) {
                return sp;
            }
        }
        return null;
    }
}
