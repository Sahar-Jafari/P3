package model.crud;

import model.entity.Customer;

import java.sql.*;

/**
 * Created by Dotin school 6 on 3/16/2015.
 */
public class CustomerCRUD {
    static Connection connection;
    static PreparedStatement statement;

    static  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project3", "root", "myjava123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(long customerNumber) throws SQLException {
        statement = connection.prepareStatement("INSERT INTO CUSTOMER (CUSTOMER_NUMBER) VALUES (?);");
        statement.setLong(1, customerNumber);
        statement.executeUpdate();
    }

    public static void delete(long customerId) throws SQLException {
        statement = connection.prepareStatement("DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?");
        statement.setLong(1, customerId);
        statement.executeUpdate();
    }

    public static long lastCustomerID() throws SQLException {
        long lastCustomerId = 0;
        statement = connection.prepareStatement("SELECT LAST_INSERT_ID() AS CUSTOMER_ID FROM CUSTOMER;");
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) lastCustomerId = resultSet.getLong("CUSTOMER_ID");
        return lastCustomerId;
    }

    public static long maxCustomerNumber() throws SQLException {
        long maxCustomerNumber = 0;
        statement = connection.prepareStatement("SELECT MAX(CUSTOMER_NUMBER) AS MAX_CUSTOMER_NUMBER FROM CUSTOMER");
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) maxCustomerNumber = resultSet.getLong("MAX_CUSTOMER_NUMBER");
        return maxCustomerNumber;
    }

    public static long select(String customerNumber) throws SQLException {
        long customerId=0;
        statement = connection.prepareStatement("SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMER_NUMBER=?");
        statement.setLong(1, Long.parseLong(customerNumber));
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            customerId=resultSet.getLong("CUSTOMER_ID");
        }
        return customerId;
    }
}
