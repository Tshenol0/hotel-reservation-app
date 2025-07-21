/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Booking.BookingService;

import com.mycompany.hotelreservationsystem.Booking.BookingDao.BookingDao;
import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.Exceptions.DurationException;
import com.mycompany.hotelreservationsystem.Exceptions.StartEndDateException;
import com.mycompany.hotelreservationsystem.Exceptions.InvalidDateException;
import com.mycompany.hotelreservationsystem.Exceptions.SameTimeException;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author User
 */
public class BookingServiceImpl implements BookingService{
    
    
    private BookingDao bd;
    
    public BookingServiceImpl(BookingDao bd){
        this.bd = bd;
    }

    @Override
    public boolean addBooking(Booking booking) throws InvalidDateException,StartEndDateException,SameTimeException,DurationException{
        
        Timestamp current = new Timestamp(System.currentTimeMillis());
        
        if(booking.getStart().before(current) || booking.getEnd().before(current)){
            throw new InvalidDateException();
        }
        if(booking.getStart().after(booking.getEnd())){
            throw new StartEndDateException();
        }
        if(booking.getStart().equals(booking.getEnd())){
            throw new SameTimeException();
        }
        if((booking.getEnd().getTime()-booking.getStart().getTime())/(60*60*1000)<1){
            throw new DurationException();
        }
        
        return bd.addBooking(booking);
    }

    @Override
    public Booking getBooking(Booking booking) {
        return bd.getBooking(booking);
    }

    @Override
    public List<Booking> getUserBookings(User user) {
        return bd.getUserBookings(user);
    }

    @Override
    public Booking getDetails(Booking booking) {
        return bd.getDetails(booking);
    }

    @Override
    public boolean cancelBooking(Booking booking) {
        return bd.cancelBooking(booking);
    }

    @Override
    public boolean confirmBooking(Booking booking) {
        
        return bd.comfirmBooking(booking);
    }

    @Override
    public List<Booking> getHistory(User user) {
        return bd.getHistory(user);
    }

    @Override
    public List<Booking> getUpcoming(User user) {
        return bd.getUpcoming(user);
    }

    @Override
    public boolean changeTime(Booking booking) throws InvalidDateException,StartEndDateException,SameTimeException,DurationException{
        Timestamp current = new Timestamp(System.currentTimeMillis());
        
        if(booking.getStart().before(current) || booking.getEnd().before(current)){
            throw new InvalidDateException();
        }
        if(booking.getStart().after(booking.getEnd())){
            throw new StartEndDateException();
        }
        if(booking.getStart().equals(booking.getEnd())){
            throw new SameTimeException();
        }
        if((booking.getEnd().getTime()-booking.getStart().getTime())/(60*60*1000)<1){
            throw new DurationException();
        }
        return bd.changeTime(booking);
    }

    @Override
    public boolean changeCost(Booking booking) {
       return bd.changeCost(booking.getId(), booking.getCost());
    }

    @Override
    public void setLateStatus() {
        bd.setLateStatus();
    }

    @Override
    public List<Booking> getLateBookings() {
        return bd.lateBookings();
    }

    @Override
    public List<Booking> getBookings() {
        return bd.getBookings();
    }
    

}
