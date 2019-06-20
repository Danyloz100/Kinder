package ua.ifit.lms.dao.repository;

import ua.ifit.lms.dao.entity.Catalog;
import ua.ifit.lms.dao.entity.Good;

import java.sql.*;
import java.util.ArrayList;

public class GoodRepository {

    // Метод приймає ліст каталогів та повертає ліст товарів, які відповідають цим каталогам
    public static ArrayList<Good> getGoodsByCategories(ArrayList<Catalog> checkedCategories) {
        DataSource dataSource = new DataSource();
        String query = "SELECT idGood, Count_of_goods, Good_name, Picture_file_name, Price, Description FROM Good" +
                " LEFT JOIN good_has_catalog ON idGood = good_idGood" +
                " WHERE Count_of_goods > 0  AND ( 1 = 1 /*type*/)" +
                " GROUP BY Good_idGood;";
        for(String each: CatalogRepository.getCatalogsTypes()) {
            query = query.replace("/*type*/", "AND ( /*" + each + "*/ ) /*type*/");
        }
        query = query.replace("/*type*/", "");

        for(Catalog each: checkedCategories) {
            query = query.replace("/*" + each.getName() + "*/", each.getId() + " = ANY( SELECT catalog_id FROM good_has_catalog a WHERE a.good_idgood = good_has_catalog.good_idGood)" + " OR " + "/*" + each.getName() + "*/");
        }
        for(String each: CatalogRepository.getCatalogsTypes()) {
            query = query.replace(" OR /*" + each + "*/", "");
            query = query.replace(" AND ( /*" + each + "*/ )", "");
        }
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
    // Метод приймає код товару та повертає його обєкт
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
    // Метод приймає код юзера та повертає ліст всіх товарів з його корзини
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
    // Приймає ліст товарів та віднімає по 1 з кількості кожного з товарів
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
    // Метод приймає опис введений користувачем та повертає ліст товарів, які йому відповідають
    static public ArrayList<Good> getGoodsByDescription(String description) {
        DataSource dataSource = new DataSource();
        ArrayList<Good> list = new ArrayList();
        String query = "CALL FindByDescription('" + description + "');";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery(query)) {
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
            System.out.println(e.getMessage());
        }
        return null;
    }

}
