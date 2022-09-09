<%-- 
    Document   : plantsCreate
    Created on : Jun 18, 2022, 5:53:30 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Plant</title>
        <link rel="stylesheet" href="mycss.css" type="text/css">
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"/>
        <section>
            <c:set var="pid" value="${param.pid}"/>
            <form action="mainController" method="post" class="formregister">
                <h1 class="set-center-text">CREATE NEW PLANT</h1>
                <table class="order">
                    <tr>
                        <th>Plant name</th>
                        <th>Price</th>
                        <th>Image</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Cate ID</th>
                    </tr>                 
                    <tr>
                        <td><input class="f-size-15" required type="text" name="plantName" value="${param.plantName}"></td>
                        <td><input class="f-size-15" required type="number" min="0" name="plantPrice" value="${param.plantPrice}"></td>
                        <td><input class="f-size-15" required type="text" name="plantImg" value="${param.plantImg}"></td>
                        <td><input class="f-size-15" required type="text" name="plantDescription" value="${param.plantDescription}"></td>
                        <td><input class="f-size-15" required type="number" min="0" name="plantStatus" value="${param.plantStatus}"></td>
                        <td><input class="f-size-15" required type="number" min="0" name="cateID" value="${param.cateID}"></td>
                    </tr>
                </table>
                <input class="submit-btn" style="margin-left: 850px; margin-top: 20px;" type="submit" value="CreatePlant" name="action" />
            </form>
            <h1 class="set-center-text" style="color: red;">${requestScope.alert}</h1>
        </section>
        <c:import url="footer.jsp"/>
    </body>
</html>
