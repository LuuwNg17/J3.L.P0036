<%-- 
    Document   : error
    Created on : Mar 21, 2021, 11:27:37 AM
    Author     : ThaiDuongNg
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <!--            header-->
            <%@include file="components/header.jsp" %>
            <div>
                <p style="color: red">${error}</p>
            </div>

            <!--            footer-->
            <%@include file="components/footer.jsp" %>
        </div>
    </body>
</html>
