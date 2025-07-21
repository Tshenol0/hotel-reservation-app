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
import com.mycompany.hotelreservationsystem.BookingPreference.BookingPreferenceModel.BookingPreference;
import com.mycompany.hotelreservationsystem.BookingPreference.BookingPreferenceService.BookingPreferenceService;
import com.mycompany.hotelreservationsystem.BookingPreference.BookingPreferenceService.BookingPreferenceServiceImpl;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomDao.BookingRoomDaoImpl;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomModel.BookingRoom;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomService.BookingRoomService;
import com.mycompany.hotelreservationsystem.BookingRoom.BookingRoomService.BookingRoomServiceImpl;
import com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryDao.BookingRoomHistoryDaoImpl;
import com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryModel.BookingRoomHistory;
import com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryService.BookingRoomHistoryService;
import com.mycompany.hotelreservationsystem.BookingRoomHistory.BookingRoomHistoryService.BookingRoomHistoryServiceImpl;
import com.mycompany.hotelreservationsystem.Email.EmailModel.Email;
import com.mycompany.hotelreservationsystem.Email.EmailService.EmailServiceImpl;
import com.mycompany.hotelreservationsystem.Exceptions.DurationException;
import com.mycompany.hotelreservationsystem.Exceptions.InvalidDateException;
import com.mycompany.hotelreservationsystem.Exceptions.SameTimeException;
import com.mycompany.hotelreservationsystem.Exceptions.StartEndDateException;
import com.mycompany.hotelreservationsystem.Preference.PreferenceDao.PreferenceDaoImpl;
import com.mycompany.hotelreservationsystem.Preference.PreferenceService.PreferenceService;
import com.mycompany.hotelreservationsystem.Preference.PreferenceService.PreferenceServiceImpl;
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
 * @author Train
 */
@WebServlet(name = "AddBookingServlet", urlPatterns = {"/AddBookingServlet"})
public class AddBookingServlet extends HttpServlet {

    private final BookingService bs = new BookingServiceImpl(new BookingDaoImpl());
    private final BookingPreferenceService bps = new BookingPreferenceServiceImpl(new BookingPreferenceDaoImpl());
    private final RoomService rs = new RoomServiceImpl(new RoomDaoImpl());
    private final BookingRoomService brs = new BookingRoomServiceImpl(new BookingRoomDaoImpl());
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
        bs.setLateStatus();
        User user = (User)request.getSession(false).getAttribute("user");
        List<Integer> room_num = (List<Integer>)request.getSession(false).getAttribute("cart");
        List<Room> rooms = new ArrayList<>();
        Room temp = new Room();
        int sum=0;
        for(Integer per:room_num){
            temp.setId(per);
            rooms.add(rs.getRoom(temp));
            sum+=rs.getRoom(temp).getRate();
        }
           
        Booking booking = new Booking(),book = null;
        booking.setStart(Timestamp.valueOf(request.getParameter("start").replace('/', '-').replace('T', ' ')+":00"));
        booking.setEnd(Timestamp.valueOf(request.getParameter("end").replace('/', '-').replace('T', ' ')+":00"));
        booking.setUser(user.getId());
        System.out.println(user.getId());
        long hours =(Timestamp.valueOf(request.getParameter("end").replace('/', '-').replace('T', ' ')+":00").getTime()- Timestamp.valueOf(request.getParameter("start").replace('/', '-').replace('T', ' ')+":00").getTime())/(60*60*1000);

        int cost =(int) hours*sum;
        booking.setCost(cost);
        boolean booked=false;
        String numbers="";
        try {
            
            booked = bs.addBooking(booking);
        } catch (InvalidDateException | StartEndDateException | SameTimeException | DurationException ex) {
            request.setAttribute("error", ex.getMessage());
                Room payRoom = new Room();
                List<Integer> roomToBook = (List<Integer>)request.getSession(false).getAttribute("cart");
                List<Room> cartRooms = new ArrayList<>();
              
                for(Integer toBook:roomToBook){
                    payRoom.setId(toBook);
                    cartRooms.add(rs.getRoom(payRoom));
                }
                
                request.setAttribute("tobook", cartRooms);
                request.setAttribute("choices", ps.getPreferences());            
            request.getRequestDispatcher("makebooking.jsp").forward(request, response);
        }
        String[] choices;
        BookingRoom bk = new BookingRoom();
        BookingRoomHistory bkh = new BookingRoomHistory();
        
        
        if(booked){
                book = bs.getBooking(booking);
                
                for(Integer item:room_num){   
                    bk.setBooking(book.getId());
                    bk.setRoom(item);
                    temp.setId(item);
                    temp.setStatus("taken");
                    bkh.setRoom(item);
                    bkh.setBooking(book.getId());
                    rs.updateStatus(temp);
                    brs.addBookingRoom(bk); 
                    brhs.addHistory(bkh);
                    
                }
                
                choices = request.getParameterValues("preferences");
                if(choices!=null){
                    for(String s:choices){
                    BookingPreference spec = new BookingPreference(book.getId(),Integer.parseInt(s));
                        bps.specify(spec); 
                    }    
                }
                User individual = new User();  
                List<Booking> bookings =bs.getUserBookings(user);
                
                Email mail = new Email("theminister07@gmail.com", user.getEmail(), "Succesfully booked", "ukpt alkb ncuc wgqh", "Invoice");
                EmailServiceImpl es = new EmailServiceImpl(mail);
                es.sendInvoice(book,user);
                
                room_num.clear();
                request.getSession(false).setAttribute("cart",room_num);
                request.setAttribute("bookings", bookings);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
      
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
