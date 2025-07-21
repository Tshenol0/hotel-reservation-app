/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Room.RoomService;


import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.Room.RoomDao.RoomDao;
import com.mycompany.hotelreservationsystem.Room.RoomDao.RoomDaoImpl;
import com.mycompany.hotelreservationsystem.Room.RoomModel.Room;
import java.util.List;

/**
 *
 * @author User
 */
public class RoomServiceImpl implements RoomService{

    private RoomDao roomDao;
    
    public RoomServiceImpl(RoomDao roomDao){
        this.roomDao = roomDao;
    }
    
    @Override
    public List<Room> availableRooms() {
        return roomDao.availableRooms();
    }

    @Override
    public List<Room> unavailableRooms() {
        return roomDao.unavailableRooms();
    }
    
    @Override
    public Room getRoom(Room room) {
        return roomDao.getRoom(room.getId());
    }

    @Override
    public boolean updateStatus(Room room) {
        return roomDao.setStatus(room.getId(),room.getStatus());
    }

    @Override
    public List<Room> getRoom(Booking booking) {
        return roomDao.getBookedRoom(booking.getId());
    }

    @Override
    public List<Room> getRooms(Booking booking) {
        return roomDao.getRooms(booking.getId());
    }

    @Override
    public List<Room> adminOptions(Booking booking) {
        return roomDao.adminOptions(booking.getId());
    }

    @Override
    public boolean setStatus(Booking booking) {
        return roomDao.setStatus(booking.getId());
    }
    
    public static void main(String[] args) {
        RoomService r = new RoomServiceImpl(new RoomDaoImpl());
        Booking booking = new Booking();
        booking.setId(2);
        System.out.println("check----");
        System.out.println(r.getRooms(booking));
    }
    
}
