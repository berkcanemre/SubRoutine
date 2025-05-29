package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

// The Sandwich class represents a customizable sandwich, implementing the MenuItem interface.
public class Sandwich implements MenuItem {
    private String bread; // Stores the type of bread (e.g., "white", "wheat").
    private String size;  // Stores the size of the sandwich (e.g., "4\"", "8\"", "12\"").
    private boolean toasted; // Indicates if the sandwich is toasted.

    // Lists to store various toppings, categorized as per project requirements.
    private List<String> meats;         // Premium toppings: steak, ham, salami, etc.
    private List<String> cheeses;       // Premium toppings: american, provolone, etc.
    private List<String> regularToppings; // Regular toppings: lettuce, peppers, onions, etc.
    private List<String> sauces;        // Sauces: mayo, mustard, ketchup, etc.
    private List<String> sides;         // Sides that can be added to a sandwich (e.g., "au jus sauce").

    // Fields to track explicit "extra" portions of meat and cheese.
    private int numExtraMeats;
    private int numExtraCheeses;

    // Constructor to initialize a new Sandwich object with bread and size.
    public Sandwich(String bread, String size) {
        this.bread = bread;
        this.size = size;
        this.toasted = false; // Sandwiches are not toasted by default.
        // Initialize all lists to prevent NullPointerExceptions when adding items.
        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.sides = new ArrayList<>();
        this.numExtraMeats = 0;   // Initialize extra meats count.
        this.numExtraCheeses = 0; // Initialize extra cheeses count.
    }

    // --- Getters for all properties ---
    public String getBread() {
        return bread;
    }

    public String getSize() {
        return size;
    }

    public boolean isToasted() {
        return toasted;
    }

    public List<String> getMeats() {
        return meats;
    }

    public List<String> getCheeses() {
        return cheeses;
    }

    public List<String> getRegularToppings() {
        return regularToppings;
    }

    public List<String> getSauces() {
        return sauces;
    }

    public List<String> getSides() {
        return sides;
    }

    public int getNumExtraMeats() {
        return numExtraMeats;
    }

    public int getNumExtraCheeses() {
        return numExtraCheeses;
    }

    // --- Setters ---
    // Sets whether the sandwich should be toasted.
    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public void setNumExtraMeats(int numExtraMeats) {
        this.numExtraMeats = numExtraMeats;
    }

    public void setNumExtraCheeses(int numExtraCheeses) {
        this.numExtraCheeses = numExtraCheeses;
    }

    // --- Methods to add toppings ---
    // Adds a meat topping to the sandwich.
    public void addMeat(String meat) {
        this.meats.add(meat);
    }

    // Adds a cheese topping to the sandwich.
    public void addCheese(String cheese) {
        this.cheeses.add(cheese);
    }

    // Adds a regular topping to the sandwich.
    public void addRegularTopping(String topping) {
        this.regularToppings.add(topping);
    }

    // Adds a sauce to the sandwich.
    public void addSauce(String sauce) {
        this.sauces.add(sauce);
    }

    // Adds a side (like au jus) to the sandwich.
    public void addSide(String side) {
        this.sides.add(side);
    }

    // --- Methods to remove toppings ---
    // Removes the first occurrence of a specified meat topping.
    public boolean removeMeat(String meat) {
        return this.meats.remove(meat);
    }

    // Removes the first occurrence of a specified cheese topping.
    public boolean removeCheese(String cheese) {
        return this.cheeses.remove(cheese);
    }

    // Removes the first occurrence of a specified regular topping.
    public boolean removeRegularTopping(String topping) {
        return this.regularToppings.remove(topping);
    }

    // Removes the first occurrence of a specified sauce.
    public boolean removeSauce(String sauce) {
        return this.sauces.remove(sauce);
    }

    // Removes the first occurrence of a specified side.
    public boolean removeSide(String side) {
        return this.sides.remove(side);
    }

    // Overrides the getName method from MenuItem to provide a descriptive name for the sandwich.
    @Override
    public String getName() {
        return size + " " + bread + " Sandwich";
    }

