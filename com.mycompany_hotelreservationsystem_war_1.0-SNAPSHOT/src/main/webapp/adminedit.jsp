<%-- 
    Document   : admitedit
    Created on : 23 Jun 2024, 3:27:06 PM
    Author     : User
--%>

<%@page import="com.mycompany.hotelreservationsystem.User.UserModel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Integer booking = (Integer)request.getAttribute("booking");
    User user = (User)request.getAttribute("user");
    String error = (String)request.getAttribute("error");
%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Booking</title>
    </head>
    <body>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit</title>
    </head>
    <body>
        <main>
           <form method="get" action="EditServlet">
            <label for="birthdaytime">check-in:</label>
            <input type="datetime-local" name="start">
            <label for="birthdaytime">check-out:</label>
            <input type="datetime-local" name="end">
            <input name="user" value="<%=user.getId()%>" hidden>
            <input name="booking" value="<%=booking%>" hidden>
            <input type="submit" value="time_edit" name="bookings">
        </form>
        <a href="EditServlet?bookings=room_edit&booking=<%=booking%>&user=<%=user.getId()%>">change room/s</a>
        <%if(error!=null){%>
        <div><%=error%></div>
        <%}%> 
        </main>

    </body>
    </body>
</html>
