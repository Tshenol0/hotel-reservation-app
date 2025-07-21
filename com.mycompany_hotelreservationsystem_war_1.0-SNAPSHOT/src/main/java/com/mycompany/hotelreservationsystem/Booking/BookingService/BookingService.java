/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Booking.BookingService;


import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.Exceptions.DurationException;
import com.mycompany.hotelreservationsystem.Exceptions.StartEndDateException;
import com.mycompany.hotelreservationsystem.Exceptions.InvalidDateException;
import com.mycompany.hotelreservationsystem.Exceptions.SameTimeException;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import java.util.List;

/**
 *
 * @author User
 */
public interface BookingService {
    
    boolean addBooking(Booking booking)throws InvalidDateException,StartEndDateException,SameTimeException,DurationException;
    Booking getBooking(Booking booking);
    List<Booking> getUserBookings(User user);
    Booking getDetails(Booking booking);
    boolean cancelBooking(Booking booking);
    boolean confirmBooking(Booking booking);
    List<Booking> getHistory(User user);
    List<Booking> getUpcoming(User user);
    boolean changeTime(Booking booking)throws InvalidDateException,StartEndDateException,SameTimeException,DurationException;
    boolean changeCost(Booking booking);
    void setLateStatus();
    List<Booking> getLateBookings();
    List<Booking> getBookings();
}
