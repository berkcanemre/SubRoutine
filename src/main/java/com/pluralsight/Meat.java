package com.pluralsight;

// Meat topping class extends Topping
public class Meat extends Topping {

    // Constructor to initialize meat name and whether it's extra
    public Meat(String name, boolean isExtra) {
        super(name, isExtra); // Call base constructor
    }

    // Calculate price based on sandwich size and if it's extra
    @Override
    public double getPrice(SandwichSize size) {
        double base = switch (size) {
            case FOUR_INCH -> 1.00;      // Price for 4"
            case EIGHT_INCH -> 2.00;     // Price for 8"
            case TWELVE_INCH -> 3.00;    // Price for 12"
        };
        if (isExtra) {
            base += switch (size) {
                case FOUR_INCH -> 0.50;      // Extra meat for 4"
                case EIGHT_INCH -> 1.00;     // Extra meat for 8"
                case TWELVE_INCH -> 1.50;    // Extra meat for 12"
            };
        }
        return base; // Return total price
    }
}
