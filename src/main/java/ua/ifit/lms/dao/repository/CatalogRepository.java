package ua.ifit.lms.dao.repository;

import ua.ifit.lms.dao.entity.Catalog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CatalogRepository {
    // Метод повертає ліст каталогів, які відповідають хоча б 1 товару
    static public ArrayList<Catalog> getCatalogs() {

        DataSource dataSource = new DataSource();
        String query = "SELECT DISTINCT id, name, description" +
                " FROM Catalog " +
                "INNER JOIN good_has_catalog ON id = catalog_id " +
                "ORDER BY name DESC;";
        ArrayList<Catalog> list = new ArrayList();
        try (
                // get connection with our database
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                Catalog catalog = new Catalog(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
                list.add(catalog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Метод повертає ліст всіх існуючих типів каталогів
    static public ArrayList<String> getCatalogsTypes() {
        DataSource dataSource = new DataSource();
        String query = "SELECT DISTINCT name" +
                " FROM Catalog ";
        ArrayList<String> list = new ArrayList();
        try (
                // get connection with our database
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                list.add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
