/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Booking.BookingController;

import com.mycompany.hotelreservationsystem.Booking.BookingDao.BookingDaoImpl;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingService;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingServiceImpl;
import com.mycompany.hotelreservationsystem.Preference.PreferenceDao.PreferenceDaoImpl;
import com.mycompany.hotelreservationsystem.Preference.PreferenceService.PreferenceService;
import com.mycompany.hotelreservationsystem.Preference.PreferenceService.PreferenceServiceImpl;
import com.mycompany.hotelreservationsystem.Room.RoomDao.RoomDaoImpl;
import com.mycompany.hotelreservationsystem.Room.RoomModel.Room;
import com.mycompany.hotelreservationsystem.Room.RoomService.RoomService;
import com.mycompany.hotelreservationsystem.Room.RoomService.RoomServiceImpl;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
    
    private final RoomService rs = new RoomServiceImpl(new RoomDaoImpl());    
    private final PreferenceService ps = new PreferenceServiceImpl(new PreferenceDaoImpl()); 
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
                Room payRoom = new Room();
                List<Integer> roomToBook = (List<Integer>)request.getSession(false).getAttribute("cart");
                List<Room> cartRooms = new ArrayList<>();
                User user = new User();
                user.setId(Integer.parseInt(request.getParameter("user")));
               
                for(Integer toBook:roomToBook){
                    payRoom.setId(toBook);
                    cartRooms.add(rs.getRoom(payRoom));
                }
                request.setAttribute("user", user);
                request.setAttribute("tobook", cartRooms);
                request.setAttribute("choices", ps.getPreferences());
                request.getRequestDispatcher("adminmakebooking.jsp").forward(request, response);        
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
