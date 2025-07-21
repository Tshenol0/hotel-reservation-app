/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.BookingPreference.BookingPreferenceService;

import com.mycompany.hotelreservationsystem.BookingPreference.BookingPreferenceDao.BookingPreferenceDao;
import com.mycompany.hotelreservationsystem.BookingPreference.BookingPreferenceModel.BookingPreference;


/**
 *
 * @author User
 */
public class BookingPreferenceServiceImpl implements BookingPreferenceService{
    
    private BookingPreferenceDao sd;
    
    public BookingPreferenceServiceImpl(BookingPreferenceDao sd){
        this.sd = sd;
    }

    @Override
    public boolean specify(BookingPreference spec) {
        return sd.addBookingPreference(spec);
    }
    
}
