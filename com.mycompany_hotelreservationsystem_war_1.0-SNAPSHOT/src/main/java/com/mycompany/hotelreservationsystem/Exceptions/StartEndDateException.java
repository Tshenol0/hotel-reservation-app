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
public class StartEndDateException extends Exception{
    
    public StartEndDateException(String message){
        super(message);
    }
    
    public StartEndDateException(){
        this("check-out cannot come before check-in time");
    }
}
