/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.User.UserController;

import com.mycompany.hotelreservationsystem.Exceptions.RegistrationException;
import com.mycompany.hotelreservationsystem.User.UserDao.UserDaoImpl;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import com.mycompany.hotelreservationsystem.User.UserService.UserService;
import com.mycompany.hotelreservationsystem.User.UserService.UserServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "VerificationServlet", urlPatterns = {"/VerificationServlet"})
public class VerificationServlet extends HttpServlet {

    private final UserService us = new UserServiceImpl(new UserDaoImpl());
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            User user = new User();
            user.setName(request.getParameter("name").trim());
            user.setSurname(request.getParameter("surname").trim());
            user.setEmail(request.getParameter("email").trim());
            user.setPassword(request.getParameter("password").trim());
            
        try {
            String token = us.Verify(user);
            HttpSession anothersession = request.getSession();
            anothersession.setAttribute("token",token);
            anothersession.setMaxInactiveInterval(600);
            request.getRequestDispatcher("checkemail.jsp").forward(request, response);
        } catch (RegistrationException ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
