/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Email.EmailService;

import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.User.UserModel.User;

/**
 *
 * @author User
 */
public interface EmailService {
    void sendMail();
    void sendInvoice(Booking booking,User user);
    String verification(User user);
}
