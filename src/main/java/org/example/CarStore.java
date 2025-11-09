package org.example;

import org.example.Car;
import org.example.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an online car store system where customers can view and buy cars.
 * This class manages the inventory of cars and handles purchases.
 */
public class CarStore {

    private List<Car> cars;

    /**
     * Constructs a new CarStore with an empty car list.
     */
    public CarStore() {
        cars = new ArrayList<>();
    }

    /**
     * Adds a car to the store inventory.
     *
     * @param car the car to add
     */
    public void addCar(Car car) {
        cars.add(car);
    }

    /**
     * Displays all available cars in the store.
     */
    public void displayCars() {
        System.out.println("Available Cars:");
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    /**
     * Allows a customer to purchase a car by model name.
     *
     * @param customer the customer making the purchase
     * @param model    the model name of the car
     */
    public void purchaseCar(Customer customer, String model) {
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model) && car.isAvailable()) {
                if (customer.getBalance() >= car.getPrice()) {
                    customer.deductBalance(car.getPrice());
                    car.setAvailable(false);
                    System.out.println(customer.getName() + " successfully purchased " + car.getBrand() + " " + model);
                    return;
                } else {
                    System.out.println("Insufficient funds for this purchase.");
                    return;
                }
            }
        }
        System.out.println("Car model not found or unavailable.");
    }

    /**
     * Example main method to test the store.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        CarStore store = new CarStore();
        store.addCar(new Car("Toyota", "Camry", 28000, true));
        store.addCar(new Car("Tesla", "Model 3", 40000, true));

        Customer customer = new Customer("Alice Johnson", "alice@email.com", 50000);

        store.displayCars();
        store.purchaseCar(customer, "Camry");
        store.displayCars();
    }
}