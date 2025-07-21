/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Booking.BookingModel;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author User
 */
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    
    private int id;
    private Timestamp start;
    private Timestamp end;
    private int user;
    private String status;
    private int cost;
    private Timestamp create;

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
     * @return the start
     */
    public Timestamp getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Timestamp start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public Timestamp getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    /**
     * @return the user
     */
    public int getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(int user) {
        this.user = user;
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
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * @return the create
     */
    public Timestamp getCreate() {
        return create;
    }

    /**
     * @param create the create to set
     */
    public void setCreate(Timestamp create) {
        this.create = create;
    }
    
}
