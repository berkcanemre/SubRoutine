package com.pluralsight; // Package declaration

import java.util.ArrayList; // Import ArrayList
import java.util.List;      // Import List

// Sandwich class to model a customizable sandwich
public class Sandwich {
    private SandwichSize size;        // Size of the sandwich
    private BreadType breadType;      // Type of bread
    private boolean toasted;          // Whether it is toasted
    private List<Topping> toppings;   // List of all toppings
    private List<Sauce> sauces;       // List of sauces

    // Constructor to initialize sandwich with size and bread
    public Sandwich(SandwichSize size, BreadType breadType, boolean toasted) {
        this.size = size;                   // Set sandwich size
        this.breadType = breadType;         // Set bread type
        this.toasted = toasted;             // Set toasted status
        this.toppings = new ArrayList<>();  // Initialize toppings list
        this.sauces = new ArrayList<>();    // Initialize sauces list
    }

    // Add a topping to the sandwich
    public void addTopping(Topping topping) {
        toppings.add(topping); // Add to list
    }

    // Add a sauce to the sandwich
    public void addSauce(Sauce sauce) {
        sauces.add(sauce); // Add to list
    }

    // Calculate the total price of the sandwich
    public double getPrice() {
        double price = switch (size) {
            case FOUR_INCH -> 5.50;      // Base price
            case EIGHT_INCH -> 7.00;
            case TWELVE_INCH -> 8.50;
        };

        for (Topping topping : toppings) {
            price += topping.getPrice(size); // Add price of each topping
        }

        return price; // Return total price
    }

    // Return sandwich description
    public String getDescription() {
        StringBuilder sb = new StringBuilder(); // Create string builder
        sb.append(size).append(" ").append(breadType); // Add size and bread
        if (toasted) sb.append(" (Toasted)"); // Add toasted if applicable
        sb.append("\nToppings:\n");
        for (Topping topping : toppings) {
            sb.append("- ").append(topping.getName()).append("\n"); // List toppings
        }
        sb.append("Sauces:\n");
        for (Sauce sauce : sauces) {
            sb.append("- ").append(sauce).append("\n"); // List sauces
        }
        sb.append("Price: $").append(String.format("%.2f", getPrice())); // Add price
        return sb.toString(); // Return full description
    }
}
