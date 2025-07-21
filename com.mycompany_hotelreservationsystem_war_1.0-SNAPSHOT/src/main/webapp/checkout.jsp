<%-- 
    Document   : checkout.jsp
    Created on : 16 Jun 2024, 12:59:24 PM
    Author     : User
--%>

<%@page import="com.mycompany.hotelreservationsystem.Room.RoomModel.Room"%>
<%@page import="com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Booking booking = (Booking)request.getAttribute("booking");
    List<Room> rooms = (List<Room>)request.getAttribute("rooms");
%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        body {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;            
        }

        .container {
            max-width: 1350px;
            margin: 0 auto;
            height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .div{
            
            display: flex;
            align-items: center;
            justify-content: center;
            flex: 1;
        }
       
        h1 {
            text-align: center;
            color: #333333;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
            
        }

        label {
            margin-bottom: 8px;
            color: #666666;
        }

        select, input[type="text"], input[type="submit"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #cccccc;
            border-radius: 5px;
            font-size: 14px;
        }

        input[type="submit"] {
            background-color: #66cc66;
            color: #ffffff;
            cursor: pointer;
            font-weight: bold;
            border: none;
        }

        input[type="submit"]:hover {
            background-color: #5cb85c;
        }
        
        .to,.from{
            margin-bottom: 10px;
        }
        
        
        </style>
    </head>
    <body>
        <div class="container">
                 <jsp:include page="header.jsp" />
        <div class="div">
        <div class="div1">
                                       <h1>cost: <%=booking.getCost()%></h1>
        <div class="from">from :<%=booking.getStart().toString().substring(0,16)%></div>
        <div class="to">To :<%=booking.getEnd().toString().substring(0,16)%></div>
        <form method="post" action="PaymentServlet">
            <label for="paymentMethod">Payment Method:</label>
            <select id="paymentMethod" name="option">
                <option value="credit">Credit Card</option>
                <option value="debit">Debit Card</option>
            </select>
            
            <label for="cardNumber">Card Number:</label>
            <input type="text" id="cardNumber" name="cardNumber" required>
            
            <input type="hidden" value="<%=booking.getCost()%>" name="cost">
            <input type="hidden" value="<%=booking.getId()%>" name="booking">
            
            <input type="submit" name="bookings" value="pay">
        </form>

        </div>

    </body>
</html>
