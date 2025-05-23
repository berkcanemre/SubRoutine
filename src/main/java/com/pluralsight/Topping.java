package com.pluralsight;

// Abstract class for a topping (base class)
public abstract class Topping {
    protected String name;      // Name of the topping
    protected boolean isExtra;  // Whether the topping is extra

    // Constructor for setting name and extra status
    public Topping(String name, boolean isExtra) {
        this.name = name;           // Assign name
        this.isExtra = isExtra;     // Assign whether it's extra
    }

    // Abstract method to calculate price depending on sandwich size
    public abstract double getPrice(SandwichSize size);

    // Get the name of the topping, indicating if it's extra
    public String getName() {
        return name + (isExtra ? " (Extra)" : ""); // Add (Extra) if applicable
    }
}
