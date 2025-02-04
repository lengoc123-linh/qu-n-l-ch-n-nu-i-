package model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class PhieuXuat extends Phieu {
    
    private Customer customer; // Thêm trường khách hàng

    public PhieuXuat() {
    }  

    public PhieuXuat(String maPhieu, Timestamp thoiGianTao, String nguoiTao, ArrayList<ChiTietPhieu> CTPhieu, double tongTien, Customer customer) {
        super(maPhieu, thoiGianTao, nguoiTao, CTPhieu, tongTien);
        this.customer = customer;
    }

    // Getter và Setter cho customer
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
