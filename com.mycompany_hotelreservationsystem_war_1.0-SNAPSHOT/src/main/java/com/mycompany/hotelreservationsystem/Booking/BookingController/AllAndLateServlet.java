/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.hotelreservationsystem.Booking.BookingController;

import com.mycompany.hotelreservationsystem.Booking.BookingDao.BookingDaoImpl;
import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingService;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingServiceImpl;
import com.mycompany.hotelreservationsystem.BookingPreference.BookingPreferenceDao.BookingPreferenceDaoImpl;
import com.mycompany.hotelreservationsystem.BookingPreference.BookingPreferenceService.BookingPreferenceService;
import com.mycompany.hotelreservationsystem.BookingPreference.BookingPreferenceService.BookingPreferenceServiceImpl;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomDao.BookingRoomDaoImpl;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomModel.BookingRoom;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomService.BookingRoomService;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomService.BookingRoomServiceImpl;
import com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryDao.BookingRoomHistoryDaoImpl;
import com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryService.BookingRoomHistoryService;
import com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryService.BookingRoomHistoryServiceImpl;
import com.mycompany.hotelreservationsystem.Preference.PreferenceDao.PreferenceDaoImpl;
import com.mycompany.hotelreservationsystem.Preference.PreferenceService.PreferenceService;
import com.mycompany.hotelreservationsystem.Preference.PreferenceService.PreferenceServiceImpl;
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
@WebServlet(name = "AllAndLateServlet", urlPatterns = {"/AllAndLateServlet"})
public class AllAndLateServlet extends HttpServlet {

    private final BookingService bs = new BookingServiceImpl(new BookingDaoImpl());
    private final BookingPreferenceService bps = new BookingPreferenceServiceImpl(new BookingPreferenceDaoImpl());
    private final RoomService rs = new RoomServiceImpl(new RoomDaoImpl());
    private final BookingRoomService b = new BookingRoomServiceImpl(new BookingRoomDaoImpl());
    private final PreferenceService ps = new PreferenceServiceImpl(new PreferenceDaoImpl());
    private final BookingRoomHistoryService brhs = new BookingRoomHistoryServiceImpl(new BookingRoomHistoryDaoImpl()); 
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
        switch(request.getParameter("bookings")){
            
            case "all":
                request.setAttribute("bookings", bs.getBookings());
                request.getRequestDispatcher("bookings.jsp").forward(request, response);
                break;
            case "late":
                request.setAttribute("latebookings", bs.getLateBookings());
                request.getRequestDispatcher("late.jsp").forward(request, response);
                break;
            case "cancel late":
                
                Booking newBooking = new Booking();
                newBooking.setId(Integer.parseInt(request.getParameter("booking")));
                List<Room> bookingRooms = rs.getRoom(newBooking);
                BookingRoom brs = new BookingRoom();
                if(bs.cancelBooking(newBooking)){
                    for(Room newroom:bookingRooms){
                        newroom.setStatus("available");
                        rs.updateStatus(newroom);
                        brs.setBooking(Integer.parseInt(request.getParameter("booking")));
                        brs.setRoom(newroom.getId());
                        b.removeBookingRoom(brs); 
                    } 
                    System.out.println("here");
                }
                
                List<Booking> contained = bs.getLateBookings();
                request.setAttribute("latebookings", contained);
                request.getRequestDispatcher("late.jsp").forward(request, response);                 
                break;
            case "cancel":
                
                Booking oldBooking = new Booking();
                oldBooking.setId(Integer.parseInt(request.getParameter("booking")));
                List<Room> freeRooms = rs.getRoom(oldBooking);
                BookingRoom temp = new BookingRoom();
                if(bs.cancelBooking(oldBooking)){
                    for(Room newroom:freeRooms){
                        newroom.setStatus("available");
                        rs.updateStatus(newroom);
                        temp.setBooking(Integer.parseInt(request.getParameter("booking")));
                        temp.setRoom(newroom.getId());
                        b.removeBookingRoom(temp); 
                    } 
                    System.out.println("here");
                }
                
                List<Booking> bookings = bs.getBookings();
                request.setAttribute("bookings", bookings);
                request.getRequestDispatcher("bookings.jsp").forward(request, response);                 
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
