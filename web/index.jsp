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
        <link href="public/css/style.css" rel="stylesheet" type="text/css">
        <title>Home</title>
    </head>
    <body>
        <div class="container">
            <!--header-->
            <%@include file="components/header.jsp" %>

            <c:forEach var="post" items="${posts}">
                <div class="home-body">
                    <div class="post-content">
                        <p class="post-content-text">
                            ${post.post_title}.<br>
                            <span>${post.content}</span>
                        </p>
                        <p style="color: #2a2a2a; font-size: 14px;">
                            by ${post.author_name}
                        </p>
                        <b style="font-size: 12px">${post.numberOfComment} bình luận</b>
                        <hr>
                        <c:forEach var="comment" items="${post.comments}">

                            <!--display all comment of post have been approved-->
                            <c:if test="${comment.isApproved == true}">
                                <p style="font-size: 12px">
                                    <b>${comment.user_name}</b>. <span>${comment.comment_content}</span>
                                </p>
                            </c:if>
                        </c:forEach>
                        <br>
                        <hr>
                        <p>Leave a Reply</p>
                        <form action="add_comment?post_id=${post.post_id}" method="POST">
                            <p class="text-gray">
                                Logged in as ${sessionScope.user.user_name}. 
                                <a class="text-gray" href="logout">Log out</a>
                            </p>
                            <textarea rows="6" cols="50" name="comment_content" placeholder="Comment"></textarea>
                            <button cols="50" type="submit" class="btn-commnent">Add Comment</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>

    </body>
</html>
