package com.pluralsight;

// Regular topping class (free toppings)
public class RegularTopping extends Topping {

    // Constructor for a regular topping (extras don't cost more)
    public RegularTopping(String name) {
        super(name, false); // Set name and mark as not extra
    }

    // Regular toppings are free regardless of sandwich size
    @Override
    public double getPrice(SandwichSize size) {
        return 0.0; // No charge
    }
}
