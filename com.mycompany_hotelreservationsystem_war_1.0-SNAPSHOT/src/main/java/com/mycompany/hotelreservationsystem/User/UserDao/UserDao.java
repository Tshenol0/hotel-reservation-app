/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.User.UserDao;


import com.mycompany.hotelreservationsystem.Exceptions.LoginException;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import java.util.List;

/**
 *
 * @author User
 */
public interface UserDao {

    boolean addUser(User user);
    User checkUser(User user) throws LoginException;
    List<User> getUsers(int user);
    User getUser(User user);
    boolean verifyEmail(String email);
}
