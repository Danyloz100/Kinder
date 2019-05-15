package ua.ifit.lms.dao.repository;

import ua.ifit.lms.dao.entity.Good;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public static ArrayList<Good> getGootsByQuery(String query) {
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





}
