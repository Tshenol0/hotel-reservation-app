/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.Payment.PaymentController;

import com.mycompany.hotelreservationsystem.Booking.BookingDao.BookingDaoImpl;
import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingService;
import com.mycompany.hotelreservationsystem.Booking.BookingService.BookingServiceImpl;
import com.mycompany.hotelreservationsystem.Payment.PaymentStubImp.Payment;
import com.mycompany.hotelreservationsystem.Payment.PaymentStubImp.PaymentImpl;
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
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

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
                User user = (User)request.getSession(false).getAttribute("user");
                int toPay = Integer.parseInt(request.getParameter("cost"));
                String type = request.getParameter("option");
                Booking newer = new Booking();
                newer.setId(Integer.parseInt(request.getParameter("booking")));
                Payment pay = new PaymentImpl();
                boolean transact = type.equals("credit")?pay.creditPay(toPay):pay.debitPay(toPay);
                if(transact)bs.confirmBooking(newer);
                List<Booking> contain = bs.getUserBookings(user);
                request.setAttribute("bookings", contain);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
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
