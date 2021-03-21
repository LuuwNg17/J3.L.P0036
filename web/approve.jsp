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
            <p>
                <a href="approve?type=all">All(${number})</a> |
                <a href="approve?type=0">Pending(${number})</a> | 
                <a href="approve?type=1">Approved(${number})</a> | 
                <a href="approve?type=2">Spam(${number})</a> | 
                <a href="approve?type=3">Trash(${number})</a> 
            </p>
            <div class="table-body">
                <table>
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
                                    <a>Approve</a> |
                                    <a>Reply</a> | 
                                    <a>Quick Edit</a> | 
                                    <a>Edit</a> | 
                                    <a>Spam</a> |
                                    <a>Trash</a> 
                                </p>
                            </td>
                            <td>
                                Phan mem dich vu Ssas la gi
                                <br>
                                <a href="#">View post</a>
                            </td>
                            <td>2021-03-02 00:00:00.000</td>
                        </c:forEach>
                    </tr>
                </table>
            </div>
        </div>

    </body>
</html>
