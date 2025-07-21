/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Payment.PaymentStubImp;

import com.mycompany.hotelreservationsystem.Payment.PaymentStubImp.Payment;


/**
 *
 * @author User
 */
public class PaymentImpl implements Payment{
    
    private boolean pay(int cost){
        
        return Math.random()*(2*cost)>=cost;
        
    }

    @Override
    public boolean creditPay(int cost) {
        return pay(cost);
    }

    @Override
    public boolean debitPay(int cost) {
        return pay(cost*2);
    }
    

}
