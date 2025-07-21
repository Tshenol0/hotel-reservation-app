<%-- 
    Document   : editroom
    Created on : 23 Jun 2024, 3:56:30 PM
    Author     : User
--%>

<%@page import="com.mycompany.hotelreservationsystem.User.UserModel.User"%>
<%@page import="com.mycompany.hotelreservationsystem.Room.RoomModel.Room"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Room> rooms = (List<Room>)request.getSession(false).getAttribute("cart");
    Integer booking = (Integer)request.getAttribute("booking");
    List<Room> options = (List<Room>)request.getAttribute("rooms");
    User user = (User)request.getAttribute("user");
%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
        <%if(options!=null && options.size()>0){%>
        <%for(Room option:options){%>
        <div>
            <div>room number:<%=option.getId()%></div>
            <%if(rooms.contains(Integer.valueOf(option.getId()))){%>
            <a href="AdminEditSelectServlet?selection=unselect&room=<%=option.getId()%>&booking=<%=booking%>&user=<%=user.getId()%>">unselect</a><%}else{%>
            <a href="AdminEditSelectServlet?selection=select&room=<%=option.getId()%>&booking=<%=booking%>&user=<%=user.getId()%>">select</a><%}%>
        </div>
        <%}%>
        <%}%>
        <%if(rooms.size()>0){%>
        <a href="BookingsServlet?bookings=confirm edit&booking=<%=booking%>">comfirm change</a>
        <%}%>
    </body>
</html>
