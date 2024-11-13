package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private static final Logger LOGGER = Logger.getLogger(Database.class.getName());

    public static Connection db_connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "MySQL Driver not found", e);
            throw new SQLException("MySQL Driver not found", e);
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicle_tracking", "root", "Naresh@2812");
    }
}
