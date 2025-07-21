/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.hotelreservationsystem.Room.RoomController;

import com.mycompany.hotelreservationsystem.Booking.BookingDao.BookingDaoImpl;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingService;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingServiceImpl;
import com.mycompany.hotelreservationsystem.Room.RoomDao.RoomDaoImpl;
import com.mycompany.hotelreservationsystem.Room.RoomModel.Room;
import com.mycompany.hotelreservationsystem.Room.RoomService.RoomService;
import com.mycompany.hotelreservationsystem.Room.RoomService.RoomServiceImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Train
 */
@WebServlet(name = "RoomSelectServlet", urlPatterns = {"/RoomSelectServlet"})
public class RoomSelectServlet extends HttpServlet {

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
            List<Integer> buy;
            switch(request.getParameter("selection")){
                case "select":
                   
                    buy = (List<Integer>)request.getSession(false).getAttribute("cart");
                    buy.add(Integer.parseInt(request.getParameter("room")));
                    request.getSession(false).setAttribute("cart", buy);
                    break;
                case "unselect":
                    buy = (List<Integer>)request.getSession(false).getAttribute("cart");
                    buy.remove(Integer.valueOf(request.getParameter("room")));
                    request.getSession(false).setAttribute("cart", buy);
                    break;
            }
            List<Room> available = rs.availableRooms();
            List<Room> unavailable = rs.unavailableRooms();
            request.setAttribute("available", available);
            request.setAttribute("unavailable", unavailable);    
            request.getRequestDispatcher("home.jsp").forward(request, response);
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
