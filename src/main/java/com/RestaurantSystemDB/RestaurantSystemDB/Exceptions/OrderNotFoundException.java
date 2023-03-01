package com.RestaurantSystemDB.RestaurantSystemDB.Exceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String message){
        super(message);
    }
}
