<%@page import="com.mycompany.hotelreservationsystem.User.UserModel.User"%>
<%@page import="com.mycompany.hotelreservationsystem.Preference.PreferenceModel.Preference"%>
<%@page import="com.mycompany.hotelreservationsystem.Room.RoomModel.Room"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.hotelreservationsystem.Booking.BookingModel.Booking"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Booking booking = (Booking) request.getAttribute("details");
    List<Preference> preferences = (List<Preference>) request.getAttribute("preferences");
    List<Room> rooms = (List<Room>) request.getAttribute("booked");
    User user = (User) request.getSession(false).getAttribute("user");
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
        }

        header {
            height: 100px;
            width: 100%;
            background-color: #333;
            opacity: 1;
            display: flex;
            align-items: center;
            justify-content: space-between;
            position: fixed;
            z-index: 3;
        }

        .list {
            list-style: none;
            display: flex;
            margin-right: 40px;
            letter-spacing: 2px;
            align-items: center;
        }

        .items:not(:last-child) {
            margin-right: 70px;
        }

        .anchor:link,
        .anchor:visited {
            text-decoration: none;
            color: #fff;
            font-size: 18px;
        }

        .anchor:hover {
            color: #5cb85c;
        }

        .heading {
            color: #fff;
            font-size: 40px;
            margin-left: 30px;
        }

        form {
            padding: 10px;
        }

        .container-2 {}

        main {
            width: 100%;
            height: 100vh;
            display: flex;
            align-items: flex-start;
            justify-content: center;
            padding-top: 100px;
            position: relative;
        }

        .scroll-section {
            margin-top: 130px;
            width: 70%;
            height: 430px;
            padding: 0 2px;
        }

        .room-list {
            list-style: none;
            display: flex;
            flex-direction: column;
            padding: 0;
            overflow-y: scroll;
            height: 430px;
        }

        .scrollable-content::-webkit-scrollbar {
            display: none;
        }

        .item:not(:last-child) {
            margin-bottom: 24px;
            width: 100%;
        }

        .item {
            position: relative;
            z-index: 12;
        }

        .room {
            display: block;
            padding: 60px 230px 60px 40px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            text-decoration: none;
            background-color: #fff;
            opacity: 0.9;
        }

        .availability {
            text-align: center;
            margin: 60px;
            color: white;
            z-index: 4;
            opacity: 1;
            background: none;
        }

        .selection {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            text-decoration: none;
            padding: 7px 15px;
            letter-spacing: 4px;
            font-weight: 600;
            font-size: 18px;
            opacity: 1;
            color: #000;
            background-color: #5cb85c;
        }

        .book {
            text-decoration: none;
            display: inline-block;
            border-radius: 20px;
            letter-spacing: 4px;
            font-weight: 600;
            font-size: 18px;
            position: fixed;
            z-index: 40;
            right: 40px;
            bottom: 100px;
            opacity: 1;
            padding: 10px;
            background-color: #5cb85c;
            border-radius: 5px;
        }

        span {
            color: black;
        }

        .div {
            color: black;
            font-size: 18px;
        }
        
        .center{
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
        }
    </style>
</head>
<body>

<div class="container">
    <header>
        <div class="heading">
            LLH
        </div>
        <ul class="list">
            <% if (user == null) { %>
                <li><a href="register.jsp">Register</a></li>
                <li><a href="login.jsp">Login</a></li>
            <% } else if (user != null && user.getType().equals("admin")) { %>
                <li class="items"><a class="anchor" href="HomeServlet">Home</a></li>
                <li class="items"><a class="anchor" href="AllAndLateServlet?bookings=all">Bookings</a></li>
                <li class="items"><a class="anchor" href="AllAndLateServlet?bookings=late">Late Bookings</a></li>
                <li class="items"><a class="anchor" href="LogoutServlet?action=get">Logout</a></li>
            <% } else if (user != null && user.getType().equals("user")) { %>
                <li class="items"><a class="anchor" href="HomeServlet">Home</a></li>
                <li class="items"><a class="anchor" href="BookingsServlet?bookings=track">Track</a></li>
                <li class="items"><a class="anchor" href="BookingsServlet?bookings=past">History</a></li>
                <li class="items"><a class="anchor" href="BookingsServlet?bookings=upcoming">Upcoming</a></li>
                <li class="items"><a class="anchor" href="LogoutServlet?action=get">Logout</a></li>
            <% } %>
        </ul>
    </header>
    <main>
        <div class="center">
        <div class="booking">
            <h1>Booking</h1>
            <div>Cost: <%= booking.getCost() %></div>
            <div class="from">From: <%= booking.getStart().toString().substring(0,16) %></div>
            <div class="to">To: <%= booking.getEnd().toString().substring(0,16) %></div>
            <div class="to">Status: <%= booking.getStatus() %></div>
            <div class="">Creation time: <%= booking.getCreate().toString().substring(0,16) %></div>
        </div>
        <div class="rooms">
            <h1>Rooms</h1>
            <% for (Room room : rooms) { %>
                <div><span>Room number :</span> <%= room.getId() %></div>
            <% } %>
        </div>

        <% if (preferences != null) { %>
            <h1 class="pre">Preferences</h1>
            <% for (Preference pre : preferences) { %>
                <div class="preferences"><%= pre.getChoice() %></div>
            <% } %>
        <% } else { %>
            <div class="preferences">
                No Preferences
            </div>
        <% } %>            
        </div>

    </main>
</div>

</body>
</html>
