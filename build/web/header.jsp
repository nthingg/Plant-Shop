    <%-- 
    Document   : header
    Created on : Jun 2, 2022, 3:10:53 PM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
        <style>
            .logo-header {
                width: 100px;
            }
        </style>
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li><a style="pointer-events: none;" href="#"><img class="logo-header noHover" src="img/logo.jpg"></a> </li>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="resetPersonalPage">Personal Page</a></li>
                    <li><a href="registration.jsp">Register</a></li>
                    <li><a href="login.jsp" >Login</a></li>
                    <li><a href="manageCartServlet">View cart</a></li>
                    
                    <li>
                        <form action="mainController" method="post" style="margin-top: 20px">
                            <input type="text" class="f-size-15" name="txtsearch" value="${param.txtsearch}">
                            
                            <select name="searchby" class="f-size-15">
                                <option value="byname">by name</option>
                                <option value="bycate">by category</option>
                            </select>
                            <br/>
                            <input type="submit" class="submit-btn" style="margin-top: 5px" value="search" name="action" >
                        </form>
                    </li>
                </ul>
            </nav>
        </header>
    </body>
</html>
