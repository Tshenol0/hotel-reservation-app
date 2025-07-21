/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Booking.BookingController;

import com.mycompany.hotelreservationsystem.Booking.BookingDao.BookingDaoImpl;
import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingService;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingServiceImpl;
import com.mycompany.hotelreservationsystem.Exceptions.DurationException;
import com.mycompany.hotelreservationsystem.Exceptions.InvalidDateException;
import com.mycompany.hotelreservationsystem.Exceptions.SameTimeException;
import com.mycompany.hotelreservationsystem.Exceptions.StartEndDateException;
import com.mycompany.hotelreservationsystem.Room.RoomDao.RoomDaoImpl;
import com.mycompany.hotelreservationsystem.Room.RoomModel.Room;
import com.mycompany.hotelreservationsystem.Room.RoomService.RoomService;
import com.mycompany.hotelreservationsystem.Room.RoomService.RoomServiceImpl;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import java.io.IOException;
import java.sql.Timestamp;
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
@WebServlet(name = "EditServlet", urlPatterns = {"/EditServlet"})
public class EditServlet extends HttpServlet {

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
        User user = new User();
        switch(request.getParameter("bookings")){
            
            case "edit":
                request.setAttribute("booking", Integer.parseInt(request.getParameter("booking")));
                user.setId(Integer.parseInt(request.getParameter("user")));
                request.setAttribute("user", user);
                request.getRequestDispatcher("adminedit.jsp").forward(request, response);
            break;
            
            case "room_edit":
                request.setAttribute("booking",Integer.parseInt(request.getParameter("booking")));
                user.setId(Integer.parseInt(request.getParameter("user")));                
                Booking change = new Booking();
                change.setId(Integer.parseInt(request.getParameter("booking")));
                List<Room> changerooms = rs.getRooms(change);
                List<Integer> room_id = new ArrayList<>();
                for(Room room:changerooms){
                    room_id.add(room.getId());
                }
                request.setAttribute("user", user);
                request.setAttribute("rooms", rs.adminOptions(change));
                request.getSession(false).setAttribute("cart", room_id);
                request.getRequestDispatcher("editroom.jsp").forward(request, response);            
             break;
             
            case "time_edit":

                Booking booking = new Booking(),book = null;
                booking.setStart(Timestamp.valueOf(request.getParameter("start").replace('/', '-').replace('T', ' ')+":00"));
                booking.setEnd(Timestamp.valueOf(request.getParameter("end").replace('/', '-').replace('T', ' ')+":00"));
                booking.setUser(user.getId());
                booking.setId(Integer.parseInt(request.getParameter("booking")));
                
                boolean state = false;
                
                {
                    try {
                        state = bs.changeTime(booking);
                        long hours =(Timestamp.valueOf(request.getParameter("end").replace('/', '-').replace('T', ' ')+":00").getTime()- Timestamp.valueOf(request.getParameter("start").replace('/', '-').replace('T', ' ')+":00").getTime())/(60*60*1000);
                        List<Room> rooms = rs.getRooms(booking);
                        
                        int sum=0;
                        for(Room room:rooms){
                            sum+=room.getRate();
                        }
                        int cost =(int) hours*sum;
                       
                        booking.setCost(cost);
                        bs.changeCost(booking);
                        user.setId(Integer.parseInt(request.getParameter("user")));
                        request.setAttribute("user", user);
                        request.setAttribute("bookings", bs.getUserBookings(user));
                        request.getRequestDispatcher("adminbooking.jsp").forward(request, response);
                        
                    } catch (InvalidDateException | StartEndDateException | SameTimeException | DurationException ex) {
                        User returner = new User();
                        returner.setId(Integer.parseInt(request.getParameter("user")));
                        request.setAttribute("error", ex.getMessage());
                        request.setAttribute("user", returner);
                        request.setAttribute("booking",booking.getId());
                        request.getRequestDispatcher("adminedit.jsp").forward(request, response);
                    }
                }
                
            break; 
 
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
