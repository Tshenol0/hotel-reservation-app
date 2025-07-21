/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Room.RoomService;


import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.Room.RoomModel.Room;
import java.util.List;

/**
 *
 * @author User
 */
public interface RoomService {
    List<Room> availableRooms();
    List<Room> unavailableRooms();
    Room getRoom(Room room);
    boolean updateStatus(Room room);
    List<Room> getRoom(Booking booking);
    List<Room> getRooms(Booking booking);
    List<Room> adminOptions(Booking booking);
    boolean setStatus(Booking booking);
}
