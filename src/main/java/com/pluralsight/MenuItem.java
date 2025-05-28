package com.pluralsight;
// This interface defines common methods for all items that can be part of an order and have a price.
public interface MenuItem {
    // Returns the price of the menu item.
    double getPrice();
    // Returns the name or description of the menu item.
    String getName();
}