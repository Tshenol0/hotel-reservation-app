/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Booking.BookingDao;

import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author User
 */
public interface BookingDao {
    boolean addBooking(Booking booking);
    Booking getBooking(Booking booking);
    List<Booking> getHistory(User user);
    List<Booking> getUpcoming(User user);
    List<Booking> getUserBookings(User user);
    Booking getDetails(Booking booking);
    boolean cancelBooking(Booking booking);
    boolean comfirmBooking(Booking booking);
    boolean changeTime(Booking booking);
    boolean changeStatus(Booking booking);
    boolean changeCost(int booking,int cost);
    void setLateStatus();
    List<Booking> lateBookings();
    List<Booking> getBookings();
    
}