    // Overrides the getPrice method from MenuItem to calculate the total price of the sandwich.
    @Override
    public double getPrice() {
        double price = 0.0;

        // Base sandwich price based on size. Bread type does not affect base price directly.
        switch (size) {
            case "4\"":
                price += 5.50; // Base price for a 4" sandwich.
                break;
            case "8\"":
                price += 7.00; // Base price for an 8" sandwich.
                break;
            case "12\"":
                price += 8.50; // Base price for a 12" sandwich.
                break;
            default:
                // Handle invalid size, though input validation should prevent this.
                System.err.println("Error: Invalid sandwich size encountered: " + size);
                break;
        }

        // Add price for each meat topping.
        for (String meat : meats) {
            switch (size) {
                case "4\"": price += 1.00; break; // Price per meat for 4" sandwich.
                case "8\"": price += 2.00; break; // Price per meat for 8" sandwich.
                case "12\"": price += 3.00; break; // Price per meat for 12" sandwich.
            }
        }

        // Add price for each cheese topping.
        for (String cheese : cheeses) {
            switch (size) {
                case "4\"": price += 0.75; break; // Price per cheese for 4" sandwich.
                case "8\"": price += 1.50; break; // Price per cheese for 8" sandwich.
                case "12\"": price += 2.25; break; // Price per cheese for 12" sandwich.
            }
        }

        // Add price for explicit "extra" meat portions.
        if (numExtraMeats > 0) {
            double extraMeatPrice = 0.0;
            switch (size) {
                case "4\"": extraMeatPrice = 0.50; break;
                case "8\"": extraMeatPrice = 1.00; break;
                case "12\"": extraMeatPrice = 1.50; break;
            }
            price += numExtraMeats * extraMeatPrice;
        }

        // Add price for explicit "extra" cheese portions.
        if (numExtraCheeses > 0) {
            double extraCheesePrice = 0.0;
            switch (size) {
                case "4\"": extraCheesePrice = 0.30; break;
                case "8\"": extraCheesePrice = 0.60; break;
                case "12\"": extraCheesePrice = 0.90; break;
            }
            price += numExtraCheeses * extraCheesePrice;
        }

        // Regular toppings, sauces, and sides (like au jus) are included and do not add to the cost.
        return price;
    }

    // Overrides the toString method to provide a detailed string representation of the sandwich.
    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        // Append basic sandwich info and toasted status.
        details.append(String.format("%s %s Sandwich (%s)", size, bread, toasted ? "Toasted" : "Not Toasted"));

        // Check if any toppings are present before adding " with:".
        if (!meats.isEmpty() || !cheeses.isEmpty() || !regularToppings.isEmpty() || !sauces.isEmpty() || !sides.isEmpty() || numExtraMeats > 0 || numExtraCheeses > 0) {
            details.append(" with:");
        }
        details.append("\n"); // New line for better formatting.

        // Append details for each category of toppings if they are not empty.
        if (!meats.isEmpty()) {
            details.append("    Meats: " + String.join(", ", meats) + "\n");
        }
        if (numExtraMeats > 0) {
            details.append("    Extra Meats: " + numExtraMeats + " portions\n");
        }
        if (!cheeses.isEmpty()) {
            details.append("    Cheeses: " + String.join(", ", cheeses) + "\n");
        }
        if (numExtraCheeses > 0) {
            details.append("    Extra Cheeses: " + numExtraCheeses + " portions\n");
        }
        if (!regularToppings.isEmpty()) {
            details.append("    Other Toppings: " + String.join(", ", regularToppings) + "\n");
        }
        if (!sauces.isEmpty()) {
            details.append("    Sauces: " + String.join(", ", sauces) + "\n");
        }
        if (!sides.isEmpty()) {
            details.append("    Sides: " + String.join(", ", sides) + "\n");
        }
        // Append the calculated price for the sandwich.
        details.append(String.format("    Price: $%.2f", getPrice()));
        return details.toString();
    }
}