package ua.ifit.lms.dao.repository;

import ua.ifit.lms.dao.entity.Good;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodRepository {
    static public ArrayList<Good> getGoods() {

        DataSource dataSource = new DataSource();

        String query = "SELECT idGood, Count_of_goods, Good_name, Picture_file_name, Price, Description" +
                " FROM good ";
        ArrayList<Good> list = new ArrayList();
        try (
                // get connection with our database
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                Good good = new Good(
                        resultSet.getLong("idGood"),
                        resultSet.getLong("Count_of_goods"),
                        resultSet.getString("Good_name"),
                        resultSet.getString("Picture_file_name"),
                        resultSet.getFloat("Price"),
                        resultSet.getString("Description")
                );
                list.add(good);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static ArrayList<Good> getGoodsByQuery(String query) {
        DataSource dataSource = new DataSource();

        ArrayList<Good> list = new ArrayList();

        try (
                // get connection with our database
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                Good good = new Good(
                        resultSet.getLong("idGood"),
                        resultSet.getLong("Count_of_goods"),
                        resultSet.getString("Good_name"),
                        resultSet.getString("Picture_file_name"),
                        resultSet.getFloat("Price"),
                        resultSet.getString("Description")
                );
                list.add(good);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static Good getGoodByID(Long id) {
        DataSource dataSource = new DataSource();
        String query = "SELECT idGood, Count_of_goods, Good_name, Picture_file_name, Price, Description" +
        " FROM good " +
                "WHERE idGood = " + id.toString() + ";";
        try (
                // get connection with our database
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                Good good = new Good(
                        resultSet.getLong("idGood"),
                        resultSet.getLong("Count_of_goods"),
                        resultSet.getString("Good_name"),
                        resultSet.getString("Picture_file_name"),
                        resultSet.getFloat("Price"),
                        resultSet.getString("Description")
                );
                return good;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Good> getGoodsByUserID(Long id) {
        DataSource dataSource = new DataSource();
        ArrayList list = new ArrayList<Good>();
        String query = "SELECT idGood, Count_of_goods, Good_name, Picture_file_name, Price, Description" +
                " FROM good " +
                "INNER JOIN `Order` ON User_id = " + id.toString() + " AND " + "idGood = Good_idGood" + ";";
        try (
                // get connection with our database
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                Good good = new Good(
                        resultSet.getLong("idGood"),
                        resultSet.getLong("Count_of_goods"),
                        resultSet.getString("Good_name"),
                        resultSet.getString("Picture_file_name"),
                        resultSet.getFloat("Price"),
                        resultSet.getString("Description")
                );
                list.add(good);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public void substructItems(ArrayList<Good> list) {
        DataSource dataSource = new DataSource();
        String query = "UPDATE `good` SET Count_of_goods = Count_of_goods - 1 WHERE idGood = ?; ";


        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (Good each: list)
            {
                pstmt.setInt(1, each.getIdGood().intValue());
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


}
