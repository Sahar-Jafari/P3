package model.crud;

import model.entity.RealCustomer;

import java.sql.*;
import java.util.HashMap;

/**
 * Created by Dotin school 6 on 3/16/2015.
 */
public class RealCustomerCRUD {
    static Connection connection;
    static PreparedStatement statement;

    static {
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

    public static void insert(RealCustomer realCustomer) throws SQLException {
        statement = connection.prepareStatement("INSERT INTO REALCUSTOMER (CUSTOMER_ID,NAME,FAMILY,NAME_OF_FATHER, DATE_OF_BIRTH,ID_CARD_NUMBER) VALUES (?,?,?,?,?,?);");
        statement.setLong(1, realCustomer.getId());
        statement.setString(2, realCustomer.getName());
        statement.setString(3, realCustomer.getFamily());
        statement.setString(4, realCustomer.getNameOfFather());
        statement.setString(5, realCustomer.getDateOfBirth());
        statement.setString(6, realCustomer.getIdCardNumber());
        statement.executeUpdate();
    }

    public static void update(RealCustomer realCustomer) throws SQLException {
        statement = connection.prepareStatement("UPDATE REALCUSTOMER SET NAME=?, FAMILY=?, ID_CARD_NUMBER=?, NAME_OF_FATHER=?, DATE_OF_BIRTH=? WHERE CUSTOMER_ID=?;");
        statement.setString(1, realCustomer.getName());
        statement.setString(2, realCustomer.getFamily());
        statement.setString(3, realCustomer.getIdCardNumber());
        statement.setString(4, realCustomer.getNameOfFather());
        statement.setString(5, realCustomer.getDateOfBirth());
        statement.setLong(6, realCustomer.getId());
        System.out.println(statement.toString());
        statement.executeUpdate();
    }

    public static void delete(long customerId) throws SQLException {
        statement = connection.prepareStatement("DELETE FROM REALCUSTOMER WHERE CUSTOMER_ID=?;");
        statement.setLong(1, customerId);
        statement.executeUpdate();
    }

    public static long selectCustomerId(String idCardNumber) throws SQLException {
        long customerId = 0;
        statement = connection.prepareStatement("SELECT CUSTOMER_ID FROM REALCUSTOMER WHERE ID_CARD_NUMBER=?;");
        statement.setString(1, idCardNumber);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            customerId = resultSet.getLong("CUSTOMER_ID");
        }
        return customerId;
    }

    public static HashMap<Long, RealCustomer> select(RealCustomer realCustomer, long customerNumber) throws SQLException {
        HashMap<Long, RealCustomer> realCustomers = new HashMap<Long, RealCustomer>();
        statement = connection.prepareStatement("SELECT T1.*, T2.CUSTOMER_NUMBER AS CUSTOMER_NUM FROM REALCUSTOMER AS T1 INNER JOIN CUSTOMER T2 ON T2.CUSTOMER_ID=T1.CUSTOMER_ID WHERE ((T2.CUSTOMER_NUMBER=?) OR ((T1.NAME=?) OR (T1.FAMILY=?) OR (ID_CARD_NUMBER=?)));");

        statement.setLong(1, customerNumber);

        if (realCustomer.getName() != null && !(realCustomer.getName()).isEmpty()) {
            statement.setString(2, realCustomer.getName());
        } else {
            statement.setString(2, "NULL");
        }

        if (realCustomer.getFamily() != null && !(realCustomer.getFamily()).isEmpty()) {
            statement.setString(3, realCustomer.getFamily());
        } else statement.setString(3, "NULL");

        if (realCustomer.getIdCardNumber() != null && !(realCustomer.getIdCardNumber()).isEmpty()) {
            statement.setString(4, realCustomer.getIdCardNumber());
        } else {
            statement.setString(4, "NULL");
        }
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            RealCustomer realCustomer1 = new RealCustomer();
            realCustomer1.setName(resultSet.getString("NAME"));
            realCustomer1.setFamily(resultSet.getString("FAMILY"));
            realCustomer1.setDateOfBirth(resultSet.getString("DATE_OF_BIRTH"));
            realCustomer1.setNameOfFather(resultSet.getString("NAME_OF_FATHER"));
            realCustomer1.setId(resultSet.getLong("CUSTOMER_ID"));
            realCustomer1.setIdCardNumber(resultSet.getString("ID_CARD_NUMBER"));
            realCustomers.put(resultSet.getLong("CUSTOMER_NUM"), realCustomer1);
        }
        return realCustomers;
    }

    public static boolean isIn(String idCardNumber) throws SQLException {
        boolean exist = false;
        statement = connection.prepareStatement("SELECT * FROM REALCUSTOMER WHERE ID_CARD_NUMBER=?;");
        statement.setString(1, idCardNumber);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) exist = true;
        return exist;
    }
}
