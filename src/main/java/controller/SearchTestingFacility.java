package service;

import dao.TestingFacilityDAO;
import model.TestingFacility;

import java.util.List;

public class SearchTestingFacility {

    private static SearchTestingFacility instance;

    // Singleton để sử dụng trong toàn ứng dụng
    public static SearchTestingFacility getInstance() {
        if (instance == null) {
            instance = new SearchTestingFacility();
        }
        return instance;
    }

    // Tìm cơ sở khảo nghiệm theo từ khóa
    public List<TestingFacility> searchByKeyword(String keyword) {
        return TestingFacilityDAO.getInstance().searchByKeyword(keyword);
    }

    // Tìm cơ sở khảo nghiệm theo tên
    public List<TestingFacility> searchByName(String name) {
        return TestingFacilityDAO.getInstance().searchByKeyword(name);
    }

    // Tìm cơ sở khảo nghiệm theo ID huyện
    public List<TestingFacility> searchByDistrictId(int districtId) {
        return TestingFacilityDAO.getInstance().searchByDistrictId(districtId);
    }

    // Tìm cơ sở khảo nghiệm theo ID xã
    public List<TestingFacility> searchByCommuneId(int communeId) {
        return TestingFacilityDAO.getInstance().searchByCommuneId(communeId);
    }

    // Lấy tất cả cơ sở khảo nghiệm
    public List<TestingFacility> getAll() {
        return TestingFacilityDAO.getInstance().selectAll();
    }
}
