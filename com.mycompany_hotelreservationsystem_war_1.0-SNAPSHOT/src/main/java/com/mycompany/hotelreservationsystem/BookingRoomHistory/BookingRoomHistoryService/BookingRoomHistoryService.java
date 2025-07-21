/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryService;

import com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryModel.BookingRoomHistory;

/**
 *
 * @author User
 */
public interface BookingRoomHistoryService {
    boolean addHistory(BookingRoomHistory bookingroomhistory);
    boolean removeHistory(BookingRoomHistory bookingroomhistory);
}
