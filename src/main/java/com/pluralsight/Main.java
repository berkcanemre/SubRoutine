package com.pluralsight; // Package declaration

import java.util.Scanner; // Scanner for input

// Entry point of the application
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input
        boolean running = true;                   // Flag to keep app running

        while (running) {
            System.out.println("\nWelcome to DELI-cious POS System"); // App title
            System.out.println("1) New Order");  // Option to start new order
            System.out.println("0) Exit");       // Option to exit app
            System.out.print("Choose an option: "); // Prompt user

            String choice = scanner.nextLine(); // Read user input

            switch (choice) {
                case "1":
                    System.out.println("Starting new order..."); // Placeholder
                    // In next step we'll call an OrderHandler to walk through steps
                    break;
                case "0":
                    running = false; // Stop the loop
                    System.out.println("Goodbye!"); // Exit message
                    break;
                default:
                    System.out.println("Invalid input. Try again."); // Handle bad input
            }
        }

        scanner.close(); // Close scanner
    }
}
