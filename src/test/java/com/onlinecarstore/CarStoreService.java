package com.onlinecarstore;

public class CarStoreService {
    public String findCarById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid car ID");
        }
        if (id == 10) {
            return "Toyota Camry";
        }
        return null;
    }

    public double calculateFinalPrice(double basePrice, double discountPercent) {
        if (basePrice < 0 || discountPercent < 0) {
            throw new IllegalArgumentException("Price or discount cannot be negative");
        }
        return basePrice - (basePrice * discountPercent / 100);
    }
}