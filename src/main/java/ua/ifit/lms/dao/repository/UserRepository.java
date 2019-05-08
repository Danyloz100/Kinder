package ua.ifit.lms.dao.repository;

import ua.ifit.lms.dao.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {

    /**
     * Get User By Email and Password from User Table
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
                )
        {
            if (resultSet.next())  {
                User user =  new User(
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
}
