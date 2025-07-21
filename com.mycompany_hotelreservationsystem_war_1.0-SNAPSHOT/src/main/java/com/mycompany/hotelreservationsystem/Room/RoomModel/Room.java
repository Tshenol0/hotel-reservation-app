/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Room.RoomModel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author User
 */
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    
    private int id;
    private int occupants;
    private int rate;
    private int floor;
    private String status;
    private int booking;
    private String type;

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
     * @return the occupants
     */
    public int getOccupants() {
        return occupants;
    }

    /**
     * @param occupants the occupants to set
     */
    public void setOccupants(int occupants) {
        this.occupants = occupants;
    }

    /**
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * @return the floor
     */
    public int getFloor() {
        return floor;
    }

    /**
     * @param floor the floor to set
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the booking
     */
    public int getBooking() {
        return booking;
    }

    /**
     * @param booking the booking to set
     */
    public void setBooking(int booking) {
        this.booking = booking;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
}
