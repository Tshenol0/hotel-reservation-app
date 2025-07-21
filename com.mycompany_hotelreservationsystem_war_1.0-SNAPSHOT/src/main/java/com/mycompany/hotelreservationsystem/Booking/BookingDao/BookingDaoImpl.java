/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Booking.BookingDao;


import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.User.UserDao.UserDaoImpl;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class BookingDaoImpl implements BookingDao{
    
    private Connection con;
    
    public BookingDaoImpl(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //PreparedStatement ps = con.

    }

    @Override
    public boolean addBooking(Booking booking) {
        int affect = 0;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("insert into bookings (start,end,user,cost,created) values(?,?,?,?,now())");
            ps.setTimestamp(1, booking.getStart());
            ps.setTimestamp(2, booking.getEnd());
            ps.setInt(3, booking.getUser());
            ps.setInt(4,booking.getCost());
            
            affect = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(ps!=null){
                    ps.close();                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(con!=null){
                    con.close();
                }
                
            } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return affect==1;
    }

    @Override
    public Booking getBooking(Booking booking) {
        
        PreparedStatement ps=null;
        ResultSet rs = null;
        Booking book = null;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("select * from bookings where start=? and user=? and end=?");
            ps.setTimestamp(1, booking.getStart());
            ps.setInt(2, booking.getUser());
            ps.setTimestamp(3, booking.getEnd());
            
            rs = ps.executeQuery();
              
            while(rs.next()){
                book = new Booking(rs.getInt("book_id"),rs.getTimestamp("start"),rs.getTimestamp("end"),rs.getInt("user"),rs.getString("status"),rs.getInt("cost"),rs.getTimestamp("created"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return book;
    }

    @Override
    public List<Booking> getHistory(User user) {

        PreparedStatement ps=null;
        ResultSet rs = null;
        List<Booking> bookings = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("select * from bookings where (end<? and user=?) or status='cancelled' or status='late'");
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ps.setString(1, now.format(formatter));
            ps.setInt(2, user.getId());
            rs = ps.executeQuery();
              
            while(rs.next()){
                Booking book = new Booking();
                book.setId(rs.getInt("book_id"));
                book.setStart(rs.getTimestamp("start"));
                book.setEnd(rs.getTimestamp("end"));
                book.setUser(rs.getInt("user"));
                book.setStatus(rs.getString("status"));
                book.setCost(rs.getInt("cost"));
                bookings.add(book);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return bookings;
        
    }

    @Override
    public List<Booking> getUpcoming(User user) {
        
        PreparedStatement ps=null;
        ResultSet rs = null;
        List<Booking> bookings = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("select * from bookings where start>? and user=? and bookings.status='accepted'");
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ps.setString(1, now.format(formatter));
            ps.setInt(2, user.getId());
            
            rs = ps.executeQuery();
              
            while(rs.next()){
                Booking book = new Booking();
                book.setId(rs.getInt("book_id"));
                book.setStart(rs.getTimestamp("start"));
                book.setEnd(rs.getTimestamp("end"));
                book.setUser(rs.getInt("user"));
                book.setStatus(rs.getString("status"));
                book.setCost(rs.getInt("cost"));
                bookings.add(book);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return bookings;
    }

    @Override
    public List<Booking> getUserBookings(User user) {

        PreparedStatement ps=null;
        ResultSet rs = null;
        List<Booking> bookings = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("select * from bookings where user=? and start>? and status in ('accepted','pending','late')");
            ps.setInt(1, user.getId());
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ps.setString(2, now.format(formatter));
            
            rs = ps.executeQuery();
              
            while(rs.next()){
                Booking book = new Booking();
                book.setId(rs.getInt("book_id"));
                book.setStart(rs.getTimestamp("start"));
                book.setEnd(rs.getTimestamp("end"));
                book.setUser(rs.getInt("user"));
                book.setStatus(rs.getString("status"));
                book.setCost(rs.getInt("cost"));
                bookings.add(book);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return bookings;
        
    }

    @Override
    public Booking getDetails(Booking booking) {
        PreparedStatement ps=null;
        ResultSet rs = null;
        Booking book = null;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("select *  from bookings where book_id=?");
            ps.setInt(1, booking.getId());           
            rs = ps.executeQuery();
              
            while(rs.next()){
                book = new Booking(rs.getInt("book_id"),rs.getTimestamp("start"),rs.getTimestamp("end"),rs.getInt("user"),rs.getString("status"),rs.getInt("cost"),rs.getTimestamp("created"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return book;    
    }

    @Override
    public boolean cancelBooking(Booking booking) {


        int affect = 0;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("update bookings set status='cancelled' where status in ('accepted','pending','late') and book_id=?");
            ps.setInt(1, booking.getId());
            affect = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(ps!=null){
                    ps.close();                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(con!=null){
                    con.close();
                }
                
            } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return affect==1;        
        
    }

    @Override
    public boolean comfirmBooking(Booking booking) {


        int affect = 0;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("update bookings set status='accepted' where status='pending' and book_id=?");
            ps.setInt(1, booking.getId());
            affect = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(ps!=null){
                    ps.close();                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(con!=null){
                    con.close();
                }
                
            } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return affect==1;        
        
    }

    @Override
    public boolean changeTime(Booking booking) {

        int affect = 0;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("update bookings set start=?,end=? where book_id=?");
            ps.setTimestamp(1, booking.getStart());
            ps.setTimestamp(2, booking.getEnd());
            ps.setInt(3, booking.getId());
            affect = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(ps!=null){
                    ps.close();                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(con!=null){
                    con.close();
                }
                
            } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return affect==1; 
        
    }

    @Override
    public boolean changeStatus(Booking booking) {
        
        int affect = 0;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("update bookings set status=? where and book_id=?");
            ps.setString(1, booking.getStatus());
            ps.setInt(2, booking.getId());
            affect = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(ps!=null){
                    ps.close();                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(con!=null){
                    con.close();
                }
                
            } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return affect==1;        
             
    }

    @Override
    public boolean changeCost(int booking,int cost) {
        
        int affect = 0;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("update bookings set cost=? where book_id=?");
            ps.setInt(1, cost);
            ps.setInt(2, booking);
            affect = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(ps!=null){
                    ps.close();                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(con!=null){
                    con.close();
                }
                
            } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return affect==1;        
    }

    @Override
    public void setLateStatus() {
        
        PreparedStatement ps=null;
        int rs = 0;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.prepareStatement("update bookings set status='late' where created<? and status='pending'");
            ps.setTimestamp(1,Timestamp.valueOf(LocalDateTime.now().minusMinutes(8l)));
            rs = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }        
        
    }

    @Override
    public List<Booking> lateBookings() {
    
        Statement ps=null;
        ResultSet rs = null;
        List<Booking> bookings = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.createStatement();
            
            rs = ps.executeQuery("select * from bookings where status='late'");
              
            while(rs.next()){
                Booking book = new Booking();
                book.setId(rs.getInt("book_id"));
                book.setStart(rs.getTimestamp("start"));
                book.setEnd(rs.getTimestamp("end"));
                book.setUser(rs.getInt("user"));
                book.setStatus(rs.getString("status"));
                book.setCost(rs.getInt("cost"));
                bookings.add(book);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return bookings;        
    }

    @Override
    public List<Booking> getBookings() {

    
        Statement ps=null;
        ResultSet rs = null;
        List<Booking> bookings = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false","root","root");
            ps = con.createStatement();
            
            rs = ps.executeQuery("select * from bookings");
              
            while(rs.next()){
                Booking book = new Booking();
                book.setId(rs.getInt("book_id"));
                book.setStart(rs.getTimestamp("start"));
                book.setEnd(rs.getTimestamp("end"));
                book.setUser(rs.getInt("user"));
                book.setStatus(rs.getString("status"));
                book.setCost(rs.getInt("cost"));
                bookings.add(book);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs!=null){
                    rs.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(ps!=null){
                    ps.close();                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
        
        return bookings;          
    }
    
    
    
    
    
}
