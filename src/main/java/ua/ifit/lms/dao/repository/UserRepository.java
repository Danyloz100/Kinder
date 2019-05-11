package ua.ifit.lms.dao.repository;

import ua.ifit.lms.dao.entity.User;

import java.sql.*;

public class UserRepository {

    /**
     * Get User By Email and Password from User Table
     *
     * @return class User or null
     */
    public User getUserByEmailByPassword(String email, String password) {

        DataSource dataSource = new DataSource();

        String query = "SELECT id, email, password, name, date_created, date_last_entered" +
                " FROM user " +
                " WHERE user.email='" + email + "' AND password='" + password + "'";

        try (
                // get connection with our database
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("date_created"),
                        resultSet.getString("date_last_entered")
                );

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User setUserByEmailByPassword(String name, String email, String password) {

        DataSource dataSource = new DataSource();

        String query = "INSERT INTO user (`email`, `password`, `name`) VALUES ('" + email + "', '" + password + "', '" + name + "'); ";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    }


