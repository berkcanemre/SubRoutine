package com.pluralsight;

import java.io.File;             // Used for file system operations (creating directory).
import java.io.FileWriter;       // Used for writing text to a file.
import java.io.IOException;      // Handles I/O errors.
import java.time.LocalDateTime;  // Used for getting current date and time.
import java.time.format.DateTimeFormatter; // Used for formatting date and time strings.
import java.util.InputMismatchException; // Handles incorrect input types from scanner.
import java.util.Scanner;        // Used for reading user input from the console.
import java.util.Arrays;         // Used for array operations, specifically for valid choices.
import java.util.List;           // Used for lists of valid choices.

public class SubRoutine {
    private static Scanner scanner = new Scanner(System.in); // Global scanner for user input.
    private static Order currentOrder = new Order(); // The current order being built by the customer.

    public static void main(String[] args) {
        runApplication(); // Starts the main application loop.
    }

    // Manages the overall application flow, displaying the home screen and handling main menu choices.
    public static void runApplication() {
        int choice; // Variable to store user's menu choice.
        do {
            displayHomeScreen(); // Show the main menu options.
            choice = getValidatedMenuChoice(0, 1, "Please select: ");

            switch (choice) {
                case 1:
                    newOrderScreen(); // Proceed to the order creation screen.
                    break;
                case 0:
                    System.out.println("Thank you for visiting us today. Goodbye!"); // Exit message.
                    break;
                default:
                    System.out.println("Please select another option");
                    break;
            }
        } while (choice != 0);
        scanner.close();
    }

