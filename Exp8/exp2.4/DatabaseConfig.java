import java.sql.*;

public class DatabaseConfig {
    static final String URL = "jdbc:mysql://bytexldb.com:5051/db_43zvw8r3h";
    static final String USER = "user_43zvw8r3h";
    static final String PASSWORD = "p43zvw8r3h";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
