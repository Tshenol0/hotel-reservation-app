/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.User.UserController;

import com.mycompany.hotelreservationsystem.Booking.BookingDao.BookingDaoImpl;
import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingService;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingServiceImpl;
import com.mycompany.hotelreservationsystem.User.UserDao.UserDaoImpl;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import com.mycompany.hotelreservationsystem.User.UserService.UserService;
import com.mycompany.hotelreservationsystem.User.UserService.UserServiceImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "UserDetailServlet", urlPatterns = {"/UserDetailServlet"})
public class UserDetailServlet extends HttpServlet {
    
    private final UserService us = new UserServiceImpl(new UserDaoImpl());
    private final BookingService bs = new BookingServiceImpl(new BookingDaoImpl());

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
                User oneUser = new User();
                oneUser.setId(Integer.parseInt(request.getParameter("user")));
                System.out.println(Integer.parseInt(request.getParameter("user")));
                List<Booking> userBookings = bs.getUserBookings(oneUser);
                System.out.println(userBookings);
                User person = us.getUser(oneUser);
                request.setAttribute("bookings", userBookings);
                request.setAttribute("user", person);
                request.getRequestDispatcher("adminbooking.jsp").forward(request, response);
        
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
