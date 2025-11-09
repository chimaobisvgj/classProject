package org.example;

/**
 * Represents a car available in the online car store.
 * Each car has a brand, model, price, and availability status.
 *
 * @author Chima
 * @version 1.0
 */
public class Car {

    private String brand;
    private String model;
    private double price;
    private boolean available;

    /**
     * Constructs a new Car object with the specified details.
     *
     * @param brand      the brand of the car (e.g., "Toyota")
     * @param model      the model of the car (e.g., "Camry")
     * @param price      the price of the car
     * @param available  whether the car is currently available for sale
     */
    public Car(String brand, String model, double price, boolean available) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.available = available;
    }

    /**
     * Returns the car brand.
     *
     * @return the brand of the car
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Returns the car model.
     *
     * @return the model of the car
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns the price of the car.
     *
     * @return the car price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Checks if the car is available.
     *
     * @return true if the car is available; false otherwise
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Updates the car's availability.
     *
     * @param available true if available, false if sold or reserved
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Returns a string description of the car.
     *
     * @return a formatted string with car details
     */
    @Override
    public String toString() {
        return brand + " " + model + " - $" + price + (available ? " (Available)" : " (Sold)");
    }
}