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
            <c:set var="cid" value="${param.cid}"/>
            <form action="mainController" method="post" class="formregister ">
                <h1 class="set-center-text">INPUT NEW INFORMATION</h1>
                <table class="order" style="width: 800px; margin: 0 auto;">
                    <tr>
                        <th>Category ID</th>
                        <th>Category Name</th>
                    </tr>                 
                    <tr>
                        <td><input class="f-size-15" type="hidden" name="cid" value="${ cid }"/>${ cid }</td>
                        <td><input class="f-size-15" type="text" name="cateName" value="${param.cateName}"></td>
                    </tr>
                </table>
                <input class="submit-btn" style="margin-left: 850px; margin-top: 20px;" type="submit" value="updateCate" name="action" />
            </form>
            <h1 class="set-center-text" style="color: red;">${requestScope.alert}</h1>
            <a href="ManageCategory.jsp">back</a>
        </section>
        <c:import url="footer.jsp"/>
    </body>
</html>
