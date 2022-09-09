<%-- 
    Document   : header_loginedUser
    Created on : Jun 2, 2022, 4:18:35 PM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css">
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="updateInfor.jsp">Change profile</a></li>
                <li><a href="mainController?action=completeOrders">Complete orders</a></li>
                <li><a href="mainController?action=cancelOrders">Cancel orders</a></li>
                <li><a href="mainController?action=processOrders">Processing orders</a></li>
                <li>
                    <form action="mainController" method="post">
                        <p>FROM <input type="date" class="f-size-15" name="from"> TO <input class="f-size-15" type="date" name="to"></p>
                        <input type="submit" class="submit-btn" value="searchDate" name="action">
                    </form>
                </li>
            </ul>
        </nav>
    </body>
</html>
