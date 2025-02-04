package controller;

import dao.SystemGuideDAO;
import model.SystemGuide;

import java.util.ArrayList;

public class SearchSystemGuides {

    // Phương thức khởi tạo đối tượng duy nhất (Singleton pattern)
    public static SearchSystemGuides getInstance() {
        return new SearchSystemGuides();
    }

    // Tìm kiếm tất cả các hướng dẫn theo tiêu chí
    public ArrayList<SystemGuide> searchAllSystemGuides(String text) {
        ArrayList<SystemGuide> result = new ArrayList<>();
        ArrayList<SystemGuide> systemGuides = SystemGuideDAO.getInstance().selectAll();

        for (SystemGuide guide : systemGuides) {
            // Kiểm tra nếu các trường trong SystemGuide chứa chuỗi tìm kiếm
            if (guide.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                guide.getContent().toLowerCase().contains(text.toLowerCase())) {
                result.add(guide);
            }
        }
        return result;
    }

    // Tìm kiếm theo tiêu đề hướng dẫn
    public ArrayList<SystemGuide> searchByTitle(String title) {
        ArrayList<SystemGuide> result = new ArrayList<>();
        ArrayList<SystemGuide> systemGuides = SystemGuideDAO.getInstance().selectAll();

        for (SystemGuide guide : systemGuides) {
            if (guide.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(guide);
            }
        }
        return result;
    }

    // Tìm kiếm theo nội dung hướng dẫn
    public ArrayList<SystemGuide> searchByContent(String content) {
        ArrayList<SystemGuide> result = new ArrayList<>();
        ArrayList<SystemGuide> systemGuides = SystemGuideDAO.getInstance().selectAll();

        for (SystemGuide guide : systemGuides) {
            if (guide.getContent().toLowerCase().contains(content.toLowerCase())) {
                result.add(guide);
            }
        }
        return result;
    }
}
