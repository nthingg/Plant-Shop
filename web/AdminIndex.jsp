<%-- 
    Document   : adminPage
    Created on : Jun 2, 2022, 4:16:14 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
        <style>
            img {
                width: 100%;
                height: 500px;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.name == null}">
            <c:redirect url="login.jsp?adminwarning=1"></c:redirect>
        </c:if>
        <c:import url="header_loginedAdmin.jsp"></c:import>
        <section class="right">
            <img src="img/background.jpg"/>
        </section>
        <c:import url="footer.jsp"></c:import>
    </body>
</html>
