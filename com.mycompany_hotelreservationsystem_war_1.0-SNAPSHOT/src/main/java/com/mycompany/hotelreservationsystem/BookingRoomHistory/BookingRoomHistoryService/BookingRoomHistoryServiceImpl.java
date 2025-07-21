/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryService;

import com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryDao.BookingRoomHistoryDao;
import com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryModel.BookingRoomHistory;

/**
 *
 * @author User
 */
public class BookingRoomHistoryServiceImpl implements BookingRoomHistoryService{
    
    private BookingRoomHistoryDao brhd;

    public BookingRoomHistoryServiceImpl(BookingRoomHistoryDao brhd){
        this.brhd = brhd;
    }
    
    @Override
    public boolean addHistory(BookingRoomHistory bookingroomhistory) {
        return brhd.addBookingRoomHistory(bookingroomhistory.getBooking(), bookingroomhistory.getRoom());
    }

    @Override
    public boolean removeHistory(BookingRoomHistory bookingroomhistory) {
        return brhd.removeBookingRoomHistory(bookingroomhistory.getRoom(), bookingroomhistory.getBooking());
    }
    
}
