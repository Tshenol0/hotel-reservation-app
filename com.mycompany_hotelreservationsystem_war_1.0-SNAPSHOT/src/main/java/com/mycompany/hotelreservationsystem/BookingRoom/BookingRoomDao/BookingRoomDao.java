/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomDao;

import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;


/**
 *
 * @author User
 */
public interface BookingRoomDao {
    boolean addBookingRoom(int booking,int room);
    boolean checkBookingRoom(int booking,int room);
    boolean removeBookingRoom(int booking,int room);
    boolean clearForBooking(int booking);
}
