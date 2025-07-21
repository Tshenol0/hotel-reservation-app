/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Room.RoomDao;

import com.mycompany.hotelreservationsystem.Room.RoomModel.Room;
import java.util.List;

/**
 *
 * @author User
 */
public interface RoomDao {
    
    List<Room> availableRooms();
    List<Room> unavailableRooms();
    Room getRoom(int room);
    boolean setStatus(int room,String status);
    List<Room> getBookedRoom(int booking);
    List<Room> getRooms(int booking);
    List<Room> adminOptions(int booking);
    boolean setStatus(int booking);
}
