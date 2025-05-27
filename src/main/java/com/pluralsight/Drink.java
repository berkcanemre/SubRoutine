package com.pluralsight; // Package declaration

// Drink class to store drink info
public class Drink {
    private String flavor;    // Flavor of the drink
    private String size;      // Size: small, medium, large

    // Constructor to initialize drink
    public Drink(String flavor, String size) {
        this.flavor = flavor; // Set flavor
        this.size = size;     // Set size
    }

    // Get price based on size
    public double getPrice() {
        return switch (size.toLowerCase()) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.0;
        };
    }

    // Return a string representation of the drink
    public String getDescription() {
        return size + " " + flavor + " drink - $" + String.format("%.2f", getPrice());
    }
}
