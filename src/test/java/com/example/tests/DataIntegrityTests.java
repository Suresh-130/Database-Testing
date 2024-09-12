package com.example.tests;

import com.example.base.DatabaseConnection;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Epic("Database Integrity Tests")
@Feature("Data Constraints")
public class DataIntegrityTests {

    private static final Logger logger = LogManager.getLogger(DataIntegrityTests.class);

    @Test
    @Description("Test for unique constraint on customer number")
    @Severity(SeverityLevel.CRITICAL)
    public void testUniqueConstraintOnCustomerNumber() {
        logger.info("Starting test for unique constraint on customer number.");
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO customers (customerNumber, customerName) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, 103);
            statement.setString(2, "Duplicate Customer");
            int rowsAffected = statement.executeUpdate();
            Assert.fail("Unique constraint on 'customerNumber' failed, duplicate customer was inserted");
        } catch (SQLException e) {
            logger.error("SQL Exception occurred while testing unique constraint.", e);
        }
    }

    @Test
    @Description("Test referential integrity on delete operation")
    @Severity(SeverityLevel.BLOCKER)
    public void testReferentialIntegrityOnDelete() {
        logger.info("Starting test for referential integrity on delete operation.");
        Connection connection = DatabaseConnection.getConnection();
        try {
            String deleteQuery = "DELETE FROM employees WHERE employeeNumber = ?";
            try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
                statement.setInt(1, 1370);
                int rowsAffected = statement.executeUpdate();
                Assert.fail("Referential integrity violated. Employee with customers was deleted.");
            }
        } catch (SQLException e) {
            logger.error("SQL Exception occurred while testing referential integrity.", e);
        }
    }
}
