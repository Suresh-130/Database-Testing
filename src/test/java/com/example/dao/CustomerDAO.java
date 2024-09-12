package com.example.dao;

import com.example.base.DatabaseConnection;
import com.example.models.Customer;
import com.example.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    public static Customer getCustomerById(int id){
        Connection connection = DatabaseConnection.getConnection();
        Customer customer = null;
        try {
            String query = "SELECT * FROM customers WHERE customerNumber = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customer = DBUtils.setCustomerDetails(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public static boolean createCustomer(Customer customer) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, " +
                "addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int rowsAffected = DBUtils.setCreateCustomerStatementParams(statement, customer);
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateCustomer(Customer customer) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE customers SET customerName = ?, contactLastName = ?, contactFirstName = ?, phone = ?, " +
                "addressLine1 = ?, addressLine2 = ?, city = ?, state = ?, postalCode = ?, country = ?, " +
                "salesRepEmployeeNumber = ?, creditLimit = ? WHERE customerNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int rowsAffected = DBUtils.setUpdateCustomerStatementParams(statement, customer);
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean deleteCustomer(int id) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String query = "DELETE FROM customers WHERE customerNumber = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