    // Displays the options available on the home screen.
    public static void displayHomeScreen() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~ Welcome to the Home of Subroutine ~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//Prompts the user for a numerical choice and validates it within a given range.
    public static int getValidatedMenuChoice(int min, int max, String prompt) {
        int choice = -1;
        while (true) {
            System.out.print(prompt);
            try {
                choice = scanner.nextInt();
                if (choice >= min && choice <= max) {
                    break;
                } else {
                    System.out.println("Invalid option. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            } finally {
                scanner.nextLine();
            }
        }
        return choice;
    }

    // Manages the order creation process, allowing users to add items or checkout.
    public static void newOrderScreen() {
        currentOrder = new Order();
        int choice; // Variable to store user's menu choice within the order screen.
        do {
            displayOrderScreen(); // Show current order items and options.
            choice = getValidatedMenuChoice(0, 5, "Please select: ");

            switch (choice) {
                case 1:
                    addSandwichScreen();
                    break;
                case 2:
                    addDrinkScreen();
                    break;
                case 3:
                    addChipsScreen();
                    break;
                case 4:
                    removeItemFromOrderScreen();
                    break;
                case 5:
                    checkoutScreen();
                    return;
                case 0:
                    System.out.println("Order cancelled. Returning to home screen.");
                    currentOrder.clearOrder(); // Clear all items from the cancelled order.
                    return;
                default:
                    System.out.println("An unexpected error occurred with menu choice.");
                    break;
            }
        } while (choice != 0 && choice != 5); // Loop until user cancels or checks out.
    }

    // Displays the options available on the order screen, including current order summary.
    public static void displayOrderScreen() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Order Menu ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Current Order Items:");
        System.out.println(currentOrder.toString()); // Display the detailed summary of the current order.

        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Remove Item");
        System.out.println("5) Checkout");
        System.out.println("0) Cancel Order");
    }

    // Guides the user through customizing and adding a new sandwich to the order.
    public static void addSandwichScreen() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~ Order Sandwich ~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // Prompt user to choose between custom and signature sandwich.
        int sandwichTypeChoice = getValidatedMenuChoice(1, 2, "1) Custom Sandwich\n2) Signature Sandwich\nPlease select: ");
        Sandwich newSandwich;

        // Signature Sandwich
        if (sandwichTypeChoice == 2) {
            System.out.println("~~~~~~~~~~~~~~~~~~ Select Signature Sandwich ~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("1) BLT (8\" White, Bacon, Cheddar, Lettuce, Tomato, Ranch, Toasted)");
            System.out.println("2) Philly Cheese Steak (8\" White, Steak, American, Peppers, Mayo, Toasted)");
            int signatureChoice = getValidatedMenuChoice(1, 2, "Please select: ");

            if (signatureChoice == 1) {
                newSandwich = SignatureSandwich.createBLT();
                System.out.println("BLT selected!");
            } else { // signatureChoice == 2
                newSandwich = SignatureSandwich.createPhillyCheeseSteak();
                System.out.println("Philly Cheese Steak selected!");
            }

            // Offer customization for signature sandwiches.
            int customizeChoice = getValidatedMenuChoice(1, 2, "Customize this sandwich (add/remove toppings)?\n1) Yes\n2) No\nPlease select: ");
            if (customizeChoice == 1) {
                customizeSandwich(newSandwich); // Allow adding/removing toppings/sauces/sides.
            }

        } else { // Custom Sandwich (sandwichTypeChoice == 1)
            List<String> breadOptions = Arrays.asList("white", "wheat", "rye", "wrap");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~ Select your bread ~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (int i = 0; i < breadOptions.size(); i++) {
                System.out.println((i + 1) + ") " + breadOptions.get(i));
            }
            int breadChoice = getValidatedMenuChoice(1, breadOptions.size(), "Please select: ");
            String bread = breadOptions.get(breadChoice - 1);

            List<String> sizeOptions = Arrays.asList("4\"", "8\"", "12\"");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~ Select sandwich size ~~~~~~~~~~~~~~~~~~~~~~~~");
            for (int i = 0; i < sizeOptions.size(); i++) {
                System.out.println((i + 1) + ") " + sizeOptions.get(i));
            }
            int sizeChoice = getValidatedMenuChoice(1, sizeOptions.size(), "Please select: ");
            String size = sizeOptions.get(sizeChoice - 1);

            newSandwich = new Sandwich(bread, size); // Create a new Custom Sandwich object.
            customizeSandwich(newSandwich); // Proceed to customize the custom sandwich.
        }

        currentOrder.addSandwich(newSandwich); // Add the fully customized sandwich to the current order.
        System.out.println("Sandwich added to order!");
    }

//Allows the user to add or remove toppings, sauces, sides, and set toasted status for a given sandwich.
    private static void customizeSandwich(Sandwich sandwich) {
        int customizationChoice;
        do {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~ Customize Sandwich ~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("1) Add Meats");
            System.out.println("2) Add Cheeses");
            System.out.println("3) Add Other Toppings");
            System.out.println("4) Add Sauces");
            System.out.println("5) Add Sides (e.g., Au Jus)");
            System.out.println("6) Set Toasted Status");
            System.out.println("7) Remove Topping/Sauce/Side");
            System.out.println("0) Finish Customization");
            customizationChoice = getValidatedMenuChoice(0, 7, "Please select: ");

            switch (customizationChoice) {
                case 1: addMeatsToSandwich(sandwich); break;
                case 2: addCheesesToSandwich(sandwich); break;
                case 3: addRegularToppingsToSandwich(sandwich); break;
                case 4: addSaucesToSandwich(sandwich); break;
                case 5: addSidesToSandwich(sandwich); break;
                case 6: setToastedStatus(sandwich); break;
                case 7: removeToppingFromSandwich(sandwich); break; // Call removal method.
                case 0: System.out.println("Finishing sandwich customization."); break;
                default: System.out.println("Invalid choice."); break;
            }
        } while (customizationChoice != 0);
    }

