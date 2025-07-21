/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.User.UserService;

import at.favre.lib.crypto.bcrypt.BCrypt;

import com.mycompany.hotelreservationsystem.Email.EmailModel.Email;
import com.mycompany.hotelreservationsystem.Email.EmailService.EmailServiceImpl;
import com.mycompany.hotelreservationsystem.Exceptions.LoginException;
import com.mycompany.hotelreservationsystem.Exceptions.RegistrationException;
import com.mycompany.hotelreservationsystem.User.UserDao.UserDao;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import java.util.List;


/**
 *
 * @author User
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean register(User user) throws RegistrationException {

        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            throw new RegistrationException("Invalid user!");
        }
        
        
        String hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(hashedPassword);
                                                                                                         //xgrl ktat loof zvlx tshenolo
                                                                                                         //ukpt alkb ncuc wgqh theminister
        if (userDao.addUser(user)) {
            Email mail = new Email("tshenoloseduma@gmail.com", user.getEmail(), "Succesfully registered", "xgrl ktat loof zvlx", "Registration");
            EmailServiceImpl es = new EmailServiceImpl(mail);
            es.sendMail();
            return true;
        } else {
            throw new RegistrationException();
        }

    }

    @Override
    public User login(User user) throws LoginException {
        User user1 = null;
        String hash = "";
        try {
            user1 = userDao.checkUser(user);
            hash = user1.getPassword();
        } catch (LoginException ex) {
            throw ex;
        }
        BCrypt.Result result = BCrypt.verifyer().verify(user.getPassword().toCharArray(), hash);
        if (!result.verified) {
            throw new LoginException("password or email incorrect");
        }
        return user1;
    }

    @Override
    public List<User> getUsers(User user) {
        return userDao.getUsers(user.getId());
    }

    @Override
    public User getUser(User user) {
        return userDao.getUser(user);
    }
    

    @Override
    public String Verify(User user) throws RegistrationException {
        
            if(!userDao.verifyEmail(user.getEmail())){
                throw new RegistrationException("email already taken");      
            }
        
            Email mail = new Email("theminister07@gmail.com", user.getEmail(), "please verify your email", "ukpt alkb ncuc wgqh", "Verification");
            EmailServiceImpl es = new EmailServiceImpl(mail);
            
            return es.verification(user);
            
    }
}
