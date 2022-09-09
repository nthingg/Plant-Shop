<%-- 
    Document   : header_loginedAdmin
    Created on : Jun 12, 2022, 4:02:49 PM
    Author     : thinh
--%>

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
            <ul>
                <li><a href="AdminIndex.jsp">Admin Index</a></li>
                <li><a href="mainController?action=manageAccounts">Manage Account</a></li>
                <li><a href="mainController?action=manageOrders">Manage Order</a></li>
                <li><a href="mainController?action=managePlants">Manage Plant</a></li>
                <li><a href="mainController?action=manageCates">Manage Category</a></li>
                <li> Welcome ${sessionScope.name} | <a href="mainController?action=logout">logout</a></li>
            </ul>
        </header>
    </body>
</html>
