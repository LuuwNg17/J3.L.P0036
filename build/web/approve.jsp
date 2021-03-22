<%-- 
    Document   : approve
    Created on : Mar 21, 2021, 3:05:04 PM
    Author     : ThaiDuongNg
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="public/css/style.css" rel="stylesheet" type="text/css">
        <title>approve</title>
    </head>
    <body>
        <div class="container" style="background-color: #eaeaea">
            <!--header-->
            <%@include file="components/header.jsp" %>

            <p>Comment</p>
            <p style="padding: 10px;">
                <a href="approve?type=all">All <span>(${numberAll})</span></a> |
                <a href="approve?type=0">Pending <span>(${numberPending})</span></a> | 
                <a href="approve?type=1">Approved <span>(${numberApproved})</span></a> | 
                <a href="approve?type=2">Spam <span>(${numberSpam})</span></a> | 
                <a href="approve?type=3">Trash <span>(${numberTrash})</span></a> 
            </p>
            <div class="table-body">
                <table>
                    <c:if test="${comments.size() == 0}">
                        <p>You do not have any comments to approve</p>
                    </c:if>
                    <c:forEach var="cmt" items="${comments}">
                        <tr style="background-color: white">
                            <td>Author</td> 
                            <td> Comment</td>
                            <td>In Response To</td>
                            <td>Submitted On</td>
                        </tr>
                        <tr style="background-color: #FEF7F1; 
                            border-left: 1px; 
                            border-color: #d65327">
                            <td>${cmt.user_name}</td>
                            <td>
                                ${cmt.comment_content}
                                <br>
                                <p>
                                    <a href="handle_comment?type=1&id=${cmt.comment_id}">Approve</a> |
                                    <a href="reply?id=${cmt.comment_id}">Reply</a> | 
                                    <a>Quick Edit</a> | 
                                    <a>Edit</a> | 
                                    <a href="handle_comment?type=2&id=${cmt.comment_id}">Spam</a> |
                                    <a href="handle_comment?type=3&id=${cmt.comment_id}">Trash</a> 
                                </p>
                            </td>
                            <td>
                                <c:forEach var="post" items="${posts}">
                                    <c:if test="${post.post_id == cmt.post_id}">
                                        ${post.post_title}
                                    </c:if>
                                </c:forEach>
                                <br>
                                <a href="#">View post</a>
                            </td>
                            <td>${cmt.getDateFormat()}</td>
                        </c:forEach>
                    </tr>
                </table>
            </div>
        </div>

    </body>
</html>
