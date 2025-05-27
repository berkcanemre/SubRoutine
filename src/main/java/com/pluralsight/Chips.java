package com.pluralsight; // Package declaration

// Chips class to represent a chip order
public class Chips {
    private String type; // Type of chips

    // Constructor to initialize chip type
    public Chips(String type) {
        this.type = type; // Set chip type
    }

    // Return the fixed price of chips
    public double getPrice() {
        return 1.50;
    }

    // Return a string representation of the chip
    public String getDescription() {
        return type + " chips - $1.50";
    }
}
