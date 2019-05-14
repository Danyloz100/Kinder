package ua.ifit.lms.dao.repository;

import ua.ifit.lms.dao.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DataSource implements AutoCloseable {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/shop?user=admin&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private Connection connection = null;

    /**
     * Конструктор.
     * Завантажує новий екземпляр драйвера
     */
    public DataSource() {
        try{
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Створює з'єднання із БД
     * @return об'єкт класу з'єднання з БД
     */
    public Connection getConnection()
    {
        try {
            if( connection == null ) {
                connection = DriverManager.getConnection(DB_URL);
            }
        }
        catch( SQLException e ) {
            System.out.println("Error Occured " + e.toString());
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        try {
            if( connection != null ) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}