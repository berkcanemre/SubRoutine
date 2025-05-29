<p align="center"> <pre> Understanding the DELI-cious Project (SubRoutine) </pre>

This document will help you understand the core components, functionalities, and underlying design principles of the "DELI-cious" Command-Line Interface (CLI) Point-of-Sale (POS) application.

1. <p align="center"> <pre> Project Overview and Purpose </pre>
    
    
The "DELI-cious" application is a simulated point-of-sale system for a custom sandwich shop. Its primary purpose is to automate the order-taking process, moving away from manual paper orders. It allows customers to:

    Build highly customized sandwiches.

    Select from predefined "Signature Sandwiches" with options for further customization.

    Add drinks and chips to their order.

    Review and finalize their order.

    Generate a detailed receipt for each completed order.

    The project emphasizes Object-Oriented Programming (OOP) principles in its design and implementation, which is a key aspect to highlight.

2. <pre> Core Functionality Walkthrough </pre>
   Imagine you're demonstrating the application. Here's the flow:
   
       a. Home Screen (SubRoutine.displayHomeScreen())
       
       This is the entry point.
        
       Options: "New Order" (1) and "Exit" (0).
        
      The application loops here until "Exit" is chosen, ensuring continuous operation.

        b. New Order Screen (SubRoutine.newOrderScreen())
                When "New Order" is selected, a fresh Order object is created.
                
        Options: "Add Sandwich", "Add Drink", "Add Chips", "Remove Item", "Checkout", "Cancel Order".
        
This screen continuously displays the current order summary, allowing the user to see what they've added so far.
        
"Cancel Order" clears the current order and returns to the Home Screen.

        c. Adding Items (Sandwich, Drink, Chips)
        i. Add Sandwich (SubRoutine.addSandwichScreen())
        Choice: User first decides between a "Custom Sandwich" or a "Signature Sandwich".
        
