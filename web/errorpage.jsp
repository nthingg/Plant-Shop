<%-- 
    Document   : errorpage
    Created on : Jun 19, 2022, 8:56:37 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <%@include file="header.jsp"%>
        </header>
        <section  class="set-center-item">
            <h3 class="set-center-text" style="color: red;">Something went wrong!</h3>
            <a href="index.jsp">RETURN TO HOME PAGE</a>
        </section>
        <footer>
            <%@include file="footer.jsp"%>
        </footer>
    </body>
</html>
