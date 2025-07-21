/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Exceptions;

/**
 *
 * @author User
 */
public class InvalidDateException extends Exception{
    public InvalidDateException(String message){
        super(message);
    }
    
    public InvalidDateException(){
        this("Date and Time has passed");
    }
}
