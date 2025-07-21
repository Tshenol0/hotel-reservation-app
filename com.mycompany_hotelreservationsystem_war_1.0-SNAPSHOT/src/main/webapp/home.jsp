<%-- 
    Document   : rooms.jsp
    Created on : 13 Jun 2024, 9:33:35 PM
    Author     : User
--%>

<%@page import="com.mycompany.hotelreservationsystem.User.UserModel.User"%>
<%@page import="com.mycompany.hotelreservationsystem.Room.RoomModel.Room"%>
<%@page import="java.util.List"%>

<%
  List<Room> available = (List<Room>) request.getAttribute("available");
  List<Room> unavailable = (List<Room>) request.getAttribute("unavailable");
  List<Integer> buy = (List<Integer>)request.getSession(false).getAttribute("cart");
  User user = (User)request.getSession(false).getAttribute("user");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page</title>
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
                background: url('images/bed.jpg');
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

        form{
     
            padding: 10px;            
        }

        .container-2 {   
            flex: 1;
            display: flex;
            align-items: center;
            justify-content: center;
         
        }
        
        .overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)); 
            z-index: 1;
        }

        main{
            width: 100%;
            height: 100vh;
            display: flex;
            align-items: flex-start;
            justify-content: center;
        }
        
        .scroll-section{
            margin-top: 130px;
            width: 70%;
            height: 430px;
            padding: 0 2px;
        }
        
        .room-list{
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
        
        .item:not(:last-child){
            margin-bottom: 24px;
            width: 100%;
        }
 
        .item{
            position: relative;
            z-index: 12;
        }
        
        .room{
            display: block;
            padding: 40px 230px 40px 40px;
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
            background:none;
            
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
        
        .book{
            text-decoration: none;
            display: inline-block;
            padding: 7px 15px;
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
        
        span{
            color: black;
        }
        
        .div{
            color: black;
            font-size: 18px;
            flex: 1;          
        }

        
    </style>
        <script type="text/javascript">
            
                window.onbeforeunload = function () {
                sessionStorage.setItem("scroll", window.scrollY);
                };

                window.onload = function () {
                    var scrollPosition = sessionStorage.getItem("scroll");
                    if (scrollPosition !== null) {
                    window.scrollTo(0, scrollPosition);
                    }
                };
        </script>    
    </head>
    <body>
        <div class="container">
            <div class="overlay"></div>    
        <header>
                <div class="heading">
                    LLH
                </div>
                <ul class="list">
                                    <%if(user==null){%>
                    <li><a href="register.jsp">Register</a></li>
                    <li><a href="login.jsp">Login</a></li>
                    
                    <%}else if(user!=null && user.getType().equals("admin")){%>
                        <li  class="items"><a class="anchor" href="HomeServlet">Home</a></li>
                        <li class="items"><a class="anchor" href="AllAndLateServlet?bookings=all">Bookings</a></li>
                        <li class="items"><a class="anchor" href="AllAndLateServlet?bookings=late">Late Bookings</a></li>
                        <li class="items"><a class="anchor" href="LogoutServlet?action=get">Logout</a></li>
                        
                        <%}else if(user!=null && user.getType().equals("user")){%>
                        <li  class="items"><a class="anchor" href="HomeServlet">Home</a></li>
                        <li class="items"><a class="anchor" href="BookingsServlet?bookings=track">Track</a></li>
                        <li class="items"><a class="anchor" href="BookingsServlet?bookings=past">History</a></li>
                        <li class="items"><a class="anchor" href="BookingsServlet?bookings=upcoming">Upcoming</a></li>
                        <li class="items"><a class="anchor" href="LogoutServlet?action=get">Logout</a></li>                        
                    <%}%> 
                </ul>
        </header>         
        <main>
            <div class="scroll-section">
            <ul class="room-list scrollable-content">
                <% if (available != null && available.size() > 0) { %>
                   <% for (Room room : available) { %>
                  
                   <li class="item">
                    <a href="RoomDetailServlet?rooms=<%=room.getId()%>" class="room">
                        <div class="div "><%= room.getType() %> <span>room</span></div>
                        <div class="div"><span>floor:</span> <%= room.getFloor() %></div>
                        <div class="div"><%=room.getStatus()%></div>
                     
                    </a>
                    <% if (!buy.contains(room.getId())) { %>    
                        <a class="selection select" href="RoomSelectServlet?selection=select&room=<%=room.getId() %>">select</a>
                        <% } else { %>
                        <a class="selection unselect" href="RoomSelectServlet?selection=unselect&room=<%=room.getId() %>">unselect</a>
                        <% } %>                           
                   </li>
                   <%}}else{%>
                   
                   <% for (Room room : unavailable) { %>
                   <li class="item">
                    <a href="RoomDetailServlet?rooms=<%=room.getId()%>" class="room">
                        <div class="div "><%= room.getType() %> <span>room</span></div>
                        <div class="div"><span>floor:</span> <%= room.getFloor() %></div>
                        <div class="div"><%=room.getStatus()%></div>
                       
                    </a>                   
                   <%}}%>
                   <%if(buy.size()>0){%>
                        <a href="BookingServlet" class="book">book now</a>
                    <%}%> 
                  
            </ul>                 
            </div>

        </main>
    </body>
    
</html>