    // Helper method to add meats to a sandwich.
    private static void addMeatsToSandwich(Sandwich sandwich) {
        List<String> meatOptions = Arrays.asList("steak", "ham", "salami", "roast beef", "chicken", "bacon");
        while (true) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~ Select sandwich size ~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~ Add meats (Select 0 to finish) ~~~~~~~~~~~~~~~~~~~~~~");
            for (int i = 0; i < meatOptions.size(); i++) {
                System.out.println((i + 1) + ") " + meatOptions.get(i));
            }
            int meatChoice = getValidatedMenuChoice(0, meatOptions.size(), "Please select: ");
            if (meatChoice == 0) break;
            sandwich.addMeat(meatOptions.get(meatChoice - 1));
            System.out.println(meatOptions.get(meatChoice - 1) + " added.");
        }
        int extraMeatsCount = getValidatedMenuChoice(0, 10, "How many EXTRA meat portions would you like? (Please select 0 for none): ");
        sandwich.setNumExtraMeats(sandwich.getNumExtraMeats() + extraMeatsCount); // Add to existing extra meats.
    }

    // Helper method to add cheeses to a sandwich.
    private static void addCheesesToSandwich(Sandwich sandwich) {
        List<String> cheeseOptions = Arrays.asList("american", "provolone", "cheddar", "swiss");
        while (true) {
            System.out.println("~~~~~~~~~~~~~ Add cheeses (Select 0 to finish) ~~~~~~~~~~~~~~~~~~~~~");
            for (int i = 0; i < cheeseOptions.size(); i++) {
                System.out.println((i + 1) + ") " + cheeseOptions.get(i));
            }
            int cheeseChoice = getValidatedMenuChoice(0, cheeseOptions.size(), "Please select: ");
            if (cheeseChoice == 0) break;
            sandwich.addCheese(cheeseOptions.get(cheeseChoice - 1));
            System.out.println(cheeseOptions.get(cheeseChoice - 1) + " added.");
        }
        int extraCheesesCount = getValidatedMenuChoice(0, 10, "How many EXTRA cheese portions would you like? (0 for none): ");
        sandwich.setNumExtraCheeses(sandwich.getNumExtraCheeses() + extraCheesesCount); // Add to existing extra cheeses.
    }

    // Helper method to add regular toppings to a sandwich.
    private static void addRegularToppingsToSandwich(Sandwich sandwich) {
        List<String> regularToppingOptions = Arrays.asList("lettuce", "peppers", "onions", "tomatoes", "jalapeños", "cucumbers", "pickles", "guacamole", "mushrooms");
        while (true) {
            System.out.println("~~~~~~~~~~ Add other toppings (Select 0 to finish) ~~~~~~~~~~~~~~~~~");
            for (int i = 0; i < regularToppingOptions.size(); i++) {
                System.out.println((i + 1) + ") " + regularToppingOptions.get(i));
            }
            int toppingChoice = getValidatedMenuChoice(0, regularToppingOptions.size(), "Please select: ");
            if (toppingChoice == 0) break;
            sandwich.addRegularTopping(regularToppingOptions.get(toppingChoice - 1));
            System.out.println(regularToppingOptions.get(toppingChoice - 1) + " added.");
        }
    }

    // Helper method to add sauces to a sandwich.
    private static void addSaucesToSandwich(Sandwich sandwich) {
        List<String> sauceOptions = Arrays.asList("mayo", "mustard", "ketchup", "ranch", "thousand islands", "vinaigrette");
        while (true) {
            System.out.println("~~~~~~~~~~~~ Select sauces (Select 0 to finish) ~~~~~~~~~~~~~~~~~~~~");
            for (int i = 0; i < sauceOptions.size(); i++) {
                System.out.println((i + 1) + ") " + sauceOptions.get(i));
            }
            int sauceChoice = getValidatedMenuChoice(0, sauceOptions.size(), "Please select: ");
            if (sauceChoice == 0) break;
            sandwich.addSauce(sauceOptions.get(sauceChoice - 1));
            System.out.println(sauceOptions.get(sauceChoice - 1) + " added.");
        }
    }

    // Helper method to add sides to a sandwich.
    private static void addSidesToSandwich(Sandwich sandwich) {
        List<String> sideOptions = Arrays.asList("au jus sauce"); // Only one side mentioned in doc.
        while (true) {
            System.out.println("~~~~~~~~~~~~ Select sides (Select 0 to finish) ~~~~~~~~~~~~~~~~~~~~~");
            for (int i = 0; i < sideOptions.size(); i++) {
                System.out.println((i + 1) + ") " + sideOptions.get(i));
            }
            int sideChoice = getValidatedMenuChoice(0, sideOptions.size(), "Please select: ");
            if (sideChoice == 0) break;
            sandwich.addSide(sideOptions.get(sideChoice - 1));
            System.out.println(sideOptions.get(sideChoice - 1) + " added.");
        }
    }

    // Helper method to set toasted status for a sandwich.
    private static void setToastedStatus(Sandwich sandwich) {
        int toastedChoice = getValidatedMenuChoice(1, 2, "Would you like the sandwich toasted?\n1) Yes\n2) No\nPlease select: ");
        sandwich.setToasted(toastedChoice == 1);
        System.out.println("We got your request!");
    }

