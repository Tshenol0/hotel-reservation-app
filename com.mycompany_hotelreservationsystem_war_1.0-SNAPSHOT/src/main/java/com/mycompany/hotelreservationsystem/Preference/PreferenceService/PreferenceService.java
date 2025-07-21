/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Preference.PreferenceService;


import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.Preference.PreferenceModel.Preference;
import java.util.List;

/**
 *
 * @author User
 */
public interface PreferenceService {
    List<Preference> getPreferences();
    List<Preference> getBookingPreferences(Booking booking);
}
