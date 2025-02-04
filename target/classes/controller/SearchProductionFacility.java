package controller;

import dao.ProductionFacilityDAO;
import model.ProductionFacility;

import java.util.List;

public class SearchProductionFacility {

    private static SearchProductionFacility instance;

    // Singleton để sử dụng trong toàn ứng dụng
    public static SearchProductionFacility getInstance() {
        if (instance == null) {
            instance = new SearchProductionFacility();
        }
        return instance;
    }

    // Tìm cơ sở sản xuất theo tên huyện
    public List<ProductionFacility> searchByDistrictId(int districtId) {
        return ProductionFacilityDAO.getInstance().searchByDistrict(districtId);
    }

    // Tìm cơ sở sản xuất theo tên xã
    public List<ProductionFacility> searchByCommuneId(int communeId) {
        return ProductionFacilityDAO.getInstance().searchByCommune(communeId);
    }

    // Lấy tất cả cơ sở sản xuất
    public List<ProductionFacility> searchAllFacilities() {
        return ProductionFacilityDAO.getInstance().selectAll();
    }

    // Tìm cơ sở sản xuất theo ID
    public ProductionFacility searchById(int facilityId) {
        return ProductionFacilityDAO.getInstance().selectById(facilityId);
    }

    // Tìm cơ sở sản xuất theo tên cơ sở
    public List<ProductionFacility> searchByName(String name) {
        return ProductionFacilityDAO.getInstance().searchByName(name);
    }

    // Tìm cơ sở sản xuất theo từ khóa chung (tên cơ sở, địa chỉ, người liên hệ)
    public List<ProductionFacility> searchByKeyword(String keyword) {
        return ProductionFacilityDAO.getInstance().searchByKeyword(keyword);
    }
}