//Allows the user to remove a topping, sauce, or side from a specific sandwich.
    private static void removeToppingFromSandwich(Sandwich sandwich) {
        System.out.println("~~~~~~~~~~Remove Topping/Sauce/Side from Sandwich ~~~~~~~~~~~~~~~~~~");
        System.out.println("Current toppings on this sandwich:");
        System.out.println("  Meats: " + (sandwich.getMeats().isEmpty() ? "None" : String.join(", ", sandwich.getMeats())));
        System.out.println("  Cheeses: " + (sandwich.getCheeses().isEmpty() ? "None" : String.join(", ", sandwich.getCheeses())));
        System.out.println("  Other Toppings: " + (sandwich.getRegularToppings().isEmpty() ? "None" : String.join(", ", sandwich.getRegularToppings())));
        System.out.println("  Sauces: " + (sandwich.getSauces().isEmpty() ? "None" : String.join(", ", sandwich.getSauces())));
        System.out.println("  Sides: " + (sandwich.getSides().isEmpty() ? "None" : String.join(", ", sandwich.getSides())));
        System.out.println("  Extra Meats: " + sandwich.getNumExtraMeats());
        System.out.println("  Extra Cheeses: " + sandwich.getNumExtraCheeses());

        int categoryChoice = getValidatedMenuChoice(0, 6,
                "Select category to remove from:\n" +
                        "1) Meats\n" +
                        "2) Cheeses\n" +
                        "3) Other Toppings\n" +
                        "4) Sauces\n" +
                        "5) Sides\n" +
                        "6) Extra Meats/Cheeses (reduce count)\n" +
                        "0) Back to Sandwich Customization\n" +
                        "Enter choice: ");

        if (categoryChoice == 0) return;

        String itemToRemove = "";
        int indexToRemove = -1; // For list-based removals.

        switch (categoryChoice) {
            case 1: // Meats
                if (sandwich.getMeats().isEmpty()) { System.out.println("No meats to remove."); break; }
                System.out.println("Meats: ");
                for (int i = 0; i < sandwich.getMeats().size(); i++) {
                    System.out.println((i + 1) + ") " + sandwich.getMeats().get(i));
                }
                indexToRemove = getValidatedMenuChoice(1, sandwich.getMeats().size(), "Enter number of meat to remove: ") - 1;
                itemToRemove = sandwich.getMeats().get(indexToRemove);
                if (sandwich.removeMeat(itemToRemove)) {
                    System.out.println(itemToRemove + " removed.");
                } else {
                    System.out.println("Failed to remove " + itemToRemove + ".");
                }
                break;
            case 2: // Cheeses
                if (sandwich.getCheeses().isEmpty()) { System.out.println("No cheeses to remove."); break; }
                System.out.println("Cheeses: ");
                for (int i = 0; i < sandwich.getCheeses().size(); i++) {
                    System.out.println((i + 1) + ") " + sandwich.getCheeses().get(i));
                }
                indexToRemove = getValidatedMenuChoice(1, sandwich.getCheeses().size(), "Enter number of cheese to remove: ") - 1;
                itemToRemove = sandwich.getCheeses().get(indexToRemove);
                if (sandwich.removeCheese(itemToRemove)) {
                    System.out.println(itemToRemove + " removed.");
                } else {
                    System.out.println("Failed to remove " + itemToRemove + ".");
                }
                break;
            case 3: // Other Toppings
                if (sandwich.getRegularToppings().isEmpty()) { System.out.println("No other toppings to remove."); break; }
                System.out.println("Other Toppings: ");
                for (int i = 0; i < sandwich.getRegularToppings().size(); i++) {
                    System.out.println((i + 1) + ") " + sandwich.getRegularToppings().get(i));
                }
                indexToRemove = getValidatedMenuChoice(1, sandwich.getRegularToppings().size(), "Enter number of topping to remove: ") - 1;
                itemToRemove = sandwich.getRegularToppings().get(indexToRemove);
                if (sandwich.removeRegularTopping(itemToRemove)) {
                    System.out.println(itemToRemove + " removed.");
                } else {
                    System.out.println("Failed to remove " + itemToRemove + ".");
                }
                break;
            case 4: // Sauces
                if (sandwich.getSauces().isEmpty()) { System.out.println("No sauces to remove."); break; }
                System.out.println("Sauces: ");
                for (int i = 0; i < sandwich.getSauces().size(); i++) {
                    System.out.println((i + 1) + ") " + sandwich.getSauces().get(i));
                }
                indexToRemove = getValidatedMenuChoice(1, sandwich.getSauces().size(), "Enter number of sauce to remove: ") - 1;
                itemToRemove = sandwich.getSauces().get(indexToRemove);
                if (sandwich.removeSauce(itemToRemove)) {
                    System.out.println(itemToRemove + " removed.");
                } else {
                    System.out.println("Failed to remove " + itemToRemove + ".");
                }
                break;
            case 5: // Sides
                if (sandwich.getSides().isEmpty()) { System.out.println("No sides to remove."); break; }
                System.out.println("Sides: ");
                for (int i = 0; i < sandwich.getSides().size(); i++) {
                    System.out.println((i + 1) + ") " + sandwich.getSides().get(i));
                }
                indexToRemove = getValidatedMenuChoice(1, sandwich.getSides().size(), "Enter number of side to remove: ") - 1;
                itemToRemove = sandwich.getSides().get(indexToRemove);
                if (sandwich.removeSide(itemToRemove)) {
                    System.out.println(itemToRemove + " removed.");
                } else {
                    System.out.println("Failed to remove " + itemToRemove + ".");
                }
                break;
            case 6: // Extra Meats/Cheeses
                System.out.println("1) Reduce Extra Meats (Current: " + sandwich.getNumExtraMeats() + ")");
                System.out.println("2) Reduce Extra Cheeses (Current: " + sandwich.getNumExtraCheeses() + ")");
                int extraChoice = getValidatedMenuChoice(1, 2, "Enter choice: ");
                int reduceBy = getValidatedMenuChoice(1, 10, "Reduce by how many portions? ");

                if (extraChoice == 1) { // Reduce Extra Meats
                    if (sandwich.getNumExtraMeats() >= reduceBy) {
                        sandwich.setNumExtraMeats(sandwich.getNumExtraMeats() - reduceBy);
                        System.out.println(reduceBy + " extra meat portions removed.");
                    } else {
                        System.out.println("Cannot remove that many extra meat portions. Current: " + sandwich.getNumExtraMeats());
                    }
                } else { // Reduce Extra Cheeses
                    if (sandwich.getNumExtraCheeses() >= reduceBy) {
                        sandwich.setNumExtraCheeses(sandwich.getNumExtraCheeses() - reduceBy);
                        System.out.println(reduceBy + " extra cheese portions removed.");
                    } else {
                        System.out.println("Cannot remove that many extra cheese portions. Current: " + sandwich.getNumExtraCheeses());
                    }
                }
                break;
        }
    }

    // Guides the user through selecting and adding a new drink to the order.
    public static void addDrinkScreen() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Add Drink ~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        List<String> drinkFlavors = Arrays.asList("Coke", "Sprite", "Water", "Orange Juice", "Lemonade", "Soda");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~ Select drink flavor ~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (int i = 0; i < drinkFlavors.size(); i++) {
            System.out.println((i + 1) + ") " + drinkFlavors.get(i));
        }
        int flavorChoice = getValidatedMenuChoice(1, drinkFlavors.size(), "Please select: ");
        String flavor = drinkFlavors.get(flavorChoice - 1);

        List<String> drinkSizes = Arrays.asList("Small", "Medium", "Large");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~ Select drink size ~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (int i = 0; i < drinkSizes.size(); i++) {
            System.out.println((i + 1) + ") " + drinkSizes.get(i));
        }
        int sizeChoice = getValidatedMenuChoice(1, drinkSizes.size(), "Please select: ");
        String size = drinkSizes.get(sizeChoice - 1);

        Drink newDrink = new Drink(flavor, size); // Create a new Drink object.
        currentOrder.addDrink(newDrink); // Add the drink to the current order.
        System.out.println("Drink added to order!");
    }

    // Guides the user through selecting and adding new chips to the order.
    public static void addChipsScreen() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Add Chips ~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        List<String> chipTypes = Arrays.asList("Regular", "BBQ", "Salt & Vinegar", "Cheese Puffs", "Ruffles");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~ Select chip type ~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (int i = 0; i < chipTypes.size(); i++) {
            System.out.println((i + 1) + ") " + chipTypes.get(i));
        }
        int typeChoice = getValidatedMenuChoice(1, chipTypes.size(), "Please select: ");
        String type = chipTypes.get(typeChoice - 1);

        Chips newChips = new Chips(type); // Create a new Chips object.
        currentOrder.addChips(newChips); // Add the chips to the current order.
        System.out.println("Chips added to order!");
    }

