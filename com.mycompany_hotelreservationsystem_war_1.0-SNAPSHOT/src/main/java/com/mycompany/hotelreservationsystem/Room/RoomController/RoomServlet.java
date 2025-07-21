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
@WebServlet(name = "RoomServlet", urlPatterns = {"/RoomServlet"})
public class RoomServlet extends HttpServlet {

    private final RoomService rs = new RoomServiceImpl(new RoomDaoImpl());
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
        bs.setLateStatus();
        if(request.getParameter("rooms")!=null){
            List<Integer> update;
            Booking change = new Booking();
            switch(request.getParameter("room")){
                case "adminselect":
                    update = (List<Integer>)request.getSession(false).getAttribute("cart");
                    update.add(Integer.parseInt(request.getParameter("id")));
                    request.getSession(false).setAttribute("cart", update);                    
                break; 
                case "adminunselect":
                    update = (List<Integer>)request.getSession(false).getAttribute("cart");
                    update.remove(Integer.valueOf(request.getParameter("id")));
                    request.getSession(false).setAttribute("cart", update);
                break;                
            }
            
            change.setId(Integer.parseInt(request.getParameter("booking")));
            List<Room> changerooms = rs.adminOptions(change);
            request.setAttribute("rooms", changerooms);
            request.setAttribute("booking", Integer.parseInt(request.getParameter("booking")));
            request.getRequestDispatcher("editroom.jsp").forward(request, response);
            
        }else{

        
        if(request.getParameter("selection")!=null){
            int i;
            List<Integer> buy;
            switch(request.getParameter("selection")){
                case "select":
                    buy = (List<Integer>)request.getSession(false).getAttribute("cart");
                    buy.add(Integer.parseInt(request.getParameter("room")));
                    request.getSession(false).setAttribute("cart", buy);
                    break;
                case "unselect":
                    buy = (List<Integer>)request.getSession(false).getAttribute("cart");
                    buy.remove(Integer.parseInt(request.getParameter("room")));
                    request.getSession(false).setAttribute("cart", buy);
                    break;
            }

        }
            List<Room> available = rs.availableRooms();
            List<Room> unavailable = rs.unavailableRooms();
            request.setAttribute("available", available);
            request.setAttribute("unavailable", unavailable);
            if(request.getParameter("user")!=null){
                User user = new User();
                user.setId(Integer.parseInt(request.getParameter("user")));
                User send = us.getUser(user);   
                request.setAttribute("person", send);
                request.getRequestDispatcher("adminadd.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("rooms.jsp").forward(request, response);  
            }            
        }

              
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
        
        doGet(request, response);
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
