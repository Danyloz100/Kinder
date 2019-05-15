package ua.ifit.lms.dao.repository;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderRepository {
    static public void addOrder(Long userID, Long goodID) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DataSource dataSource = new DataSource();

        String query = "INSERT INTO `order` (date_order, user_id, good_idgood) VALUES (?,?,?); ";


        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);) {
            pstmt.setString(1, dtf.format(now));
            pstmt.setInt(2, userID.intValue());
            pstmt.setInt(3, goodID.intValue());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static public void deleteOrderByUserIDByGoodID(Long userID, Long goodID) {
        DataSource dataSource = new DataSource();

        String query = "DELETE FROM `order` WHERE user_id = ? AND good_idgood = ?; ";


        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);) {
            pstmt.setInt(1, userID.intValue());
            pstmt.setInt(2, goodID.intValue());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