Custom Sandwich:
        
        User selects bread type (white, wheat, rye, wrap) and size (4", 8", 12") from numbered lists.
        
        Then, the customizeSandwich() method is called.


Signature Sandwich:
    
        User chooses from predefined options like "BLT" or "Philly Cheese Steak".
    
        These are created using SignatureSandwich.createBLT() or SignatureSandwich.createPhillyCheeseSteak().
    
        Crucially, after selecting a signature sandwich, the user is given the option to customize it further by calling customizeSandwich(). This allows adding or removing toppings to a pre-built sandwich.
    
        customizeSandwich() Method: This is a central method for modifying any sandwich.
    
        It presents a menu of options: "Add Meats", "Add Cheeses", "Add Other Toppings", "Add Sauces", "Add Sides", "Set Toasted Status", and "Remove Topping/Sauce/Side".
    
Adding Toppings: For each category (meats, cheeses, etc.), the user selects items from a numbered list.
    
        Extra Meats/Cheeses: Specific prompts allow the user to add "extra" portions, which are tracked separately for pricing.
    
Toasted Status: A simple yes/no choice.
    
Removing Toppings (SubRoutine.removeToppingFromSandwich()): 

        This new feature allows the user to select a category (e.g., "Meats") and then choose a specific item (by its number) from that category to remove it from the sandwich. 
It also allows reducing the count of "extra" meat/cheese portions.

        ii. Add Drink (SubRoutine.addDrinkScreen())
    User selects flavor and size from numbered lists.
    
A Drink object is created and added to the currentOrder.
    
        iii. Add Chips (SubRoutine.addChipsScreen())
    User selects chip type from a numbered list.
    
A Chips object is created and added to the currentOrder.
    
        d. Removing Items from Order (SubRoutine.removeItemFromOrderScreen())
    This new feature allows the user to remove a whole item (sandwich, drink, or chips) from the currentOrder.
    
The user first selects the type of item to remove (Sandwich, Drink, Chips).
    
Then, a numbered list of existing items of that type is displayed, and the user selects the number of the item to remove.
    
The Order class's removeSandwich(), removeDrink(), or removeChips() methods are called.
    
        e. Checkout (SubRoutine.checkoutScreen())
    Displays the complete currentOrder details, including all items and the Total Cost.
    
Prompts for confirmation ("Yes" to finalize, "No" to cancel).
    
If confirmed, saveReceipt() is called.
    
If canceled, the currentOrder is cleared.
    
        f. Receipt Generation (SubRoutine.saveReceipt())
    Creates a receipts folder if it doesn't exist.
    
Generates a unique filename based on the current date and time (yyyyMMdd-HHmmss.txt).
    
Writes the entire currentOrder.toString() (which includes all item details and total cost) into this file.

3. <pre> Object-Oriented Design (OOP) Explanation </pre>
   This project is a great example of applying core OOP principles.

Classes and Their Responsibilities:

        MenuItem (Interface):

Purpose: Defines a contract for any item that can be sold and has a price. It enforces that all menu items must have a getPrice() and getName() method.

OOP Principle: Abstraction (hides implementation details of how price is calculated) and Polymorphism (allows Order to treat Sandwich, Drink, Chips uniformly as MenuItems).

        Sandwich (Class):

Purpose: Represents a single, highly customizable sandwich. It holds all details like bread, size, toasted status, and lists of various toppings.

Key Features:

Manages lists of meats, cheeses, regularToppings, sauces, sides.

Includes numExtraMeats and numExtraCheeses to track additional portions.

getPrice() method encapsulates complex pricing logic based on size, toppings, and extra portions.

add...() and remove...() methods provide controlled ways to modify its state.

OOP Principle: Encapsulation (data is private, accessed via public methods), Cohesion (all sandwich-related logic is within this class).

        SignatureSandwich (Class):

Purpose: Extends the Sandwich class to represent predefined sandwich templates (like BLT, Philly Cheese Steak).

Key Features:

Inherits all properties and methods from Sandwich.

Provides static "factory methods" (createBLT(), createPhillyCheeseSteak()) to easily create pre-configured sandwiches.

OOP Principle: Inheritance (reuses Sandwich's functionality), Factory Pattern (simplifies object creation).

        Drink (Class):

Purpose: Represents a beverage with a flavor and size.

Key Features: Implements MenuItem and calculates price based on size.

OOP Principle: Encapsulation, Polymorphism (as a MenuItem).

        Chips (Class):

Purpose: Represents a bag of chips.

Key Features: Implements MenuItem and has a fixed price.

OOP Principle: Encapsulation, Polymorphism (as a MenuItem).

Order (Class):

Purpose: Represents a customer's entire order, containing collections of Sandwich, Drink, and Chips objects.

Key Features:

Manages lists of sandwiches, drinks, chips.

add...() methods to add items.

remove...() methods (new): Allows removing specific items by index.

calculateTotalPrice() sums the prices of all items in the order.

clearOrder() resets the order.

OOP Principle: Aggregation/Composition (Order "has-a" list of MenuItems), Encapsulation (order state managed internally).

SubRoutine (Main Application Class):

Purpose: Contains the main application logic, user interface (CLI), input handling, and file operations. It orchestrates the interaction between other classes.

Key Features:

main() method starts the application.

runApplication() manages the main loop.

display...Screen() methods handle UI output.

getValidatedMenuChoice() is a crucial helper for robust input.

saveReceipt() handles file I/O.

OOP Principle: Orchestration/Control (manages the overall flow), Separation of Concerns (UI and core logic are somewhat separated, though still in one class for a CLI app).

4. <pre> Key Implementation Details to Highlight </pre>
   

           Robust Input Handling (SubRoutine.getValidatedMenuChoice()):

Why it's important: Prevents crashes from invalid user input (e.g., typing "abc" instead of "1").

How it works: Uses a while loop to continuously prompt until a valid integer within the specified min and max range is entered. It uses try-catch for InputMismatchException. This makes the application much more user-friendly and stable.

        Flexible Pricing Logic (Sandwich.getPrice()):

This method dynamically calculates the sandwich price based on its size, the individual premium toppings, and the explicitly added "extra" meat and cheese portions.

It demonstrates how complex business rules can be encapsulated within a single method.

Modular Design with Helper Methods:

Notice how customizeSandwich() calls smaller, focused helper methods like addMeatsToSandwich(), removeToppingFromSandwich(), etc.

Benefit: Makes the code easier to read, understand, test, and maintain. Each method has a single, clear responsibility.

        File I/O for Receipts (SubRoutine.saveReceipt()):

Demonstrates how to interact with the file system: creating directories (File.mkdir()) and writing text to files (FileWriter).

Uses LocalDateTime and DateTimeFormatter for unique, time-stamped receipt filenames.

Uses a try-with-resources statement for FileWriter, ensuring the file is automatically closed even if errors occur.

        Removal Logic:

Order Level: removeItemFromOrderScreen() guides the user to select the type of item (sandwich, drink, chips) and then the specific item by its number from a displayed list. The Order class then handles the actual removal from its internal lists.

Sandwich Topping Level: removeToppingFromSandwich() allows granular control. It lists current toppings by category, lets the user pick a category, and then select a specific topping to remove. It also handles reducing "extra" meat/cheese counts. This is a more advanced customization feature.