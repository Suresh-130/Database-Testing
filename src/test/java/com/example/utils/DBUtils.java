package com.example.utils;

import com.example.models.Customer;
import com.github.javafaker.Faker;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class DBUtils {

    public static Customer setCustomerDetails(@NotNull ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerNumber(resultSet.getInt("customerNumber"));
        customer.setCustomerName(resultSet.getString("customerName"));
        customer.setContactLastName(resultSet.getString("contactLastName"));
        customer.setContactFirstName(resultSet.getString("contactFirstName"));
        customer.setPhone(resultSet.getString("phone"));
        customer.setAddressLine1(resultSet.getString("addressLine1"));
        customer.setAddressLine2(resultSet.getString("addressLine2"));
        customer.setCity(resultSet.getString("city"));
        customer.setState(resultSet.getString("state"));
        customer.setPostalCode(resultSet.getString("postalCode"));
        customer.setCountry(resultSet.getString("country"));
        customer.setSalesRepEmployeeNumber(resultSet.getInt("salesRepEmployeeNumber"));
        customer.setCreditLimit(resultSet.getDouble("creditLimit"));

        return customer;
    }

    public static int setCreateCustomerStatementParams(@NotNull PreparedStatement statement, @NotNull Customer customer) throws SQLException {
        statement.setInt(1, customer.getCustomerNumber());
        statement.setString(2, customer.getCustomerName());
        statement.setString(3, customer.getContactLastName());
        statement.setString(4, customer.getContactFirstName());
        statement.setString(5, customer.getPhone());
        statement.setString(6, customer.getAddressLine1());
        statement.setString(7, customer.getAddressLine2());
        statement.setString(8, customer.getCity());
        statement.setString(9, customer.getState());
        statement.setString(10, customer.getPostalCode());
        statement.setString(11, customer.getCountry());
        statement.setInt(12, customer.getSalesRepEmployeeNumber());
        statement.setDouble(13, customer.getCreditLimit());
        return statement.executeUpdate();
    }

    public static int setUpdateCustomerStatementParams(@NotNull PreparedStatement statement, @NotNull Customer customer) throws SQLException {
        statement.setString(1, customer.getCustomerName());
        statement.setString(2, customer.getContactLastName());
        statement.setString(3, customer.getContactFirstName());
        statement.setString(4, customer.getPhone());
        statement.setString(5, customer.getAddressLine1());
        statement.setString(6, customer.getAddressLine2());
        statement.setString(7, customer.getCity());
        statement.setString(8, customer.getState());
        statement.setString(9, customer.getPostalCode());
        statement.setString(10, customer.getCountry());
        statement.setInt(11, customer.getSalesRepEmployeeNumber());
        statement.setDouble(12, customer.getCreditLimit());
        statement.setInt(13, customer.getCustomerNumber());
        return statement.executeUpdate();
    }

    public static Customer generateCreateCustomer() {
        Faker faker = new Faker();
        Random random = new Random();

        Customer customer = new Customer();
        customer.setCustomerNumber(1);
        customer.setCustomerName(faker.company().name());
        customer.setContactLastName(faker.name().lastName());
        customer.setContactFirstName(faker.name().firstName());
        customer.setPhone(faker.phoneNumber().phoneNumber());
        customer.setAddressLine1(faker.address().streetAddress());
        customer.setAddressLine2(faker.address().secondaryAddress());
        customer.setCity(faker.address().city());
        customer.setState(faker.address().state());
        customer.setPostalCode(faker.address().zipCode());
        customer.setCountry(faker.address().country());
        customer.setSalesRepEmployeeNumber(1002);
        customer.setCreditLimit(faker.number().randomDouble(2, 500, 10000));

        return customer;
    }

    public static Customer genarateUpdateCustomer(Customer customer) {
        Faker faker = new Faker();
        Random random = new Random();

        customer.setCustomerName(faker.company().name());
        customer.setContactLastName(faker.name().lastName());
        customer.setContactFirstName(faker.name().firstName());
        customer.setPhone(faker.phoneNumber().phoneNumber());
        customer.setAddressLine1(faker.address().streetAddress());
        customer.setAddressLine2(faker.address().secondaryAddress());
        customer.setCity(faker.address().city());
        customer.setState(faker.address().state());
        customer.setPostalCode(faker.address().zipCode());
        customer.setCountry(faker.address().country());
        customer.setSalesRepEmployeeNumber(1002);
        customer.setCreditLimit(faker.number().randomDouble(2, 500, 10000));

        return customer;
    }
}
