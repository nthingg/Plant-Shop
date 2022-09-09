<%-- 
    Document   : UpdatePlants
    Created on : Jun 16, 2022, 3:08:51 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css">
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"/>
        <section>
            <c:set var="cid" value="${param.cid}"/>
            <form action="mainController" method="post" class="formregister">
                <h1 class="set-center-text">CREATE NEW CATEGORY</h1>
                <table class="order"  style="width: 400px; margin: 0 auto;">
                    <tr>
                        <th>Category Name</th>
                    </tr>                 
                    <tr>
                        <td><input class="f-size-15" required type="text" name="cateName" value="${param.cateName}"></td>
                    </tr>
                </table>
                <input style="margin-left: 850px; margin-top: 20px;" type="submit" class="submit-btn" value="createCate" name="action" />
            </form>
            <h1 class="set-center-text" style="color: red;">${requestScope.alert}</h1>
        </section>
        <c:import url="footer.jsp"/>
    </body>
</html>
