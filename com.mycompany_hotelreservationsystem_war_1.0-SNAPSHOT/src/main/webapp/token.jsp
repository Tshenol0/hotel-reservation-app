<%-- 
    Document   : register.jsp
    Created on : Jun 21, 2024, 9:27:40 AM
    Author     : Train
--%>

<%
  String name =(String)request.getAttribute("name");
  String surname =(String)request.getAttribute("surname");
  String email =(String)request.getAttribute("email");
  String password = (String)request.getAttribute("password");
  String token = (String)request.getAttribute("token");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>verify</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;            
        }

        .container {
            max-width: 1300px;
            margin: 0 auto;
            height: 100vh;
            display: flex;
            flex-direction: column;
        }
        
        form{
            width: 510px;
        }

        .container-2 {
            display: flex;
            justify-content: center; /* Center items horizontally */
            align-items: center; /* Center items vertically */
            height: 100vh; /* Adjust height as needed */
        }

        .form-container {
            width: 50%; /* Take up 50% of the container-2 width */
            max-width: 400px; /* Limit maximum width */
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-group input[type="text"],
        .form-group input[type="password"],
        .form-group input[type="email"] {
            width: 60%; /* Take full width of the form container */
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            transition: border-color 0.3s;
            box-sizing: border-box; /* Ensure padding is included in width calculation */
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
            background-color: #2652bf;
        }
        
      
    </style>
    </head>
    <body>
        <main class="container">
        <div class="container-2">
        <form method="post" action="UserServlet">
            <div class="form-group">
                <input type="hidden" id="email" name="email" value="<%=email%>" required>
            </div>
            <div class="form-group">
                <input type="hidden" id="password" name="password" value="<%=password%>" required>
            </div>
            <div class="form-group">
                <input type="hidden" id="name" name="name" value="<%=name%>" required>
            </div>
            <div class="form-group">
                <input type="hidden" id="surname" name="surname" value="<%=surname%>" required>
            </div>
            <div class="form-group">
                <input type="hidden" id="token2" name="token2" value="<%=token%>" required>
            </div>            
            <div class="form-group">
                <label for="password">token:</label>
                <input type="password" id="token" name="token" placeholder="token" required>
            </div>
            <div class="form-group">
                <input type="submit" name="submit" value="verify">
            </div>
        </form>            
        </div>
        </div>
        </main>
    </body>
</html>