package com.RestaurantSystemDB.RestaurantSystemDB.Exceptions;

public class OrderDetailNotFoundException extends RuntimeException{

    public OrderDetailNotFoundException(String message){
        super(message);
    }
}
