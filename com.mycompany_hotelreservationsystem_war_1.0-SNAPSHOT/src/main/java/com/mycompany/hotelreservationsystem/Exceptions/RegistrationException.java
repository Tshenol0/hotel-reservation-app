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
public class RegistrationException extends Exception{
    
    public RegistrationException(String message){
        super(message);
    }
    
    public RegistrationException(){
        this("Email or Password incorrect");
    }
    
}
