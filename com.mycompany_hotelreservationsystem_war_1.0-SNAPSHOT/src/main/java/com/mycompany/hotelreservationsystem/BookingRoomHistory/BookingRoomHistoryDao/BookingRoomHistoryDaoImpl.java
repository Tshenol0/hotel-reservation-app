/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryDao;

import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomDao.BookingRoomDaoImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class BookingRoomHistoryDaoImpl implements BookingRoomHistoryDao{
    
    private Connection con;
    
    public BookingRoomHistoryDaoImpl(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookingRoomHistoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //PreparedStatement ps = con.
        //PreparedStatement ps = con.

    }

    @Override
    public boolean addBookingRoomHistory(int booking,int room) {
        int affect = 0;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("insert into bookingroomhistory (booking,room) values(?,?)");
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
    public boolean removeBookingRoomHistory(int room, int booking) {
        int affect = 0;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("delete from bookingroomhistory where booking=? and room=?");
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
    
}
