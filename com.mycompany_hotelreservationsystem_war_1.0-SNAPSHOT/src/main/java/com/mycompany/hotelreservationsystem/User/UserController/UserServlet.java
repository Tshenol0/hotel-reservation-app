/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.User.UserController;


import com.mycompany.hotelreservationsystem.Exceptions.LoginException;
import com.mycompany.hotelreservationsystem.Exceptions.RegistrationException;
import com.mycompany.hotelreservationsystem.User.UserDao.UserDaoImpl;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import com.mycompany.hotelreservationsystem.User.UserService.UserService;
import com.mycompany.hotelreservationsystem.User.UserService.UserServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {
    
    private final UserService us = new UserServiceImpl(new UserDaoImpl());

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
        
            switch(request.getParameter("submit")){
                
                case "verify":
                    String name = request.getParameter("name").trim();
                    String surname = request.getParameter("surname").trim();
                    String email = request.getParameter("email").trim();
                    String password = request.getParameter("password").trim();
                    User user = new User();
                    user.setEmail(email);
                    user.setSurname(surname);
                    user.setPassword(password);
                    user.setName(name);
                    HttpSession anothersession = request.getSession(false);
                    System.out.println(anothersession);
                    System.out.println(anothersession.getAttribute("token"));
                    if(anothersession !=null ){
                        Object sessionTokenObj = anothersession.getAttribute("token");
                        String sessionToken = (sessionTokenObj != null) ? sessionTokenObj.toString() : null;
                        String requestToken = request.getParameter("token");
                        if (!requestToken.equals(sessionToken)) {
                            request.getRequestDispatcher("register.jsp").forward(request, response);
                        }else{
                          try {
                                us.register(user);
                                request.getRequestDispatcher("login.jsp").forward(request, response);
                            } catch (RegistrationException ex) {
                                request.setAttribute("error", ex.getMessage().toUpperCase());
                                request.getRequestDispatcher("register.jsp").forward(request, response);
                            }
               
                        }
                    }else{
                         request.getRequestDispatcher("tokenexpired.jsp").forward(request, response);
                    } 
                    break;

                case "login":
                    String email1 = request.getParameter("email");
                    String password1 = request.getParameter("password");
                    User user1 = new User();
                    user1.setEmail(email1);
                    user1.setPassword(password1);
                    User u;
                    try {
                        u = us.login(user1);
                        HttpSession session = request.getSession();
                        session.setAttribute("user", u);
                        session.setAttribute("cart", new ArrayList<Integer>());
                        if(u.getType().equals("admin")){
                            request.getRequestDispatcher("/UsersServlet").forward(request, response); 
                        }else if(u.getType().equals("user")){
                            request.getRequestDispatcher("/HomeServlet").forward(request, response);                            
                        }

                    } catch (LoginException ex) {
                        request.setAttribute("error", ex.getMessage().toUpperCase());
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                    //request.getRequestDispatcher("/RoomServlet").forward(request, response);
                    break;
                    
            }
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
