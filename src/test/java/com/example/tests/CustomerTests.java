package com.example.tests;

import com.example.dao.CustomerDAO;
import com.example.models.Customer;
import com.example.utils.DBUtils;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Customer Management Tests")
@Feature("Customer CRUD Operations")
public class CustomerTests {

    private static final Logger logger = LogManager.getLogger(CustomerTests.class);

    @Test(priority = 1)
    @Description("Test for creating a customer")
    @Severity(SeverityLevel.BLOCKER)
    public void testCreateCustomer() {
        logger.info("Starting test for creating a new customer.");
        Customer customer = DBUtils.generateCreateCustomer();
        boolean isCreated = CustomerDAO.createCustomer(customer);
        Assert.assertTrue(isCreated, "Customer creation failed.");
        logger.info("Customer created successfully.");
    }

    @Test(priority = 2)
    @Description("Test for retrieving customer by ID")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetCustomerById() {
        logger.info("Starting test for retrieving customer by ID.");
        Customer customer = CustomerDAO.getCustomerById(1);
        Assert.assertNotNull(customer, "Customer not found.");
        logger.info("Customer retrieved successfully.");
    }

    @Test(priority = 3)
    @Description("Test for updating customer details")
    @Severity(SeverityLevel.CRITICAL)
    public void testUpdateCustomer() {
        logger.info("Starting test for updating customer.");
        Customer customer = CustomerDAO.getCustomerById(1);
        boolean isUpdated = CustomerDAO.updateCustomer(DBUtils.genarateUpdateCustomer(customer));
        Assert.assertTrue(isUpdated, "Customer update failed.");
        logger.info("Customer updated successfully.");
    }

    @Test(priority = 4)
    @Description("Test for deleting customer")
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteCustomer() {
        logger.info("Starting test for deleting customer.");
        boolean isDeleted = CustomerDAO.deleteCustomer(1);
        Assert.assertTrue(isDeleted, "Customer deletion failed.");
        logger.info("Customer deleted successfully.");
    }
}
