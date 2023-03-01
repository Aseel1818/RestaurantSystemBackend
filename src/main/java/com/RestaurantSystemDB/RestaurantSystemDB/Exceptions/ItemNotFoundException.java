package com.RestaurantSystemDB.RestaurantSystemDB.Exceptions;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(String message){
        super(message);
    }
}
