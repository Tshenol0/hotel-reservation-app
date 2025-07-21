/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelreservationsystem.Email.EmailService;

import com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking;
import com.mycompany.hotelreservationsystem.Email.EmailModel.Email;
import com.mycompany.hotelreservationsystem.TokenGenerator.TokenGenerator;
import com.mycompany.hotelreservationsystem.User.UserModel.User;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


/**
 *
 * @author Train
 */
public class EmailServiceImpl implements EmailService{

    private Email email;
    
    public EmailServiceImpl(Email email){
        this.email = email;
    }
    
    public void sendInvoice(Booking booking,User user){
        
        final String USERNAME = email.getSender();
        final String PASSWORD = email.getPassword();
        String reciever = email.getReciever();
        String subject = email.getSubject();
        String content = email.getMessage();

        Properties props = new Properties();
        
        
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reciever));
            message.setSubject(subject);
            String htmlContent = "<html>"
                    + "<head>"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; border: solid #000; padding: 2px 5px;}"
                    +"div{margin-bottom: 4px;}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div >"
                    +"<h1>Invoice</h1>"
                    +"<div><span>To : </span>"+user.getSurname()+" "+user.getName()+"</div>"
                    +"<div><span>Time of booking : </span>"+booking.getCreate().toString().substring(0,16)+"</div>"
                    +"<div><span>Checkin time : </span>"+booking.getStart().toString().substring(0,16)+"</div>"
                    +"<div><span>Checkout time : </span>"+booking.getEnd().toString().substring(0,16)+"</div>"
                    +"<div><span>Status : </span>"+booking.getStatus()+"</div>"
                    +"<div><span>Total : </span>"+"R "+booking.getCost()+".00"+"</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";
                    
            message.setContent(htmlContent, "text/html");
           

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String verification(User user) {
         
        String token = TokenGenerator.generateToken(4);
        final String USERNAME = email.getSender();
        final String PASSWORD = email.getPassword();
        String reciever = email.getReciever();
        String subject = email.getSubject();
        String content = email.getMessage();

        Properties props = new Properties();
        
        
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reciever));
            message.setSubject(subject);
            String htmlContent = "<html>"
                               +"<head>"
                               +"<style>"
                               +".link-button {"
                               +"background: none;"+
                               "color: blue;"+
                                "border: none;"+
                                "padding: 0;"+
                                "font: inherit;"+
                                "cursor: pointer;"+
                                    "text-decoration: none;"+
                                    "}"+

                                  ".link-button:hover {"+
                             "color: darkblue;"+
                                    "}"
                               +"</style>"
                               +"</head>"
                               + "<body>"
                               + "<div>"+token+"<div>"
                               + "<form action='http://localhost:8080/hotelreservationsystem/VerifyServlet' method='post'>"
                               +"<input type='hidden'  value="+ user.getName()+" name='name' >"
                               +"<input type='hidden'  value="+ user.getSurname()+" name='surname'>"
                               +"<input type='hidden'  value="+ user.getEmail()+" name='email'>"
                               +"<input type='hidden'  value="+ user.getPassword()+ " name='password' >"
                                +"<input type='hidden'  value="+ token+ " name='token' >"
                               +"<input type='submit' value='verify' name='submit' class='link-button'>"
                               +"</form>"
                               + "</body>"
                               + "</html>";
                    
            message.setContent(htmlContent, "text/html");
           

            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public void sendMail() {
        final String USERNAME = email.getSender();
        final String PASSWORD = email.getPassword();
        String reciever = email.getReciever();
        String subject = email.getSubject();
        String content = email.getMessage();

        Properties props = new Properties();
        
        
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reciever));
            message.setSubject(subject);
            String htmlContent = "<html>"
                    + "<head>"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; }"
                    + "div{color:red;"
                    + "font-weight:bold;"
                    + "font-size:30px"
                    + "}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div >"
                    +"<h1>Registration</h1>"
                    +content
                    + "</div>"
                    + "</body>"
                    + "</html>";
                    
            message.setContent(htmlContent, "text/html");
           

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
}
