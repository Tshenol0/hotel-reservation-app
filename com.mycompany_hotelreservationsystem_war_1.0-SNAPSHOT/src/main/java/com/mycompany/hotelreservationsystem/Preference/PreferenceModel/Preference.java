/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Preference.PreferenceModel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author User
 */
@NoArgsConstructor
@AllArgsConstructor
public class Preference {
    private int id;
    private String choice;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the choice
     */
    public String getChoice() {
        return choice;
    }

    /**
     * @param choice the choice to set
     */
    public void setChoice(String choice) {
        this.choice = choice;
    }
}
