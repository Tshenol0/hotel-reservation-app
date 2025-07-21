/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Preference.PreferenceDao;

import com.mycompany.hotelreservationsystem.Preference.PreferenceModel.Preference;
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
public class PreferenceDaoImpl implements PreferenceDao{
    
        
    private Connection con;
    
    public PreferenceDaoImpl(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PreferenceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //PreparedStatement ps = con.

    }

    @Override
    public List<Preference> getPreference() {
        Statement cs=null;
        ResultSet rs = null;
        List<Preference> preferences = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            cs = con.createStatement();
            rs = cs.executeQuery("select * from preferences");
              
            while(rs.next()){
                Preference pre = new Preference();
                pre.setId(rs.getInt("prefer_id"));
                pre.setChoice(rs.getString("choice"));
                preferences.add(pre);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            try {
                if(cs!=null){
                    cs.close();                    
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
      
        }
        
        return preferences;  
    }

    @Override
    public List<Preference> getBookingPreferences(int booking) {

        PreparedStatement ps=null;
        ResultSet rs = null;
        List<Preference> preferences = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("SELECT * FROM preferences JOIN bookingpreference ON preferences.prefer_id = bookingpreference.preference join bookings on bookingpreference.booking = bookings.book_id WHERE bookings.book_id=?");
            ps.setInt(1, booking);
            rs = ps.executeQuery();
              
            while(rs.next()){
                Preference pre = new Preference();
                pre.setId(rs.getInt("prefer_id"));
                pre.setChoice(rs.getString("choice"));
                preferences.add(pre);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PreferenceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(PreferenceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(PreferenceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PreferenceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return preferences; 
    }
    
}
