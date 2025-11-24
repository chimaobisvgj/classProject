package com.onlinecarstore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarStoreServiceTest {

    com.onlinecarstore.CarStoreService service = new com.onlinecarstore.CarStoreService();

    // 1. Happy path — finding an existing car
    @Test
    void testFindCarById_validId_returnsCar() {
        String result = service.findCarById(10);
        assertEquals("Toyota Camry", result);
    }

    // 2. Exception — invalid car ID
    @Test
    void testFindCarById_invalidId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.findCarById(0);
        });
    }

    // 3. Happy path — calculation of final price
    @Test
    void testCalculateFinalPrice_validValues_returnsCorrectPrice() {
        double finalPrice = service.calculateFinalPrice(20000, 10);
        assertEquals(18000, finalPrice);
    }
}