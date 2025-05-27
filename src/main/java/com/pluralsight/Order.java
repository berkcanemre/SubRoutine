package com.pluralsight; // Package declaration

import java.util.ArrayList; // Import ArrayList
import java.util.List;      // Import List

// Order class to manage all items in an order
public class Order {
    private List<Sandwich> sandwiches; // List of sandwiches
    private List<Drink> drinks;        // List of drinks
    private List<Chips> chips;         // List of chips

    // Constructor initializes the lists
    public Order() {
        this.sandwiches = new ArrayList<>(); // Initialize sandwich list
        this.drinks = new ArrayList<>();     // Initialize drinks list
        this.chips = new ArrayList<>();      // Initialize chips list
    }

    // Add a sandwich
    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich); // Add to list
    }

    // Add a drink
    public void addDrink(Drink drink) {
        drinks.add(drink); // Add to list
    }

    // Add chips
    public void addChips(Chips chip) {
        chips.add(chip); // Add to list
    }

    // Calculate total cost of the order
    public double getTotal() {
        double total = 0.0;

        for (Sandwich s : sandwiches) total += s.getPrice(); // Add sandwich prices
        for (Drink d : drinks) total += d.getPrice();         // Add drink prices
        for (Chips c : chips) total += c.getPrice();          // Add chips prices

        return total;
    }

    // Return full order summary
    public String getSummary() {
        StringBuilder sb = new StringBuilder(); // Create string builder

        for (int i = 0; i < sandwiches.size(); i++) {
            sb.append("\nSandwich #").append(i + 1).append(":\n");
            sb.append(sandwiches.get(i).getDescription()).append("\n");
        }

        for (Drink drink : drinks) {
            sb.append(drink.getDescription()).append("\n"); // Add drink info
        }

        for (Chips chip : chips) {
            sb.append(chip.getDescription()).append("\n"); // Add chip info
        }

        sb.append("Total: $").append(String.format("%.2f", getTotal())); // Total price

        return sb.toString(); // Return final string
    }
}
