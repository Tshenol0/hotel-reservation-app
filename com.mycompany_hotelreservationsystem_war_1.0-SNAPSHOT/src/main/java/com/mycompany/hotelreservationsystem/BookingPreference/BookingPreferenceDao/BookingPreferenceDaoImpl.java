/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.BookingPreference.BookingPreferenceDao;

import com.mycompany.hotelreservationsystem.BookingPreference.BookingPreferenceModel.BookingPreference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author User
 */
public class BookingPreferenceDaoImpl implements BookingPreferenceDao{
    
    private Connection con;
    
    public BookingPreferenceDaoImpl(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        //PreparedStatement ps = con.
        //PreparedStatement ps = con.
        //PreparedStatement ps = con.
        //PreparedStatement ps = con.

    }

    @Override
    public boolean addBookingPreference(BookingPreference specify) {

        int affect = 0;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("insert into bookingpreference (booking,preference) values(?,?)");
            ps.setInt(1, specify.getBook());
            ps.setInt(2, specify.getPrefer());
            
            affect = ps.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                if(ps!=null){
                    ps.close();                    
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if(con!=null){
                    con.close();
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return affect==1;

    }
    
}
