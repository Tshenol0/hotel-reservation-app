/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Room.RoomDao;


import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.Room.RoomModel.Room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class RoomDaoImpl implements RoomDao{

    private Connection con;
    
    public RoomDaoImpl(){
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //PreparedStatement ps = con.
        
        //PreparedStatement ps = con.

    }
    
    @Override
    public List<Room> availableRooms() {

        Statement cs=null;
        ResultSet rs = null;
        List<Room> rooms = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            cs = con.createStatement();
            rs = cs.executeQuery("select * from rooms left join bookingroom on rooms.room_id = bookingroom.room where bookingroom.room is null");
              
            while(rs.next()){
                Room room1 = new Room();
                room1.setId(rs.getInt("room_id"));
                room1.setOccupants(rs.getInt("occupants"));
                room1.setFloor(rs.getInt("floor"));
                room1.setRate(rs.getInt("rate"));
                room1.setStatus(rs.getString("status"));
                room1.setType(rs.getString("type"));
                rooms.add(room1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(cs!=null){
                    cs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return rooms;
                
    }

    @Override
    public List<Room> unavailableRooms() {
        
        Statement cs=null;
        ResultSet rs = null;
        List<Room> rooms = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            cs = con.createStatement();
            rs = cs.executeQuery("select * from rooms join bookingroom on rooms.room_id = bookingroom.room join bookings on bookingroom.booking = bookings.book_id where bookings.status='pending' or bookings.status='accepted'");
              
            while(rs.next()){
                Room room1 = new Room();
                room1.setId(rs.getInt("room_id"));
                room1.setOccupants(rs.getInt("occupants"));
                room1.setFloor(rs.getInt("floor"));
                room1.setRate(rs.getInt("rate"));
                room1.setStatus(rs.getString("status"));
                room1.setType(rs.getString("type"));                
                rooms.add(room1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(cs!=null){
                    cs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return rooms;
                   
    }
    
    @Override
    public Room getRoom(int room) {

        PreparedStatement ps=null;
        ResultSet rs = null;
        Room room1 = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("select * from rooms where room_id=?");
            ps.setInt(1, room);
            rs = ps.executeQuery();
              
            while(rs.next()){
                room1 = new Room();
                room1.setId(rs.getInt("room_id"));
                room1.setOccupants(rs.getInt("occupants"));
                room1.setFloor(rs.getInt("floor"));
                room1.setRate(rs.getInt("rate"));
                room1.setStatus(rs.getString("status"));
                room1.setType(rs.getString("type"));                
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return room1;
    
    }

    @Override
    public boolean setStatus(int room,String status) {
        
        PreparedStatement ps=null;
        int affect = 0;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("update rooms set status=? where room_id=?");
            ps.setString(1, status);
            ps.setInt(2, room);
            affect = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return affect==1;
      
    }

    @Override
    public List<Room> getBookedRoom(int booking) {

        PreparedStatement ps=null;
        ResultSet rs = null;
        Room room1;
        List<Room> rooms = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("SELECT * FROM rooms LEFT JOIN bookingroom ON rooms.room_id = bookingroom.room LEFT JOIN bookings ON bookingroom.booking=bookings.book_id WHERE \n" +
"bookings.book_id=?");
            ps.setInt(1, booking);
            rs = ps.executeQuery();
              
            while(rs.next()){
                room1 = new Room();
                room1.setId(rs.getInt("room_id"));
                room1.setOccupants(rs.getInt("occupants"));
                room1.setFloor(rs.getInt("floor"));
                room1.setRate(rs.getInt("rate"));
                room1.setStatus(rs.getString("status"));
                room1.setType(rs.getString("type"));                
                rooms.add(room1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }    
        return rooms;
    }

    @Override
    public List<Room> getRooms(int booking) {
        
        PreparedStatement ps=null;
        ResultSet rs = null;
        Room room1;
        List<Room> rooms = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("select * from rooms join bookingroomhistory on rooms.room_id = bookingroomhistory.room join bookings on bookingroomhistory.booking = bookings.book_id where bookings.book_id=?");
            ps.setInt(1, booking);
            rs = ps.executeQuery();
              
            while(rs.next()){
                room1 = new Room();
                room1.setId(rs.getInt("room_id"));
                room1.setOccupants(rs.getInt("occupants"));
                room1.setFloor(rs.getInt("floor"));
                room1.setRate(rs.getInt("rate"));
                room1.setStatus(rs.getString("status"));
                room1.setType(rs.getString("type"));                
                rooms.add(room1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return rooms;
    
    }

    @Override
    public List<Room> adminOptions(int booking) {

        
        PreparedStatement ps=null;
        ResultSet rs = null;
        Room room1;
        List<Room> rooms = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("SELECT  DISTINCT rooms.* FROM rooms LEFT JOIN bookingroom ON rooms.room_id = bookingroom.room LEFT JOIN bookings ON bookingroom.booking=bookings.book_id WHERE (rooms.`status`='available' OR bookingroom.room IS NULL) OR (bookings.book_id=? AND bookings.`status` IN ('pending','accepted'))");
            ps.setInt(1, booking);
            rs = ps.executeQuery();
              
            while(rs.next()){
                room1 = new Room();
                room1.setId(rs.getInt("room_id"));
                room1.setOccupants(rs.getInt("occupants"));
                room1.setFloor(rs.getInt("floor"));
                room1.setRate(rs.getInt("rate"));
                room1.setStatus(rs.getString("status"));
                room1.setType(rs.getString("type"));                
                rooms.add(room1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return rooms;
            
    }

    @Override
    public boolean setStatus(int booking) {
    
        PreparedStatement ps=null;
        int affect = 0;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("update rooms join bookingroom on rooms.room_id=bookingroom.room join bookings on bookingroom.booking=bookings.book_id set rooms.status='available' where bookings.book_id=?");
            ps.setInt(1, booking);
            affect = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return affect>0;
          
    }
    
    
}
