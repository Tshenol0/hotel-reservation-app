/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.User.UserDao;

import com.mycompany.hotelreservationsystem.Exceptions.LoginException;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class UserDaoImpl implements UserDao {

    private Connection con;

    public UserDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
          

        } catch (ClassNotFoundException ex) {
            

            ex.printStackTrace();
        }
        //PreparedStatement ps = con.
        //PreparedStatement ps = con.

    }

    public void getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            System.out.println("Connected...");
        } catch (SQLException ex) {
            System.out.println("Connection failed "+ex.getMessage());
        }
    }

    @Override
    public boolean addUser(User user) {
        int affect = 0;
        PreparedStatement ps = null;
        try {
            getConnection();
            ps = con.prepareStatement("insert into users (name,surname,email,password) values(?,?,?,?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            affect = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Could not add user.");
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Could not close connectionm");
                ex.printStackTrace();
                
            }
         
        }
        return affect == 1;
    }

    @Override
    public User checkUser(User user) throws LoginException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        User user1 = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            ps = con.prepareStatement("select * from users where email=?");
            ps.setString(1, user.getEmail());
            rs = ps.executeQuery();

            while (rs.next()) {
                user1 = new User();
                user1.setId(rs.getInt("id"));
                user1.setName(rs.getString("name"));
                user1.setSurname(rs.getString("surname"));
                user1.setPassword(rs.getString("password"));
                user1.setEmail(rs.getString("email"));
                user1.setType(rs.getString("type"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (user1 == null) {
            throw new LoginException("email not registered");
        }

        return user1;
    }

    @Override
    public List<User> getUsers(int admin) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            ps = con.prepareStatement("select id,name,surname,email,type from users where id <> ?");
            ps.setInt(1, admin);
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setType(rs.getString("type"));
                users.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return users;

    }

    @Override
    public User getUser(User user) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        User user1 = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            ps = con.prepareStatement("select * from users where id=?");
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                user1 = new User();
                user1.setId(rs.getInt("id"));
                user1.setName(rs.getString("name"));
                user1.setSurname(rs.getString("surname"));
                user1.setPassword(rs.getString("password"));
                user1.setEmail(rs.getString("email"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return user1;
    }

   // public static void main(String[] args) {
    //    UserDao userDao = new UserDaoImpl();

      //  boolean added = userDao.addUser(new User(0, "n", "ds", "Dsd", "dsd", "ds"));
       // System.out.println(added);
    //}

    @Override
    public boolean verifyEmail(String email) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        User user1 = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservationsystem?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            ps = con.prepareStatement("select * from users where email=?");
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                user1 = new User();
                user1.setId(rs.getInt("id"));
                user1.setName(rs.getString("name"));
                user1.setSurname(rs.getString("surname"));
                user1.setPassword(rs.getString("password"));
                user1.setEmail(rs.getString("email"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return user1==null;        
    }
}
