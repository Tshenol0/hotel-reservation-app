<%-- 
    Document   : home
    Created on : Jun 21, 2024, 9:12:37 AM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


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

            .container {
                margin: 0 auto;
                height: 100vh;
                background-image: linear-gradient(to right, rgba(240, 240, 240,0.3) , rgba(240, 240, 240,0.3)),url('images/hotel.jpg');
                background-size: cover;
                position: relative;
            }
            
            header {
                height: 100px;
                width: 100%;
                background-color: black;
                opacity: 1;
                display: flex;
                align-items: center;
                justify-content: space-between;
                background-color: #343a40;
                
            }
            
            
            
            .list{
                list-style: none;
                display: flex;
                margin-right: 40px;
                letter-spacing: 2px;
            }
            
            .item:not(:last-child){
                margin-right: 70px;
            }
            
            .anchor:link,.anchor:visited{
                text-decoration: none;
                color: #fff;
                font-size: 18px;
            }
            
            .heading{
                color: #fff;
                font-size: 40px;
                margin-left: 30px;
            }
            
            .content{
                position: absolute;
                width: 80%;
                height: 300px;
                
                transform: translate(10%,40%);
            }
            
            h1{
                font-size: 70px;
                color: white;
                text-align: center;
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
                    <li class="item"><a class="anchor" href="register.jsp">Register</a></li>
                    <li class="item"><a class="anchor" href="login.jsp">Login</a></li>
                    
                </ul>
            </header>
            <div class="content">
                <h1>Best place to stay at.</h1>
            </div>
        </div>


    </body>
</html>
