/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Preference.PreferenceService;

import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.Preference.PreferenceDao.PreferenceDao;
import com.mycompany.hotelreservationsystem.Preference.PreferenceModel.Preference;
import java.util.List;

/**
 *
 * @author User
 */
public class PreferenceServiceImpl implements PreferenceService{

    private PreferenceDao preferenceDao;
    
    public PreferenceServiceImpl(PreferenceDao preferenceDao){
        this.preferenceDao = preferenceDao;
    }
    
    @Override
    public List<Preference> getPreferences() {
        return preferenceDao.getPreference();
    } 

    @Override
    public List<Preference> getBookingPreferences(Booking booking) {
        return preferenceDao.getBookingPreferences(booking.getId());
    }
    
    
}
