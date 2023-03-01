package com.RestaurantSystemDB.RestaurantSystemDB.Exceptions;

public class TableNotFoundException extends RuntimeException{

    public TableNotFoundException(String message){
        super(message);
    }
}
