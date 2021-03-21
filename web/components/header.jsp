<%-- 
    Document   : header
    Created on : Mar 21, 2021, 1:15:42 PM
    Author     : ThaiDuongNg
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <div class="topnav">
            <div class="left">
                <a class="${home}" href="home">Posts</a>
                <a class="${approve}" href="approve?type=all">Approve Comment</a>
            </div>
            <div class="right">
                <a class="user-name">Welcome ${sessionScope.user.user_name}</a>
                <a class="path" href="logout">Logout</a>
            </div>
        </div>
    </body>
</html>