//Allows the user to remove a previously added item (Sandwich, Drink, or Chips) from the current order.
    public static void removeItemFromOrderScreen() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~ Remove Item from Order ~~~~~~~~~~~~~~~~~~~~~~~");

        // Display current order items with numbers for selection.
        System.out.println(currentOrder.toString());

        // Check if the order is empty.
        if (currentOrder.getSandwiches().isEmpty() && currentOrder.getDrinks().isEmpty() && currentOrder.getChips().isEmpty()) {
            System.out.println("Your order is empty. Nothing to remove.");
            return;
        }

        int itemTypeChoice = getValidatedMenuChoice(0, 3,
                "What type of item would you like to remove?\n" +
                        "1) Sandwich\n" +
                        "2) Drink\n" +
                        "3) Chips\n" +
                        "0) Back to Order Screen\n" +
                        "Enter choice: ");

        if (itemTypeChoice == 0) return; // User chose to go back.

        int itemIndex;
        switch (itemTypeChoice) {
            case 1: // Remove Sandwich
                if (currentOrder.getSandwiches().isEmpty()) {
                    System.out.println("No sandwiches in the order to remove.");
                    break;
                }
                System.out.println("~~~~~~~~~~~~~~~~~~ Select Sandwich to Remove ~~~~~~~~~~~~~~~~~~~~~~");
                for (int i = 0; i < currentOrder.getSandwiches().size(); i++) {
                    System.out.println((i + 1) + ") " + currentOrder.getSandwiches().get(i).getName());
                }
                itemIndex = getValidatedMenuChoice(1, currentOrder.getSandwiches().size(), "Enter number of sandwich to remove: ") - 1;
                Sandwich removedSandwich = currentOrder.removeSandwich(itemIndex);
                if (removedSandwich != null) {
                    System.out.println(removedSandwich.getName() + " removed from order.");
                } else {
                    System.out.println("Failed to remove sandwich. Invalid selection.");
                }
                break;
            case 2: // Remove Drink
                if (currentOrder.getDrinks().isEmpty()) {
                    System.out.println("No drinks in the order to remove.");
                    break;
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~ Select Drink to Remove ~~~~~~~~~~~~~~~~~~~~~~~");
                for (int i = 0; i < currentOrder.getDrinks().size(); i++) {
                    System.out.println((i + 1) + ") " + currentOrder.getDrinks().get(i).getName());
                }
                itemIndex = getValidatedMenuChoice(1, currentOrder.getDrinks().size(), "Enter number of drink to remove: ") - 1;
                Drink removedDrink = currentOrder.removeDrink(itemIndex);
                if (removedDrink != null) {
                    System.out.println(removedDrink.getName() + " removed from order.");
                } else {
                    System.out.println("Failed to remove drink. Invalid selection.");
                }
                break;
            case 3: // Remove Chips
                if (currentOrder.getChips().isEmpty()) {
                    System.out.println("No chips in the order to remove.");
                    break;
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~ Select Chips to Remove ~~~~~~~~~~~~~~~~~~~~~~~~");
                for (int i = 0; i < currentOrder.getChips().size(); i++) {
                    System.out.println((i + 1) + ") " + currentOrder.getChips().get(i).getName());
                }
                itemIndex = getValidatedMenuChoice(1, currentOrder.getChips().size(), "Enter number of chips to remove: ") - 1;
                Chips removedChips = currentOrder.removeChips(itemIndex);
                if (removedChips != null) {
                    System.out.println(removedChips.getName() + " removed from order.");
                } else {
                    System.out.println("Failed to remove chips. Invalid selection.");
                }
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }


    // Handles the checkout process, displaying the final order and prompting for confirmation.
    public static void checkoutScreen() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~ Checkout ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // Check if the order is empty before proceeding to checkout.
        if (currentOrder.getSandwiches().isEmpty() && currentOrder.getDrinks().isEmpty() && currentOrder.getChips().isEmpty()) {
            System.out.println("Your order is empty. Please add items before checking out.");
            return; // Return to the order screen.
        }

        System.out.println(currentOrder.toString()); // Display the complete order details and total cost.

        int confirmationChoice = getValidatedMenuChoice(1, 2, "Confirm order?\n1) Yes\n2) No\nPlease select: ");

        if (confirmationChoice == 1) { // User confirmed the order.
            saveReceipt(currentOrder); // Save the order details to a receipt file.
            System.out.println("Order confirmed and receipt saved. Returning to home screen.");
            currentOrder.clearOrder(); // Clear the order after successful checkout.
        } else { // User cancelled the order at checkout.
            System.out.println("Order cancelled. Returning to home screen.");
            currentOrder.clearOrder(); // Clear the order if cancelled at checkout.
        }
    }

    // Saves the confirmed order details to a text file in a 'receipts' folder.
    private static void saveReceipt(Order order) {
        String folderPath = "receipts"; // Define the folder name for receipts.
        File receiptsFolder = new File(folderPath); // Create a File object for the folder.

        // Check if the receipts folder exists, and create it if it doesn't.
        if (!receiptsFolder.exists()) {
            if (receiptsFolder.mkdir()) { // Attempt to create the directory.
                System.out.println("Created receipts folder: " + folderPath);
            } else {
                System.err.println("Failed to create receipts folder: " + folderPath);
                return; // Exit if folder creation fails.
            }
        }

        // Get current date and time to name the receipt file.
        LocalDateTime now = LocalDateTime.now();
        // Define the desired date-time format for the file name (HH for 24-hour format).
        DateTimeFormatter filenameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        // Define the format for displaying date and time within the receipt content.
        DateTimeFormatter contentFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Construct the full file path.
        String fileName = folderPath + "/" + now.format(filenameFormatter) + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) { // Use try-with-resources for automatic closing.
            // Add date and time to the top of the receipt content.
            writer.write("~~~~~~~~~~~~~~~~~~ SubRoutine Receipt ~~~~~~~~~~~~~~~~~~~~~~~~\n");
            writer.write("Date and Time: " + now.format(contentFormatter) + "\n");
            writer.write(order.toString()); // Write the order summary to the file.
            System.out.println("Receipt saved to: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving receipt: " + e.getMessage()); // Print error if writing fails.
        }
    }
}