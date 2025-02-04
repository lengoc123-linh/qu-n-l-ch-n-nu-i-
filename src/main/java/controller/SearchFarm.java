package controller;

import dao.FarmDAO;
import model.Farm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFarm {

    private static SearchFarm instance;

    public static SearchFarm getInstance() {
        if (instance == null) {
            instance = new SearchFarm();
        }
        return instance;
    }

    // Tìm kiếm tất cả farm dựa trên từ khóa (tìm trên tất cả trường)
    public List<Farm> searchAll(String keyword) {
        return FarmDAO.getInstance()
                .selectAll()
                .stream()
                .filter(farm -> farm.getFarmName().toLowerCase().contains(keyword.toLowerCase())
                        || farm.getAddress().toLowerCase().contains(keyword.toLowerCase())
                        || farm.getOwner().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Tìm kiếm theo mã farm (farm_id)
    public List<Farm> searchByFarmId(String farmIdText) {
        try {
            int farmId = Integer.parseInt(farmIdText);
            return FarmDAO.getInstance()
                    .selectAll()
                    .stream()
                    .filter(farm -> farm.getFarmId() == farmId)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            return new ArrayList<>(); // Trả về danh sách rỗng nếu farmId không hợp lệ
        }
    }

    // Tìm kiếm theo tên farm
    public List<Farm> searchByFarmName(String farmName) {
        return FarmDAO.getInstance()
                .selectAll()
                .stream()
                .filter(farm -> farm.getFarmName().toLowerCase().contains(farmName.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Tìm kiếm farm theo tên huyện
    public List<Farm> searchByDistrictName(String districtName) {
        return FarmDAO.getInstance().searchByDistrictName(districtName);
    }

    // Tìm kiếm farm theo tên xã
    public List<Farm> searchByCommuneName(String communeName) {
        return FarmDAO.getInstance().searchByCommuneName(communeName);
    }

    // Tìm kiếm farm theo tổ chức (organization_id)
    public List<Farm> searchByOrganization(String organizationIdText) {
        try {
            int organizationId = Integer.parseInt(organizationIdText);
            return FarmDAO.getInstance()
                    .selectAll()
                    .stream()
                    .filter(farm -> farm.getOrganizationId() == organizationId)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            return new ArrayList<>(); // Trả về danh sách rỗng nếu organizationId không hợp lệ
        }
    }
}
