<%-- 
    Document   : room
    Created on : 13 Jun 2024, 10:49:43 PM
    Author     : User
--%>

<%@page import="com.mycompany.hotelreservationsystem.Preference.PreferenceModel.Preference"%>
<%@page import="com.mycompany.hotelreservationsystem.Room.RoomModel.Room"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Room room = (Room)request.getAttribute("room");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room Details</title>
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
                    <li class="items"><a class="anchor" href="register.jsp">Register</a></li>
                    <li class="items"><a class="anchor" href="login.jsp">Login</a></li>
                    
                </ul>
        </header>
        <div class="overlay"></div>
        <div class="room">
            <div class="div1">
                <img class="room-img" src="images/room detail.jpg" alt="Room Image">
            </div>
            <div class="room-details">
                <h2>Room Number: <%= room.getId() %></h2>
                <div>Occupancy Limit: <%= room.getOccupants() %></div>
                <div>Floor: <%= room.getFloor() %></div>
                <div>Type: <%= room.getType() %></div>
                <div>Rate: R<%= room.getRate() %> per hour</div>
            </div>
        </div>
            
    </div>
</body>
</html>