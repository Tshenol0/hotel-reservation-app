/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomDao;


import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomModel.BookingRoom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class BookingRoomDaoImpl implements BookingRoomDao{
    
    private Connection con;
    
    public BookingRoomDaoImpl(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //PreparedStatement ps = con.
        //PreparedStatement ps = con.

    }

    @Override
    public boolean addBookingRoom(int booking,int room) {
        int affect = 0;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("insert into bookingroom (booking,room) values(?,?)");
            ps.setInt(1, booking);
            ps.setInt(2, room);
            
            affect = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(ps!=null){
                    ps.close();                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(con!=null){
                    con.close();
                }
                
            } catch (SQLException ex) {
            Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return affect==1;
   
    }

    @Override
    public boolean checkBookingRoom(int booking, int room) {
                
        PreparedStatement ps=null;
        ResultSet rs = null;
        BookingRoom book = null;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("select * from bookingroom where booking=? and room=?");
            ps.setInt(1, booking);
            ps.setInt(2, room);        
            
            rs = ps.executeQuery();
              
            while(rs.next()){
                book = new BookingRoom(rs.getInt("booking"),rs.getInt("room"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return book==null;
    }

    @Override
    public boolean removeBookingRoom(int booking, int room) {

        int affect = 0;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("delete from bookingroom where booking=? and room=?");
            ps.setInt(1, booking);
            ps.setInt(2, room);
            
            affect = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(ps!=null){
                    ps.close();                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(con!=null){
                    con.close();
                }
                
            } catch (SQLException ex) {
            Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return affect>0;        
        
    }

    @Override
    public boolean clearForBooking(int booking) {

        int affect = 0;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("delete from bookingroom where booking=?");
            ps.setInt(1, booking);
            
            affect = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(ps!=null){
                    ps.close();                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(con!=null){
                    con.close();
                }
                
            } catch (SQLException ex) {
            Logger.getLogger(BookingRoomDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return affect>0;         
    }
    
    
    
}
