package model.crud;

import model.entity.LegalCustomer;

import java.sql.*;
import java.util.HashMap;

/**
 * Created by Dotin school 6 on 3/16/2015.
 */
public class LegalCustomerCRUD {
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

    public static void insert(LegalCustomer legalCustomer) throws SQLException {
        statement = connection.prepareStatement("INSERT INTO LEGALCUSTOMER (CUSTOMER_ID, NAME, DATE_OF_REGISTRATION, LEGAL_ID_NUMBER) VALUES (?,?,?,?);");
        statement.setLong(1, legalCustomer.getId());
        statement.setString(2, legalCustomer.getName());
        statement.setString(3, legalCustomer.getDateOfRegistration());
        statement.setString(4, legalCustomer.getLegalIdNumber());
        statement.executeUpdate();
    }

    public static void update(LegalCustomer legalCustomer) throws SQLException {
        statement = connection.prepareStatement("UPDATE LEGALCUSTOMER SET NAME=?, DATE_OF_REGISTRATION=?, LEGAL_ID_NUMBER=? WHERE CUSTOMER_ID=?;");
        statement.setString(1, legalCustomer.getName());
        statement.setString(2, legalCustomer.getDateOfRegistration());
        statement.setString(3, legalCustomer.getLegalIdNumber());
        statement.setLong(4, legalCustomer.getId());
        statement.executeUpdate();

    }

    public static void delete(String legalIdNumber) throws SQLException {
        statement = connection.prepareStatement("DELETE FROM LEGALCUSTOMER WHERE LEGAL_ID_NUMBER=?");
        statement.setString(1, legalIdNumber);
        statement.executeUpdate();
    }

    public static long selectCustomerId(String legalIdNumber) throws SQLException {
        long customerId = 0;
        statement = connection.prepareStatement("SELECT CUSTOMER_ID FROM LEGALCUSTOMER WHERE LEGAL_ID_NUMBER=?;");
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            customerId = resultSet.getLong("CUSTOMER_ID");
        }
        return customerId;
    }

    public static HashMap<Long, LegalCustomer> select(LegalCustomer legalCustomer, long customerNumber) throws SQLException {
        HashMap<Long, LegalCustomer> legalCustomers = new HashMap<Long, LegalCustomer>();

        statement = connection.prepareStatement("SELECT T1.*, T2.CUSTOMER_NUMBER AS CUSTOMER_NUM FROM LEGALCUSTOMER AS T1 INNER JOIN CUSTOMER T2 ON T2.CUSTOMER_ID=T1.CUSTOMER_ID WHERE ((T2.CUSTOMER_NUMBER=?) AND ((T1.NAME=?) OR (LEGAL_ID_NUMBER=?)));");

        statement.setLong(1, customerNumber);

        if (legalCustomer.getName() != null && !(legalCustomer.getName()).isEmpty()) {
            statement.setString(2, legalCustomer.getName());
        } else statement.setString(2, "NULL");

        if (legalCustomer.getLegalIdNumber() != null && !(legalCustomer.getLegalIdNumber()).isEmpty()) {
            statement.setString(3, legalCustomer.getLegalIdNumber());
        } else statement.setString(3, "NULL");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            LegalCustomer legalCustomer1 = new LegalCustomer();
            legalCustomer1.setName(resultSet.getString("NAME"));
            legalCustomer1.setDateOfRegistration(resultSet.getString("DATE_OF_REGISTRATION"));
            legalCustomer1.setLegalIdNumber(resultSet.getString("LEGAL_ID_NUMBER"));
            legalCustomer1.setId(resultSet.getLong("CUSTOMER_ID"));
            legalCustomers.put(resultSet.getLong("CUSTOMER_NUM"), legalCustomer1);
        }
        return legalCustomers;
    }

}
