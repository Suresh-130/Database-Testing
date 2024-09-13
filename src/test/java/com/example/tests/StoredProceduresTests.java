package com.example.tests;

import com.example.base.DatabaseConnection;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Epic("Stored Procedure Tests")
@Feature("Customer Procedures")
public class StoredProceduresTests {

    private static final Logger logger = LogManager.getLogger(StoredProceduresTests.class);

    @Test
    @Description("Test stored procedure for customer order history")
    @Severity(SeverityLevel.CRITICAL)
    public void testCustomerOrdersHistory() {
        logger.info("Starting test for customer order history procedure.");
        Connection connection = DatabaseConnection.getConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{call GetCustomerOrderHistory(?)}");
            callableStatement.setInt(1, 103);
            ResultSet resultSet = callableStatement.executeQuery();
            Assert.assertTrue(resultSet.next(), "Stored procedure returned no results.");
            logger.info("Customer order history retrieved successfully.");
        } catch (SQLException e) {
            logger.error("Stored procedure execution failed.", e);
            Assert.fail("Stored procedure execution failed.");
        }
    }

    @Test
    @Description("Test stored procedure for calculating customer balance")
    @Severity(SeverityLevel.CRITICAL)
    public void testCustomerBalance() {
        logger.info("Starting test for calculating customer balance.");
        Connection connection = DatabaseConnection.getConnection();
        String procedureCall = "{CALL CalculateCustomerBalance(?, ?)}";
        try (CallableStatement callableStatement = connection.prepareCall(procedureCall)) {
            callableStatement.setInt(1, 103);
            callableStatement.registerOutParameter(2, java.sql.Types.DOUBLE);
            callableStatement.execute();
            double balance = callableStatement.getDouble(2);
            Assert.assertTrue(balance >= 0, "Balance should be non-negative.");
            logger.info("Customer balance retrieved: " + balance);
        } catch (SQLException e) {
            logger.error("Stored procedure execution failed.", e);
            Assert.fail("Stored procedure test failed due to SQL error.");
        }
    }
}
