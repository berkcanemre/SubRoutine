package com.pluralsight;

// Cheese topping class extends Topping
public class Cheese extends Topping {

    // Constructor to initialize cheese name and whether it's extra
    public Cheese(String name, boolean isExtra) {
        super(name, isExtra); // Call base constructor
    }

    // Calculate price based on sandwich size and if it's extra
    @Override
    public double getPrice(SandwichSize size) {
        double base = switch (size) {
            case FOUR_INCH -> 0.75;      // Price for 4"
            case EIGHT_INCH -> 1.50;     // Price for 8"
            case TWELVE_INCH -> 2.25;    // Price for 12"
        };
        if (isExtra) {
            base += switch (size) {
                case FOUR_INCH -> 0.30;      // Extra cheese for 4"
                case EIGHT_INCH -> 0.60;     // Extra cheese for 8"
                case TWELVE_INCH -> 0.90;    // Extra cheese for 12"
            };
        }
        return base; // Return total price
    }
}
