/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelreservationsystem.Email.EmailModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Train
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    
    private String sender;
    private String reciever;
    private String message;
    private String password;
    private String subject;
    
}
