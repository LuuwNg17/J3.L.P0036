<%-- 
    Document   : login
    Created on : Mar 17, 2021, 11:15:31 AM
    Author     : ThaiDuongNg
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="public/css/loggin.css" rel="stylesheet" type="text/css"> 
        <title>Login</title>
    </head>
    <body>
        <div class="body-login">
            <h2>Login to your account</h2>
            <form action="login" method="POST">
                <input type="text" title="username" placeholder="username" name="username"/>
                <input type="password" title="username" placeholder="password" name="password"/>
                <p style="color: red">${error}</p>
                <button type="submit" class="btn">Login</button>
            </form>
            
        </div>
    </body>
</html>
