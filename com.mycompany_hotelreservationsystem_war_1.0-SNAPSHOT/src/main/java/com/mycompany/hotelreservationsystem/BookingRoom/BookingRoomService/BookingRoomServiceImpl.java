/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomService;

import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomDao.BookingRoomDao;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomModel.BookingRoom;
import com.mycompany.hotelreservationsystem.Room.RoomModel.Room;



/**
 *
 * @author User
 */
public class BookingRoomServiceImpl implements BookingRoomService{

    private BookingRoomDao brd;
    
    public BookingRoomServiceImpl(BookingRoomDao brd){
        this.brd = brd;
    }
    
    @Override
    public boolean addBookingRoom(BookingRoom bookingroom) {
        return brd.addBookingRoom(bookingroom.getBooking(),bookingroom.getRoom());
    }

    @Override
    public boolean removeBookingRoom(BookingRoom bookingroom) {
        return brd.removeBookingRoom(bookingroom.getBooking(), bookingroom.getRoom());
    }

    @Override
    public boolean clearForBooking(Booking booking) {
        return brd.clearForBooking(booking.getId());
    }
    
}
