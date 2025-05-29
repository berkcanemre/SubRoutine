package com.pluralsight;

// The Chips class represents a bag of chips, implementing the MenuItem interface.
public class Chips implements MenuItem {
    private String type; // Stores the type or flavor of chips (e.g., "Regular", "Salt & Vinegar").

    // Constructor to initialize a new Chips object.
    public Chips(String type) {
        this.type = type;
    }

    // --- Getter ---
    public String getType() {
        return type;
    }

    // Overrides the getName method from MenuItem to provide a descriptive name for the chips.
    @Override
    public String getName() {
        return type + " Chips";
    }

    // Overrides the getPrice method from MenuItem. Chips have a fixed price.
    @Override
    public double getPrice() {
        return 1.50; // Fixed price for chips as per project requirements.
    }

    // Overrides the toString method to provide a concise string representation of the chips.
    @Override
    public String toString() {
        return String.format("%s Chips - $%.2f", type, getPrice());
    }
}