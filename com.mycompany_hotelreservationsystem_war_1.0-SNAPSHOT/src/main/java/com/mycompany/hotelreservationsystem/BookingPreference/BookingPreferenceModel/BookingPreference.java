/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.BookingPreference.BookingPreferenceModel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author User
 */
@NoArgsConstructor
@AllArgsConstructor
public class BookingPreference {
    private int book;
    private int prefer;

    /**
     * @return the book
     */
    public int getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(int book) {
        this.book = book;
    }

    /**
     * @return the prefer
     */
    public int getPrefer() {
        return prefer;
    }

    /**
     * @param prefer the prefer to set
     */
    public void setPrefer(int prefer) {
        this.prefer = prefer;
    }
}
