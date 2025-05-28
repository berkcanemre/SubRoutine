package com.pluralsight;
import java.io.File;             // Used for file system operations (creating directory).
import java.io.FileWriter;       // Used for writing text to a file.
import java.io.IOException;      // Handles I/O errors.
import java.time.LocalDateTime;  // Used for getting current date and time.
import java.time.format.DateTimeFormatter; // Used for formatting date and time strings.
import java.util.InputMismatchException; // Handles incorrect input types from scanner.
import java.util.Scanner;        // Used for reading user input from the console.

// The main application class for the DELI-cious Point-of-Sale system.
public class SubRoutine {
    private static Scanner scanner = new Scanner(System.in); // Global scanner for user input.
    private static Order currentOrder = new Order(); // The current order being built by the customer.

    // Main method: entry point of the application.
    public static void main(String[] args) {
        runApplication(); // Starts the main application loop.
    }

    // Manages the overall application flow, displaying the home screen and handling main menu choices.
    public static void runApplication() {
        int choice; // Variable to store user's menu choice.
        do {
            displayHomeScreen(); // Show the main menu options.
            choice = getHomeScreenChoice(); // Get user's input for the home screen.

            switch (choice) {
                case 1:
                    newOrderScreen(); // Proceed to the order creation screen.
                    break;
                case 0:
                    System.out.println("Exiting DELI-cious application. Goodbye!"); // Exit message.
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Handle invalid choices.
            }
        } while (choice != 0); // Loop until the user chooses to exit (0).
        scanner.close(); // Close the scanner to release system resources.
    }

    // Displays the options available on the home screen.
    public static void displayHomeScreen() {
        System.out.println("\n--- DELI-cious Home Screen ---");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        System.out.print("Enter your choice: ");
    }

    // Reads and validates integer input for home screen choices.
    public static int getHomeScreenChoice() {
        try {
            int choice = scanner.nextInt(); // Attempt to read an integer.
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number."); // Error for non-integer input.
            return -1; // Return -1 to indicate an error, prompting re-entry.
        } finally {
            scanner.nextLine(); // Consume the remaining newline character after nextInt().
        }
    }

    // Manages the order creation process, allowing users to add items or checkout.
    public static void newOrderScreen() {
        currentOrder = new Order(); // Initialize a new, empty order for the customer.
        int choice; // Variable to store user's menu choice within the order screen.
        do {
            displayOrderScreen(); // Show current order items and options.
            choice = getOrderScreenChoice(); // Get user's input for the order screen.

            switch (choice) {
                case 1:
                    addSandwichScreen(); // Go to the sandwich customization screen.
                    break;
                case 2:
                    addDrinkScreen(); // Go to the drink selection screen.
                    break;
                case 3:
                    addChipsScreen(); // Go to the chips selection screen.
                    break;
                case 4:
                    checkoutScreen(); // Proceed to checkout.
                    return; // Exit the order screen after checkout is complete.
                case 0:
                    System.out.println("Order cancelled. Returning to home screen.");
                    currentOrder.clearOrder(); // Clear all items from the cancelled order.
                    return; // Go back to the home screen.
                default:
                    System.out.println("Invalid option. Please try again."); // Handle invalid choices.
            }
        } while (choice != 0 && choice != 4); // Loop until user cancels or checks out.
    }

    // Displays the options available on the order screen, including current order summary.
    public static void displayOrderScreen() {
        System.out.println("\n--- Order Screen ---");
        System.out.println("Current Order Items:");
        System.out.println(currentOrder.toString()); // Display the detailed summary of the current order.

        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
        System.out.print("Enter your choice: ");
    }

    // Reads and validates integer input for order screen choices.
    public static int getOrderScreenChoice() {
        try {
            int choice = scanner.nextInt(); // Attempt to read an integer.
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number."); // Error for non-integer input.
            return -1; // Return -1 to indicate an error.
        } finally {
            scanner.nextLine(); // Consume the remaining newline.
        }
    }

