/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomService;

import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomModel.BookingRoom;


/**
 *
 * @author User
 */
public interface BookingRoomService {
    boolean addBookingRoom(BookingRoom bookingroom);
    boolean removeBookingRoom(BookingRoom bookingroom);
    boolean clearForBooking(Booking booking);
}
