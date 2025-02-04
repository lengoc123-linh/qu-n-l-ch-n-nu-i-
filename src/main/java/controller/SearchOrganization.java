package controller;

import dao.OrganizationDAO;
import model.Organization;

import java.util.ArrayList;
import java.util.List;

public class SearchOrganization {

    public static SearchOrganization getInstance() {
        return new SearchOrganization();
    }

    // Tìm kiếm theo tất cả thuộc tính
    public List<Organization> searchAll(String keyword) {
        List<Organization> allOrganizations = OrganizationDAO.getInstance().selectAll();
        List<Organization> resultList = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return allOrganizations;
        }

        for (Organization org : allOrganizations) {
            if (org.getName().toLowerCase().contains(keyword.toLowerCase())
                    || (org.getContactPerson() != null && org.getContactPerson().toLowerCase().contains(keyword.toLowerCase()))
                    || (org.getAddress() != null && org.getAddress().toLowerCase().contains(keyword.toLowerCase()))
                    || (org.getOrganizationType() != null && org.getOrganizationType().toLowerCase().contains(keyword.toLowerCase()))
                    || (org.getEmail() != null && org.getEmail().toLowerCase().contains(keyword.toLowerCase()))) {
                resultList.add(org);
            }
        }
        return resultList;
    }

    // Tìm kiếm theo tên tổ chức
    public List<Organization> searchByName(String keyword) {
        List<Organization> allOrganizations = OrganizationDAO.getInstance().selectAll();
        List<Organization> resultList = new ArrayList<>();

        for (Organization org : allOrganizations) {
            if (org.getName().toLowerCase().contains(keyword.toLowerCase())) {
                resultList.add(org);
            }
        }
        return resultList;
    }

    // Tìm kiếm theo loại tổ chức
    public List<Organization> searchByType(String type) {
        List<Organization> allOrganizations = OrganizationDAO.getInstance().selectAll();
        List<Organization> resultList = new ArrayList<>();

        for (Organization org : allOrganizations) {
            if (org.getOrganizationType().equalsIgnoreCase(type)) {
                resultList.add(org);
            }
        }
        return resultList;
    }

    // Tìm kiếm theo người liên hệ
    public List<Organization> searchByContactPerson(String contactPerson) {
        List<Organization> allOrganizations = OrganizationDAO.getInstance().selectAll();
        List<Organization> resultList = new ArrayList<>();

        for (Organization org : allOrganizations) {
            if (org.getContactPerson().toLowerCase().contains(contactPerson.toLowerCase())) {
                resultList.add(org);
            }
        }
        return resultList;
    }

    // Tìm kiếm theo trạng thái (1: Active, 0: Inactive)
    public List<Organization> searchByStatus(int status) {
        List<Organization> allOrganizations = OrganizationDAO.getInstance().selectAll();
        List<Organization> resultList = new ArrayList<>();

        for (Organization org : allOrganizations) {
            if (org.getStatus() == status) {
                resultList.add(org);
            }
        }
        return resultList;
    }

    // Tìm kiếm theo địa chỉ
    public List<Organization> searchByAddress(String address) {
        List<Organization> allOrganizations = OrganizationDAO.getInstance().selectAll();
        List<Organization> resultList = new ArrayList<>();

        for (Organization org : allOrganizations) {
            if (org.getAddress().toLowerCase().contains(address.toLowerCase())) {
                resultList.add(org);
            }
        }
        return resultList;
    }
}
