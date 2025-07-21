/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.User.UserService;


import com.mycompany.hotelreservationsystem.Exceptions.LoginException;
import com.mycompany.hotelreservationsystem.Exceptions.RegistrationException;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import java.util.List;

/**
 *
 * @author User
 */
public interface UserService {
    boolean register(User user)throws RegistrationException;
    User login(User user) throws LoginException;
    List<User> getUsers(User user);
    User getUser(User user);
    String Verify(User user) throws RegistrationException;
}
