<%-- 
    Document   : users
    Created on : 23 Jun 2024, 6:49:25 AM
    Author     : User
--%>

<%@page import="com.mycompany.hotelreservationsystem.User.UserModel.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<User> users = (List<User>)request.getAttribute("users");
    User user = (User)request.getSession(false).getAttribute("user");    
%>    
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
                background-color: #f0f0f0;
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
            padding: 60px 20px 60px 20px;
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
                   <% if (users != null && users.size() > 0) { %>
                   <% for (User use : users) { %>
                   <li class="item">
                    <a href="UserDetailServlet?user=<%=user.getId()%>" class="room">
                        <div class="div div"><span>Name : </span><%=use.getName() %> </div>
                        <div class="div"><span>Surname : </span> <%= use.getSurname() %></div>
                        <div class="div"><span>Email : </span><%=use.getEmail()%></div> 
                    </a>
                   </li>
                   <%}}%>
                  
            </ul>                 
            </div>

        </main>
    </body>
    
</html>
