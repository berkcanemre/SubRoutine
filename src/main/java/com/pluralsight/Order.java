package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

// The Order class manages a collection of sandwiches, drinks, and chips for a single customer order.
public class Order {
    private List<Sandwich> sandwiches; // List to store all sandwiches in the order.
    private List<Drink> drinks;       // List to store all drinks in the order.
    private List<Chips> chips;        // List to store all chips in the order.

    // Constructor to initialize an empty order.
    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    // Adds a Sandwich object to the order.
    public void addSandwich(Sandwich sandwich) {
        this.sandwiches.add(sandwich);
    }

    // Adds a Drink object to the order.
    public void addDrink(Drink drink) {
        this.drinks.add(drink);
    }

    // Adds a Chips object to the order.
    public void addChips(Chips chip) {
        this.chips.add(chip);
    }

    // --- Getters for lists of items ---
    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Chips> getChips() {
        return chips;
    }

    // --- Methods to remove items ---

    //Removes a sandwich from the order by its index.
    public Sandwich removeSandwich(int index) {
        if (index >= 0 && index < sandwiches.size()) {
            return sandwiches.remove(index);
        }
        return null; // Index out of bounds.
    }
    //Removes adrink from the order by its index.
    public Drink removeDrink(int index) {
        if (index >= 0 && index < drinks.size()) {
            return drinks.remove(index);
        }
        return null; // Index out of bounds.
    }

    //Removes chips from the order by its index.
    public Chips removeChips(int index) {
        if (index >= 0 && index < chips.size()) {
            return chips.remove(index);
        }
        return null; // Index out of bounds.
    }

    // Calculates the total price of all items in the order.
    public double calculateTotalPrice() {
        double total = 0.0;
        // Sum prices of all sandwiches.
        for (Sandwich s : sandwiches) {
            total += s.getPrice();
        }
        // Sum prices of all drinks.
        for (Drink d : drinks) {
            total += d.getPrice();
        }
        // Sum prices of all chips.
        for (Chips c : chips) {
            total += c.getPrice();
        }
        return total;
    }

    // Clears all items from the current order.
    public void clearOrder() {
        sandwiches.clear();
        drinks.clear();
        chips.clear();
    }

    // Overrides the toString method to provide a detailed summary of the order.
    @Override
    public String toString() {
        StringBuilder orderSummary = new StringBuilder();
        orderSummary.append("~~~~~~~~~~~~~~~~~~~~~~~~ Your Order Details ~~~~~~~~~~~~~~~~~~~~~~\n");

        // Check if the order is empty and return appropriate message.
        if (sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty()) {
            orderSummary.append("Order is currently empty.\n");
            return orderSummary.toString();
        }

        // Append sandwich details if any sandwiches are in the order.
        if (!sandwiches.isEmpty()) {
            orderSummary.append("Sandwiches:\n");
            for (int i = 0; i < sandwiches.size(); i++) {
                // Indent sandwich details for better readability.
                orderSummary.append("  " + (i + 1) + ". " + sandwiches.get(i).toString().replace("\n", "\n  ") + "\n");
            }
        }

        // Append drink details if any drinks are in the order.
        if (!drinks.isEmpty()) {
            orderSummary.append("\nDrinks:\n");
            for (int i = 0; i < drinks.size(); i++) {
                orderSummary.append("  " + (i + 1) + ". " + drinks.get(i).toString() + "\n");
            }
        }

        // Append chip details if any chips are in the order.
        if (!chips.isEmpty()) {
            orderSummary.append("\nChips:\n");
            for (int i = 0; i < chips.size(); i++) {
                orderSummary.append("  " + (i + 1) + ". " + chips.get(i).toString() + "\n");
            }
        }

        // Append the total calculated cost of the order.
        orderSummary.append(String.format("\nTotal Cost: $%.2f\n", calculateTotalPrice()));
        orderSummary.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        return orderSummary.toString();
    }
}