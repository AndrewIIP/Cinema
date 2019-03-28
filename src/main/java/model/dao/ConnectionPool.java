package model.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.PoolingDataSource;

import javax.sql.ConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPool {
    private static final String DRIVER__CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/cinema?serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setDriverClassName(DRIVER__CLASS_NAME);
        ds.setUrl(URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxTotal(50);
        ds.setMaxOpenPreparedStatements(100);

        for(int i = 0; i < 200; i++) {
            try (Connection connection = ds.getConnection()) {
                try (Statement statement = connection.createStatement()) {

                    ResultSet rs = statement.executeQuery("SELECT CONNECTION_ID()");
                    rs.next();
                    System.out.println(i+" Connection in = " + rs.getLong(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private ConnectionPool(){ }
}
