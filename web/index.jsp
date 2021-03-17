<%-- 
    Document   : index
    Created on : Mar 17, 2021, 11:13:13 AM
    Author     : ThaiDuongNg
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="public/css/home.css" rel="stylesheet" type="text/css">
        <title>Home</title>
    </head>
    <body>
        <div class="container">
            <div class="topnav">
                <div class="left">
                    <a class="active" href="#home">Posts</a>
                    <a class="path" href="#contact">Approve Comment</a>
                </div>
                <div class="right">
                    <a class="user-name">Welcome Ngu</a>
                    <a class="path" href="logout">Logout</a>
                </div>
            </div>
            <c:forEach var="post" items="${posts}">
                <div class="home-body">
                    <div class="post-content">
                        <p>${post.author_name}</p>
                        <p>${post.content}</p>
                        <h5>${post.numberOfComment} bình luận</h5>
                        <hr>
                        <c:forEach var="comment" items="${post.comments}">
                            <a><b>${comment.user_name}</b></a> <span>${comment.comment_content}</span>
                            <br>
                        </c:forEach>
                        <br>
                        <br>
                        <hr>
                        <form action="home" method="POST">
                            <textarea rows="4" cols="50" name="comment_content" placeholder="Comment"></textarea>
                            <button type="submit" class="btn">Add Comment</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>

    </body>
</html>
