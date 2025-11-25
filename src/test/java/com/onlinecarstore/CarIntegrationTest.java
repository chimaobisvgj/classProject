package com.onlinecarstore;

import org.example.Car;
import org.example.CarStore;
import org.example.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for testing the interaction between Customer and CarStore modules.
 * Tests login functionality to ensure users cannot log in with the same username twice.
 */
public class CarIntegrationTest {

    private CarStore carStore;
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;

    @BeforeEach
    void setUp() {
        carStore = new CarStore();
        
        // Create test customers with different usernames
        customer1 = new Customer("john_doe", "John Doe", "john@email.com", 50000);
        customer2 = new Customer("jane_smith", "Jane Smith", "jane@email.com", 60000);
        customer3 = new Customer("john_doe", "John Duplicate", "john.dup@email.com", 40000);
        
        // Add test cars
        carStore.addCar(new Car("Toyota", "Camry", 28000, true));
        carStore.addCar(new Car("Tesla", "Model 3", 40000, true));
        carStore.addCar(new Car("BMW", "X5", 55000, true));
    }

    @Test
    void loginWithUniqueUsernameShouldSucceed() {
        // Act
        carStore.login(customer1);
        
        // Assert
        assertTrue(customer1.isLoggedIn());
        assertEquals(1, carStore.getLoggedInCustomerCount());
        assertEquals(customer1, carStore.getLoggedInCustomer("john_doe"));
    }

    @Test
    void loginWithSameUsernameTwiceShouldThrowException() {
        // Arrange - First customer logs in successfully
        carStore.login(customer1);
        
        // Act & Assert - Attempting to login with same username should throw exception
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            carStore.login(customer3); // customer3 has the same username as customer1
        });
        
        assertTrue(exception.getMessage().contains("already logged in"));
        assertEquals(1, carStore.getLoggedInCustomerCount());
    }

    @Test
    void multipleDifferentUsersCanLoginSimultaneously() {
        // Act
        carStore.login(customer1);
        carStore.login(customer2);
        
        // Assert
        assertTrue(customer1.isLoggedIn());
        assertTrue(customer2.isLoggedIn());
        assertEquals(2, carStore.getLoggedInCustomerCount());
    }

    @Test
    void logoutShouldAllowReloginWithSameUsername() {
        // Arrange
        carStore.login(customer1);
        assertTrue(customer1.isLoggedIn());
        
        // Act - Logout
        carStore.logout("john_doe");
        
        // Assert - Customer should be logged out
        assertFalse(customer1.isLoggedIn());
        assertEquals(0, carStore.getLoggedInCustomerCount());
        
        // Act - Login again with same username (different customer object)
        carStore.login(customer3);
        
        // Assert - Should succeed after logout
        assertTrue(customer3.isLoggedIn());
        assertEquals(1, carStore.getLoggedInCustomerCount());
    }

    @Test
    void loggedInCustomerCanPurchaseCar() {
        // Arrange
        carStore.login(customer1);
        double initialBalance = customer1.getBalance();
        
        // Act
        carStore.purchaseCar(customer1, "Camry");
        
        // Assert
        assertTrue(customer1.getBalance() < initialBalance);
        assertEquals(28000, initialBalance - customer1.getBalance());
    }
}
