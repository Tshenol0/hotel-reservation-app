<%-- 
    Document   : book.jsp
    Created on : Jun 18, 2024, 2:26:36 PM
    Author     : Train
--%>

<%@page import="com.mycompany.hotelreservationsystem.User.UserModel.User"%>
<%@page import="com.mycompany.hotelreservationsystem.Preference.PreferenceModel.Preference"%>
<%@page import="com.mycompany.hotelreservationsystem.Room.RoomModel.Room"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Room> buy = (List<Room>)request.getAttribute("tobook");
    List<Preference> pr = (List<Preference>)request.getAttribute("choices");
    String error = (String)request.getAttribute("error");
    User user = (User)request.getAttribute("user");
    User admin = (User)request.getSession(false).getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book</title>
    <style>
            body {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: Arial, sans-serif;

            }

            .container {
                margin: 0 auto;
                height: 100vh;
                background: url('images/frames.jpg');
                background-position: center;
                background-size: cover;
                background-repeat: no-repeat;
                display: flex;
                flex-direction: column;
                
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
             
            main{
                margin-top: 40px;
                margin-left: 40px;
                margin-right: 40px;
                opacity: 0.95;
            }
            
            .list{
                list-style: none;
                display: flex;
                margin-right: 40px;
                letter-spacing: 2px;
                align-items: center;
                
            }
            
            .items:not(:last-child){
                margin-right: 70px;
            }
            
            .anchor:link,.anchor:visited{
                text-decoration: none;
                color: #fff;
                font-size: 18px;
            }
            
            .anchor:hover {
                color: #5cb85c;
            }
            
            .heading{
                color: #fff;
                font-size: 40px;
                margin-left: 30px;
            }

        .header {
            margin-bottom: 20px;
        }

        .availability {
            text-align: center;
            margin-bottom: 30px;
        }

        .position {
            margin: 0 auto;
            padding: 20px;
            background-color: #f9f9f9; 
            border-radius: 5px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1); 
        }

        .form-flex {
            display: flex;
            justify-content: space-between;
        }

        .div1, .div2 {
            flex: 1;
            padding: 10px;
        }

        .div1 {
            margin-right: 20px;
            flex: 1;
        }

        .options {
            padding-left: 20px;
            opacity: 1;
        }

        .submit {
            padding: 10px 20px;
            background-color: #5cb85c;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-size: 18px;
            color: white;
            margin-top: 10px;
            opacity: 1;
           
        }

        .room {
            margin-top: 20px;
            padding: 10px;
            background-color: #e0e0e0;
            border-radius: 5px;
        }

        .error {
            text-align: center;
            font-size: 18px;
            color: red;
            font-weight: bold;
            margin-top: 20px;
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
                    <%if(user==null){%>
                    <li><a href="register.jsp">Register</a></li>
                    <li><a href="login.jsp">Login</a></li>
                    
                    <%}else if(admin!=null && admin.getType().equals("admin")){%>
                        <li  class="items"><a class="anchor" href="HomeServlet">Home</a></li>
                        <li class="items"><a class="anchor" href="AllAndLateServlet?bookings=all">Bookings</a></li>
                        <li class="items"><a class="anchor" href="AllAndLateServlet?bookings=late">Late Bookings</a></li>
                        <li class="items"><a class="anchor" href="LogoutServlet?action=get">Logout</a></li>
                        
                        <%}else if(admin!=null && admin.getType().equals("user")){%>
                        <li  class="items"><a class="anchor" href="HomeServlet">Home</a></li>
                        <li class="items"><a class="anchor" href="BookingsServlet?bookings=track">Track</a></li>
                        <li class="items"><a class="anchor" href="BookingsServlet?bookings=past">History</a></li>
                        <li class="items"><a class="anchor" href="BookingsServlet?bookings=upcoming">Upcoming</a></li>
                        <li class="items"><a class="anchor" href="LogoutServlet?action=get">Logout</a></li>                        
                    <%}%> 
                </ul>
        </header> 
        <main>
            <h1 class="availability">Book Room/s</h1>
            <div class="position">
                <form method="get" action="AddBookingServlet" class="form">
                    <div class="form-flex">
                        <div class="div1">
                            <h3>Duration</h3>
                            <div class="duration">
                                <label for="start">Check-in:</label>
                                <input type="datetime-local" id="start" name="start">
                                <label for="end">Check-out:</label>
                                <input type="datetime-local" id="end" name="end">
                            </div>
                            <input type="submit" class="submit" value="Book Room/s">
                        </div>
                        <div class="div2">
                            <h3>Choose Preference (Optional)</h3>
                            <div class="options">
                                <% for (Preference pre : pr) { %>
                                    <label>
                                        <input type="checkbox" name="preferences" value="<%= pre.getId() %>">
                                        <span><%= pre.getChoice() %></span>
                                    </label><br/>
                                <% } %>
                            </div>
                        </div>
                    </div>
                </form>
                <% for (Room room : buy) { %>
                    <div class="room">
                        Room Number: <%= room.getId() %>
                    </div>
                <% } %>
            </div>
            <% if (error != null) { %>
                <div class="error"><%= error %></div>
            <% } %>
        </main>
    </div>
</body>
</html>

