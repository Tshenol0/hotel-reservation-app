/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Room.RoomController;

import com.mycompany.hotelreservationsystem.Booking.BookingDao.BookingDaoImpl;
import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingService;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingServiceImpl;
import com.mycompany.hotelreservationsystem.Room.RoomDao.RoomDaoImpl;
import com.mycompany.hotelreservationsystem.Room.RoomModel.Room;
import com.mycompany.hotelreservationsystem.Room.RoomService.RoomService;
import com.mycompany.hotelreservationsystem.Room.RoomService.RoomServiceImpl;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
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
@WebServlet(name = "AdminEditSelectServlet", urlPatterns = {"/AdminEditSelectServlet"})
public class AdminEditSelectServlet extends HttpServlet {

    private final RoomService rs = new RoomServiceImpl(new RoomDaoImpl());
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
            bs.setLateStatus();
            List<Integer> update;
            Booking change = new Booking();
            User user =new User();
            
            switch(request.getParameter("selection")){
                case "select":
                    update = (List<Integer>)request.getSession(false).getAttribute("cart");
                    update.add(Integer.parseInt(request.getParameter("room")));
                    request.getSession(false).setAttribute("cart", update); 
                    user.setId(Integer.parseInt(request.getParameter("user")));
                break; 
                case "unselect":
                    update = (List<Integer>)request.getSession(false).getAttribute("cart");
                    update.remove(Integer.valueOf(request.getParameter("room")));
                    request.getSession(false).setAttribute("cart", update);
                    user.setId(Integer.parseInt(request.getParameter("user")));
                break;
            }
            change.setId(Integer.parseInt(request.getParameter("booking")));
            List<Room> changerooms = rs.adminOptions(change);
            request.setAttribute("user", user);
            request.setAttribute("rooms", changerooms);
            request.setAttribute("booking", Integer.parseInt(request.getParameter("booking")));
            request.getRequestDispatcher("editroom.jsp").forward(request, response);                
            
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
