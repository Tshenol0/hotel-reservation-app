/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Payment.PaymentStubImp;

/**
 *
 * @author User
 */
public interface Payment {
    boolean creditPay(int cost);
    boolean debitPay(int cost);
}
