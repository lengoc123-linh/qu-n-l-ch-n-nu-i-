package dao;

import model.SystemGuide;
import database.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;

public class SystemGuideDAO implements DAOInterface<SystemGuide> {

    // Singleton pattern
    public static SystemGuideDAO getInstance() {
        return new SystemGuideDAO();
    }

    @Override
    public int insert(SystemGuide t) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO system_guides (title, content) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTitle());
            pst.setString(2, t.getContent());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(SystemGuide t) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "UPDATE system_guides SET title=?, content=? WHERE guide_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTitle());
            pst.setString(2, t.getContent());
            pst.setInt(3, t.getGuideId());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(SystemGuide t) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM system_guides WHERE guide_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getGuideId());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<SystemGuide> selectAll() {
        ArrayList<SystemGuide> guides = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM system_guides";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int guideId = rs.getInt("guide_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                SystemGuide guide = new SystemGuide(guideId, title, content, createdAt, updatedAt);
                guides.add(guide);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guides;
    }

    @Override
    public SystemGuide selectById(String id) {
        SystemGuide guide = null;
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM system_guides WHERE guide_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int guideId = rs.getInt("guide_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                guide = new SystemGuide(guideId, title, content, createdAt, updatedAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guide;
    }
}
