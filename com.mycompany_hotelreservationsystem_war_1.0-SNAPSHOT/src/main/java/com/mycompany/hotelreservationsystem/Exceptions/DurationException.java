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
public class DurationException extends Exception{
    
    public DurationException(String message){
        super(message);
    }
    
    public DurationException(){
        this("Duration too short");
    }
}
