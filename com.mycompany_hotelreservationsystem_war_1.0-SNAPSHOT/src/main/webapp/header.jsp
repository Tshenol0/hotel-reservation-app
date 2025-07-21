<%-- 
    Document   : header
    Created on : Jun 21, 2024, 9:15:18 AM
    Author     : Train
--%>

<%@page import="com.mycompany.hotelreservationsystem.User.UserModel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User)request.getSession(false).getAttribute("user");
%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        
        body{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            overflow-x: hidden;
        }    
            
        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            height: 70px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            z-index: 40;
            width: 100%;
        }
        
        nav {
            margin-right: 40px;
            
        }

        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        nav ul li {
            display: inline;
            margin-right: 10px;
        }
        
        
        nav ul li:not(:last-child) {

            margin-right: 40px;
        }

        nav ul li a:link,nav ul li a:visited {
            color: #fff; 
            letter-spacing: 5px;
            font-weight: 600;
            text-decoration: none;
        }

        nav ul li a:hover {
            text-decoration: underline;
        }
        
        .fixed{
            position: fixed;
            top: 0;
        }
        
        </style>
        <script>
                window.onbeforeunload = function () {
                sessionStorage.setItem("scroll", window.scrollY);
                };

                window.onload = function () {
                    var scrollPosition = sessionStorage.getItem("scroll");
                    if (scrollPosition !== null) {
                    window.scrollTo(0, scrollPosition);
                    }
                };
                
        window.addEventListener('scroll', function () {
            const header = document.getElementById('navbar');
            if (window.scrollY > 60) {
                header.classList.add('fixed');
            } else {
                header.classList.remove('fixed');
            }
        });                
        </script>    
    </head>
    <body>
    <header id="navbar">
        <h1>LLH</h1>
        <nav>
            <ul>
                <%if(user==null){%>
                    <li><a href="register.jsp">Register</a></li>
                    <li><a href="login.jsp">Login</a></li>
                    
                    <%}else if(user!=null && user.getType().equals("admin")){%>
                        
                        <li><a href="UsersServlet">Home</a></li>
                        <li><a href="AllAndLateServlet?bookings=all">Bookings</a></li>
                        <li><a href="AllAndLateServlet?bookings=late">Late Bookings</a></li>
                        <li><a href="LogoutServlet?action=get">Logout</a></li>
                        
                        
                        <%}else if(user!=null && user.getType().equals("user")){%>
                        <li><a href="HomeServlet">Home</a></li>
                        <li><a href="BookingsServlet?bookings=track">Track</a></li>
                        <li><a href="BookingsServlet?bookings=past">History</a></li>
                        <li><a href="BookingsServlet?bookings=upcoming">Upcoming</a></li>
                        <li><a href="LogoutServlet?action=get">Logout</a></li>                        
                    <%}%>    
             
                

            </ul>
        </nav>
    </header>
    </body>
</html>
