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
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomDao.BookingRoomDaoImpl;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomModel.BookingRoom;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomService.BookingRoomService;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomService.BookingRoomServiceImpl;
import com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryDao.BookingRoomHistoryDaoImpl;
import com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryService.BookingRoomHistoryService;
import com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryService.BookingRoomHistoryServiceImpl;
import com.mycompany.hotelreservationsystem.Preference.PreferenceDao.PreferenceDaoImpl;
import com.mycompany.hotelreservationsystem.Preference.PreferenceModel.Preference;
import com.mycompany.hotelreservationsystem.Preference.PreferenceService.PreferenceService;
import com.mycompany.hotelreservationsystem.Preference.PreferenceService.PreferenceServiceImpl;
import com.mycompany.hotelreservationsystem.Room.RoomDao.RoomDaoImpl;
import com.mycompany.hotelreservationsystem.Room.RoomModel.Room;
import com.mycompany.hotelreservationsystem.Room.RoomService.RoomService;
import com.mycompany.hotelreservationsystem.Room.RoomService.RoomServiceImpl;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import java.io.IOException;
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
@WebServlet(name = "BookingsServlet", urlPatterns = {"/BookingsServlet"})
public class BookingsServlet extends HttpServlet {
    
     private final BookingService bs = new BookingServiceImpl(new BookingDaoImpl());
     private final RoomService rs = new RoomServiceImpl(new RoomDaoImpl());
     private final BookingRoomHistoryService brhs = new BookingRoomHistoryServiceImpl(new BookingRoomHistoryDaoImpl());
     private final PreferenceService ps = new PreferenceServiceImpl(new PreferenceDaoImpl());
     private final BookingRoomService b = new BookingRoomServiceImpl(new BookingRoomDaoImpl());
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
        
        User user = (User)request.getSession(false).getAttribute("user");
        bs.setLateStatus();
        switch(request.getParameter("bookings")){
            
            case "track":
                List<Booking> books = bs.getUserBookings(user);
                request.setAttribute("bookings", books);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);                
            break;
            case "cancel":
                
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
                }
                
                List<Booking> contained = bs.getUserBookings(user);
                request.setAttribute("bookings", contained);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);                   
            break;
            case "past":
                List<Booking> history = bs.getHistory(user);
                request.setAttribute("history", history);
                request.getRequestDispatcher("history.jsp").forward(request, response);
            break;
            
            case "upcoming":
                
                List<Booking> upcoming = bs.getUpcoming(user);
                request.setAttribute("upcoming", upcoming);
                request.getRequestDispatcher("upcoming.jsp").forward(request, response);                 
            break;
            
            case "checkout":
                
                Booking payBooking = new Booking();
                payBooking.setId(Integer.parseInt(request.getParameter("booking")));
                if(bs.getDetails(payBooking).getStatus().equals("late")){
                    List<Booking> back = bs.getHistory(user);
                    request.setAttribute("history", back);
                    request.getRequestDispatcher("history.jsp").forward(request, response);                 
                }else{
                    List<Room>costroom = rs.getRoom(payBooking);
                    payBooking = bs.getDetails(payBooking);
                    request.setAttribute("rooms",costroom);
                    request.setAttribute("booking",payBooking);
                    request.getRequestDispatcher("checkout.jsp").forward(request, response);
                }    
                
            break;
            case "confirm edit":
                Booking bookingedit = new Booking();
                bookingedit.setId(Integer.parseInt(request.getParameter("booking")));
                User buser = new User();
                Booking bookuser = bs.getDetails(bookingedit);
                bookingedit.setStatus(bookuser.getStatus());
                buser.setId(bookuser.getUser());
                boolean confirm = rs.setStatus(bookingedit);
                List<Integer> n = (List<Integer>)request.getSession(false).getAttribute("cart");
                
                b.clearForBooking(bookingedit);
                int sum =0;
                BookingRoom bookingroom = new BookingRoom();
                if(confirm){
                    long hours =(bookuser.getEnd().getTime()-bookuser.getStart().getTime())/(60*60*1000);
                    for(Integer i:n){
                        Room rm= new Room();
                        rm.setStatus("taken");
                        rm.setId((int)i);
                        rs.updateStatus(rm);
                        sum+=rs.getRoom(rm).getRate();
                        bookingroom.setBooking(bookuser.getId());
                        bookingroom.setRoom(i);
                        b.addBookingRoom(bookingroom);     
                    }
                    int cost =(int) hours*sum;
                    bookuser.setCost(cost);
                    bs.changeCost(bookuser);
                    request.getSession(false).setAttribute("cart",new ArrayList<Integer>());
                }
                request.setAttribute("user", buser);
                request.setAttribute("bookings", bs.getUserBookings(buser));
                request.getRequestDispatcher("adminbooking.jsp").forward(request, response);            

            default :
                Booking b = new Booking();
                b.setId(Integer.parseInt(request.getParameter("bookings")));
                Booking details = bs.getDetails(b);
                List<Preference> pre = ps.getBookingPreferences(b);
                List<Room> bookedrooms = rs.getRooms(b);
                request.setAttribute("details", details);
                request.setAttribute("preferences",pre);
                request.setAttribute("booked",bookedrooms);
                System.out.println(pre);
                System.out.println(bookedrooms);
                request.getRequestDispatcher("bookingdetails.jsp").forward(request, response);                
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
