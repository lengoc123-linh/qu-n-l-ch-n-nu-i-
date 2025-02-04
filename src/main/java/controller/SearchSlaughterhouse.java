package controller;

import dao.SlaughterhouseDAO;
import model.Slaughterhouse;

import java.util.List;

public class SearchSlaughterhouse {

    private static SearchSlaughterhouse instance;

    // Singleton Pattern
    public static SearchSlaughterhouse getInstance() {
        if (instance == null) {
            instance = new SearchSlaughterhouse();
        }
        return instance;
    }

    // SEARCH BY NAME
    public List<Slaughterhouse> searchByName(String name) {
        String condition = "LOWER(name) LIKE ?";
        return SlaughterhouseDAO.getInstance().queryWithConditions(condition, "%" + name.toLowerCase() + "%");
    }

    // SEARCH BY CAPACITY
    public List<Slaughterhouse> searchByCapacity(int capacity) {
        String condition = "capacity >= ?";
        return SlaughterhouseDAO.getInstance().queryWithConditions(condition, capacity);
    }

    // SEARCH BY TYPE
    public List<Slaughterhouse> searchByType(String type) {
        String condition = "type = ?";
        return SlaughterhouseDAO.getInstance().queryWithConditions(condition, type);
    }

    // SEARCH BY STATUS
    public List<Slaughterhouse> searchByStatus(String status) {
        String condition = "LOWER(status) = ?";
        return SlaughterhouseDAO.getInstance().queryWithConditions(condition, status.toLowerCase());
    }

    // SEARCH BY ADDRESS
    public List<Slaughterhouse> searchByAddress(String address) {
        String condition = "LOWER(address) LIKE ?";
        return SlaughterhouseDAO.getInstance().queryWithConditions(condition, "%" + address.toLowerCase() + "%");
    }

    // SEARCH BY KEYWORD (Multiple fields)
    public List<Slaughterhouse> searchByKeyword(String keyword) {
        String condition = "LOWER(name) LIKE ? OR LOWER(type) LIKE ? OR LOWER(status) LIKE ? OR LOWER(address) LIKE ?";
        return SlaughterhouseDAO.getInstance().queryWithConditions(
                condition,
                "%" + keyword.toLowerCase() + "%",
                "%" + keyword.toLowerCase() + "%",
                "%" + keyword.toLowerCase() + "%",
                "%" + keyword.toLowerCase() + "%"
        );
    }

    // ADVANCED SEARCH
    public List<Slaughterhouse> advancedSearch(String name, String type, String status, String address, Integer capacity) {
        StringBuilder condition = new StringBuilder();
        if (name != null && !name.isEmpty()) {
            condition.append("LOWER(name) LIKE ? ");
        }
        if (type != null && !type.isEmpty()) {
            if (condition.length() > 0) condition.append("AND ");
            condition.append("type = ? ");
        }
        if (status != null && !status.isEmpty()) {
            if (condition.length() > 0) condition.append("AND ");
            condition.append("LOWER(status) = ? ");
        }
        if (address != null && !address.isEmpty()) {
            if (condition.length() > 0) condition.append("AND ");
            condition.append("LOWER(address) LIKE ? ");
        }
        if (capacity != null) {
            if (condition.length() > 0) condition.append("AND ");
            condition.append("capacity >= ? ");
        }

        return SlaughterhouseDAO.getInstance().queryWithConditions(
                condition.toString(),
                name != null ? "%" + name.toLowerCase() + "%" : null,
                type,
                status != null ? status.toLowerCase() : null,
                address != null ? "%" + address.toLowerCase() + "%" : null,
                capacity
        );
    }
}