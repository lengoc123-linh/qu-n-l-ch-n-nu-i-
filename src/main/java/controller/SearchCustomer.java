package controller;

import dao.CustomerDAO;
import java.util.ArrayList;
import model.Customer;

/**
 *
 * @author Admin
 */
public class SearchCustomer {

    public static SearchCustomer getInstance() {
        return new SearchCustomer();
    }

    // Tìm kiếm tất cả khách hàng dựa trên tên, email, hoặc địa chỉ
    public ArrayList<Customer> searchTatCa(String text) {
        ArrayList<Customer> result = new ArrayList<>();
        ArrayList<Customer> customers = CustomerDAO.getInstance().selectAll();
        for (var customer : customers) {
            if (customer.getFullName().toLowerCase().contains(text.toLowerCase()) 
                || customer.getEmail().toLowerCase().contains(text.toLowerCase())
                || customer.getAddress().toLowerCase().contains(text.toLowerCase())) {
                result.add(customer);
            }
        }
        return result;
    }

    // Tìm kiếm theo mã khách hàng
    public ArrayList<Customer> searchMaKH(String text) {
        ArrayList<Customer> result = new ArrayList<>();
        ArrayList<Customer> customers = CustomerDAO.getInstance().selectAll();
        for (var customer : customers) {
            if (String.valueOf(customer.getId()).contains(text)) {
                result.add(customer);
            }
        }
        return result;
    }

    // Tìm kiếm theo tên khách hàng
    public ArrayList<Customer> searchTenKH(String text) {
        ArrayList<Customer> result = new ArrayList<>();
        ArrayList<Customer> customers = CustomerDAO.getInstance().selectAll();
        for (var customer : customers) {
            if (customer.getFullName().toLowerCase().contains(text.toLowerCase())) {
                result.add(customer);
            }
        }
        return result;
    }

    // Tìm kiếm theo số điện thoại
    public ArrayList<Customer> searchPhone(String text) {
        ArrayList<Customer> result = new ArrayList<>();
        ArrayList<Customer> customers = CustomerDAO.getInstance().selectAll();
        for (var customer : customers) {
            if (customer.getPhone().contains(text)) {
                result.add(customer);
            }
        }
        return result;
    }

    // Tìm kiếm theo email
    public ArrayList<Customer> searchEmail(String text) {
        ArrayList<Customer> result = new ArrayList<>();
        ArrayList<Customer> customers = CustomerDAO.getInstance().selectAll();
        for (var customer : customers) {
            if (customer.getEmail().toLowerCase().contains(text.toLowerCase())) {
                result.add(customer);
            }
        }
        return result;
    }

    // Tìm kiếm theo địa chỉ
    public ArrayList<Customer> searchDiaChi(String text) {
        ArrayList<Customer> result = new ArrayList<>();
        ArrayList<Customer> customers = CustomerDAO.getInstance().selectAll();
        for (var customer : customers) {
            if (customer.getAddress().toLowerCase().contains(text.toLowerCase())) {
                result.add(customer);
            }
        }
        return result;
    }

    // Tìm kiếm khách hàng theo ID
    public Customer searchId(String text) {
        Customer result = null;
        ArrayList<Customer> customers = CustomerDAO.getInstance().selectAll();
        for (var customer : customers) {
            if (String.valueOf(customer.getId()).equals(text)) {
                return customer;
            }
        }
        return null;
    }
}
