package com.pluralsight;

// The Drink class represents a beverage, implementing the MenuItem interface.
public class Drink implements MenuItem {
    private String flavor; // Stores the flavor of the drink (e.g., "Coke", "Sprite").
    private String size;   // Stores the size of the drink (e.g., "Small", "Medium", "Large").

    // Constructor to initialize a new Drink object.
    public Drink(String flavor, String size) {
        this.flavor = flavor;
        this.size = size;
    }

    // --- Getters ---
    public String getFlavor() {
        return flavor;
    }

    public String getSize() {
        return size;
    }

    // Overrides the getName method from MenuItem to provide a descriptive name for the drink.
    @Override
    public String getName() {
        return size + " " + flavor + " Drink";
    }

    // Overrides the getPrice method from MenuItem to calculate the price based on size.
    @Override
    public double getPrice() {
        switch (size) {
            case "Small":
                return 2.00; // Price for a small drink.
            case "Medium":
                return 2.50; // Price for a medium drink.
            case "Large":
                return 3.00; // Price for a large drink.
            default:
                // Default to 0.0 for unknown sizes, though input validation should prevent this.
                return 0.0;
        }
    }

    // Overrides the toString method to provide a concise string representation of the drink.
    @Override
    public String toString() {
        return String.format("%s %s Drink - $%.2f", size, flavor, getPrice());
    }
}