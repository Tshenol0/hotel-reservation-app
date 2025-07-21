/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomModel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author User
 */
@NoArgsConstructor
@AllArgsConstructor
public class BookingRoom {
    private int booking;
    private int room;

    /**
     * @return the booking
     */
    public int getBooking() {
        return booking;
    }

    /**
     * @param booking the booking to set
     */
    public void setBooking(int booking) {
        this.booking = booking;
    }

    /**
     * @return the room
     */
    public int getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(int room) {
        this.room = room;
    }
}
