/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryDao;

/**
 *
 * @author User
 */
public interface BookingRoomHistoryDao {
    
    boolean addBookingRoomHistory(int booking,int room);
    boolean removeBookingRoomHistory(int room,int booking);
}
