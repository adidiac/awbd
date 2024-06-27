package com.cafeteria.cafeteria;
public final class MyConstants {
    // Private constructor to prevent instantiation
    private MyConstants() {
        throw new IllegalStateException("Constants class");
    }

    // Constants for authentication
    public static final String ERROR_MESSAGE_TOKEN_INVALID = "Token is invalid";
    public static final String ERROR_MESSAGE_AUTH_FAILED = "Authentication failed";
    public static final String ERROR_MESSAGE_USER_NOT_FOUND = "User not found";
    public static final String ERROR_MESSAGE_USER_ALREADY_EXISTS = "User already exists";
    public static final String ERROR_MESSAGE_USER_NOT_REGISTERED = "User not registered";

    // Constants for role
    public static final String USER_ROLE_ADMIN = "admin";
    public static final String USER_ROLE_USER = "CUSTOMER";

    // Constants for http status codes
    public static final String NotFound = "Not found: ";
    public static final String InternalServerError = "Internal server error: ";
    public static final String Unauthorized = "Unauthorized access: ";

    public static final String ERROR_MESSAGE_MENU_ITEM_NOT_FOUND = "Menu item not found";
    public static final String ERROR_MESSAGE_MENU_ITEM_ALREADY_EXISTS = "Menu item already exists";
    // add all for this app everthins is needed 
    public static final String ERROR_MESSAGE_ORDER_NOT_FOUND = "Order not found";
    public static final String ERROR_MESSAGE_ORDER_ITEM_NOT_FOUND = "Order item not found";

    public static final String ERROR_MESSAGE_UKNOWN_ERROR = "Unknown error";
}
