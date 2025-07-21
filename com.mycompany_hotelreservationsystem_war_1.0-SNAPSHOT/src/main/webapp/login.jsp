<%-- 
    Document   : register.jsp
    Created on : Jun 21, 2024, 9:27:40 AM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String str = (String)request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
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

        .form-container {
             
            background-color: #f0f0f0;
            width: 35%;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 16px;
            margin-bottom: 20px;

        }

        .form-container h2 {
            text-align: center;
            margin-bottom: 50px;
            
        }

        .form-group {
            margin-bottom: 10px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-group input[type="text"],
        .form-group input[type="password"],
        .form-group input[type="email"] {
            width: 100%; 
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            transition: border-color 0.3s;
            box-sizing: border-box;
        }

        .form-group input[type="text"]:focus,
        .form-group input[type="password"]:focus,
        .form-group input[type="email"]:focus {
            outline: none;
            border-color: #3366ff;
        }

        .form-group input[type="submit"] {
            background-color: #3366ff;
            color: white;
            border: none;
            padding: 12px 20px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .form-group input[type="submit"]:hover {
            background-color: #5cb85c;
        }

        .error {
      
         color: #5cb85c;
         margin-left: 30px; 
         font-weight: 600;
        }
        
        .btn{
            display: flex;
            
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
       
        <div class="container-2">
            
            <div class="form-container">
 
           <form method="post" action="UserServlet">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group btn">
                <input type="submit" name="submit" value="login">
               <div class="error">
            <%if(str!=null && str.length()>0){%>
                <p><%=str%></p>
            <%}%>
        </div>
        </form> 
        </div>
        </div>    
    </body>
</html>