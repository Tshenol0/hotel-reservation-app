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
public class SameTimeException extends Exception{
    public SameTimeException(String message){
        super(message);
    }
    public SameTimeException(){
        this("check-in time cannot be the same as check-out time");
    }
}