    // Guides the user through customizing and adding a new sandwich to the order.
    public static void addSandwichScreen() {
        System.out.println("\n--- Add Sandwich ---");

        // Select bread type.
        String bread;
        while (true) {
            System.out.print("Select your bread (white, wheat, rye, wrap): ");
            bread = scanner.nextLine().trim().toLowerCase();
            if (bread.equals("white") || bread.equals("wheat") || bread.equals("rye") || bread.equals("wrap")) {
                break; // Valid bread, exit loop.
            } else {
                System.out.println("Invalid bread type. Please choose from white, wheat, rye, or wrap.");
            }
        }

        // Select sandwich size.
        String sizeInput;
        String size;
        while (true) {
            System.out.print("Select sandwich size (4, 8, or 12 inches): ");
            sizeInput = scanner.nextLine().trim();
            if (sizeInput.equals("4") || sizeInput.equals("8") || sizeInput.equals("12")) {
                size = sizeInput + "\""; // Format as "4\"", "8\"", "12\"".
                break; // Valid size, exit loop.
            } else {
                System.out.println("Invalid size. Please enter 4, 8, or 12.");
            }
        }

        Sandwich newSandwich = new Sandwich(bread, size); // Create a new Sandwich object.

        // Add meats (loop until user enters 'done').
        System.out.println("Add meats (e.g., steak, ham, salami, roast beef, chicken, bacon). Enter 'done' to finish:");
        String meatChoice;
        do {
            System.out.print("Meat: ");
            meatChoice = scanner.nextLine().trim().toLowerCase();
            if (!meatChoice.equals("done") && !meatChoice.isEmpty()) {
                newSandwich.addMeat(meatChoice); // Add meat to the sandwich.
            }
        } while (!meatChoice.equals("done"));

        // Add cheeses (loop until user enters 'done').
        System.out.println("Add cheeses (e.g., american, provolone, cheddar, swiss). Enter 'done' to finish:");
        String cheeseChoice;
        do {
            System.out.print("Cheese: ");
            cheeseChoice = scanner.nextLine().trim().toLowerCase();
            if (!cheeseChoice.equals("done") && !cheeseChoice.isEmpty()) {
                newSandwich.addCheese(cheeseChoice); // Add cheese to the sandwich.
            }
        } while (!cheeseChoice.equals("done"));

        // Add other toppings (regular) (loop until user enters 'done').
        System.out.println("Add other toppings (e.g., lettuce, peppers, onions, tomatoes, jalapeños, cucumbers, pickles, guacamole, mushrooms). Enter 'done' to finish:");
        String toppingChoice;
        do {
            System.out.print("Topping: ");
            toppingChoice = scanner.nextLine().trim().toLowerCase();
            if (!toppingChoice.equals("done") && !toppingChoice.isEmpty()) {
                newSandwich.addRegularTopping(toppingChoice); // Add regular topping.
            }
        } while (!toppingChoice.equals("done"));

        // Select sauces (loop until user enters 'done').
        System.out.println("Select sauces (e.g., mayo, mustard, ketchup, ranch, thousand islands, vinaigrette). Enter 'done' to finish:");
        String sauceChoice;
        do {
            System.out.print("Sauce: ");
            sauceChoice = scanner.nextLine().trim().toLowerCase();
            if (!sauceChoice.equals("done") && !sauceChoice.isEmpty()) {
                newSandwich.addSauce(sauceChoice); // Add sauce.
            }
        } while (!sauceChoice.equals("done"));

        // Ask if the sandwich should be toasted.
        String toastedInput;
        while (true) {
            System.out.print("Would you like the sandwich toasted? (yes/no): ");
            toastedInput = scanner.nextLine().trim().toLowerCase();
            if (toastedInput.equals("yes")) {
                newSandwich.setToasted(true);
                break;
            } else if (toastedInput.equals("no")) {
                newSandwich.setToasted(false);
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        currentOrder.addSandwich(newSandwich); // Add the fully customized sandwich to the current order.
        System.out.println("Sandwich added to order!");
    }

    // Guides the user through selecting and adding a new drink to the order.
    public static void addDrinkScreen() {
        System.out.println("\n--- Add Drink ---");
        System.out.print("Enter drink flavor (e.g., Coke, Sprite, Water): ");
        String flavor = scanner.nextLine().trim();

        String size;
        while (true) {
            System.out.print("Enter drink size (Small, Medium, Large): ");
            size = scanner.nextLine().trim();
            if (size.equalsIgnoreCase("small") || size.equalsIgnoreCase("medium") || size.equalsIgnoreCase("large")) {
                break; // Valid size, exit loop.
            } else {
                System.out.println("Invalid size. Please enter Small, Medium, or Large.");
            }
        }

        Drink newDrink = new Drink(flavor, size); // Create a new Drink object.
        currentOrder.addDrink(newDrink); // Add the drink to the current order.
        System.out.println("Drink added to order!");
    }

    // Guides the user through selecting and adding new chips to the order.
    public static void addChipsScreen() {
        System.out.println("\n--- Add Chips ---");
        System.out.print("Enter chip type (e.g., Regular, BBQ, Salt & Vinegar): ");
        String type = scanner.nextLine().trim();
        // No size or additional options for chips as per requirements, fixed price.

        Chips newChips = new Chips(type); // Create a new Chips object.
        currentOrder.addChips(newChips); // Add the chips to the current order.
        System.out.println("Chips added to order!");
    }

    // Handles the checkout process, displaying the final order and prompting for confirmation.
    public static void checkoutScreen() {
        System.out.println("\n--- Checkout ---");
        System.out.println(currentOrder.toString()); // Display the complete order details and total cost.

        String confirmation;
        while (true) {
            System.out.print("Confirm order? (yes/no): ");
            confirmation = scanner.nextLine().trim().toLowerCase();
            if (confirmation.equals("yes")) {
                saveReceipt(currentOrder); // Save the order details to a receipt file.
                System.out.println("Order confirmed and receipt saved. Returning to home screen.");
                currentOrder.clearOrder(); // Clear the order after successful checkout.
                break;
            } else if (confirmation.equals("no")) {
                System.out.println("Order cancelled. Returning to home screen.");
                currentOrder.clearOrder(); // Clear the order if cancelled at checkout.
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
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
        // Define the desired date-time format for the file name.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"); // HH for 24-hour format.
        // Construct the full file path.
        String fileName = folderPath + "/" + now.format(formatter) + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) { // Use try-with-resources for automatic closing.
            writer.write(order.toString()); // Write the order summary to the file.
            System.out.println("Receipt saved to: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving receipt: " + e.getMessage()); // Print error if writing fails.
        }
    }
}