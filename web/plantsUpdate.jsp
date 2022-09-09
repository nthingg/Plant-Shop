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
            <c:set var="pid" value="${param.pid}"/>
            <form action="mainController" method="post" class="formregister">
                <h1 class="set-center-text">INPUT NEW INFORMATION</h1>
                <table class="order">
                    <tr>
                        <th>Plant ID</th>
                        <th>Plant name</th>
                        <th>Price</th>
                        <th>Image</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Cate ID</th>
                    </tr>                 
                    <tr>
                        <td><input class="f-size-15" type="hidden" name="pid" value="${ pid }"/>${ pid }</td>
                        <td><input class="f-size-15" type="text" name="plantName" value="${param.plantName}"></td>
                        <td><input class="f-size-15" type="number" min="0" name="plantPrice" value="${param.plantPrice}"></td>
                        <td><input class="f-size-15" type="text" name="plantImg" value="${param.plantImg}"></td>
                        <td><input class="f-size-15" type="text" name="plantDescription" value="${param.plantDescription}"></td>
                        <td><input class="f-size-15" type="number" min="0" name="plantStatus" value="${param.plantStatus}"></td>
                        <td><input class="f-size-15" type="number" min="0" name="cateID" value="${param.cateID}"></td>
                    </tr>
                </table>
                <input style="margin-left: 850px; margin-top: 20px;" type="submit" class="submit-btn" value="UpdatePlant" name="action" />
            </form>
            <h1 class="set-center-text" style="color: red;">${requestScope.alert}</h1>
        </section>
        <c:import url="footer.jsp"/>
    </body>
</html>
