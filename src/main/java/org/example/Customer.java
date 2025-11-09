package org.example;

/**
 * Represents a customer who can browse and purchase cars from the online store.
 * Each customer has a name, email, and balance.
 */
public class Customer {

    private String name;
    private String email;
    private double balance;

    /**
     * Constructs a new Customer object.
     *
     * @param name     the customer's full name
     * @param email    the customer's email address
     * @param balance  the current balance available for purchases
     */
    public Customer(String name, String email, double balance) {
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    /**
     * Returns the customer's name.
     *
     * @return the full name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the customer's email address.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the customer's balance.
     *
     * @return the available balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deducts an amount from the customer's balance.
     *
     * @param amount the amount to deduct
     */
    public void deductBalance(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    /**
     * Returns a string description of the customer.
     *
     * @return a formatted string with customer details
     */
    @Override
    public String toString() {
        return name + " (" + email + ") - Balance: $" + balance;
    }
}