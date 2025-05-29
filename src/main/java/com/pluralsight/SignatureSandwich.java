package com.pluralsight;
// This class extends Sandwich to represent pre-defined signature sandwiches.
// It provides static factory methods to create instances of specific signature sandwiches.
public class SignatureSandwich extends Sandwich {

    // Constructor for SignatureSandwich, calling the parent Sandwich constructor.
    public SignatureSandwich(String bread, String size) {
        super(bread, size);
    }

    // Static factory method to create a BLT signature sandwich.
    public static SignatureSandwich createBLT() {
        SignatureSandwich blt = new SignatureSandwich("white", "8\"");
        blt.addMeat("bacon");
        blt.addCheese("cheddar");
        blt.addRegularTopping("lettuce");
        blt.addRegularTopping("tomato");
        blt.addSauce("ranch");
        blt.setToasted(true);
        return blt;
    }

    // Static factory method to create a Philly Cheese Steak signature sandwich.
    public static SignatureSandwich createPhillyCheeseSteak() {
        SignatureSandwich philly = new SignatureSandwich("white", "8\"");
        philly.addMeat("steak");
        philly.addCheese("american");
        philly.addRegularTopping("peppers");
        philly.addSauce("mayo");
        philly.setToasted(true); // Assuming Philly Cheese Steak is toasted by default.
        return philly;
    }

    // Overrides getName to provide a specific name for signature sandwiches.
    @Override
    public String getName() {
        return super.getName() + " (Signature)";
    }
}