package org.example;

import org.example.Car;
import org.example.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an online car store system where customers can view and buy cars.
 * This class manages the inventory of cars and handles purchases.
 */
public class CarStore {

    private List<Car> cars;
    private Map<String, Customer> loggedInCustomers;

    /**
     * Constructs a new CarStore with an empty car list.
     */
    public CarStore() {
        cars = new ArrayList<>();
        loggedInCustomers = new HashMap<>();
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
     * Logs in a customer to the store.
     *
     * @param customer the customer to log in
     * @throws IllegalStateException if the username is already logged in
     */
    public void login(Customer customer) {
        if (loggedInCustomers.containsKey(customer.getUsername())) {
            throw new IllegalStateException("Username '" + customer.getUsername() + "' is already logged in");
        }
        customer.setLoggedIn(true);
        loggedInCustomers.put(customer.getUsername(), customer);
    }

    /**
     * Logs out a customer from the store.
     *
     * @param username the username of the customer to log out
     */
    public void logout(String username) {
        Customer customer = loggedInCustomers.remove(username);
        if (customer != null) {
            customer.setLoggedIn(false);
        }
    }

    /**
     * Gets a logged-in customer by username.
     *
     * @param username the username to search for
     * @return the customer if logged in, null otherwise
     */
    public Customer getLoggedInCustomer(String username) {
        return loggedInCustomers.get(username);
    }

    /**
     * Returns the number of logged-in customers.
     *
     * @return the count of logged-in customers
     */
    public int getLoggedInCustomerCount() {
        return loggedInCustomers.size();
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

        Customer customer = new Customer("alice_j", "Alice Johnson", "alice@email.com", 50000);
        store.login(customer);

        store.displayCars();
        store.purchaseCar(customer, "Camry");
        store.displayCars();
    }
}