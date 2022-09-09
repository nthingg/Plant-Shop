<%-- 
    Document   : updateInfor
    Created on : Jun 2, 2022, 11:16:37 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dbacess.AccountDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css">
    </head>
    <body>
        <header>
            <%@include file="header_loginedUser.jsp"%>
        </header>
        <section class="set-center-text">
            <c:if test="${sessionScope.name == null}">
                <c:redirect url="login.jsp?updatewarning=1"></c:redirect>
            </c:if>
            <c:if test="${requestScope.checkUpdate eq 2}">
                <h3 class="set-center-text" style="color: red;">UPDATE FAIL</h3>
            </c:if>
                <c:if test="${requestScope.checkUpdate eq 1}">
                <h3 class="set-center-text" style="color: #006666;">UPDATE SUCCCESS</h3>
            </c:if>
            <h1 class="set-center-text">UPDATE</h1>
            <form action="mainController" method="post" class="set-center-item formregister">
                <table>
                    <tr>
                        <td>Fullname</td>
                        <td><input class="normal-submit" type="text" name="fullname"></td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><input class="normal-submit" type="text" name="phone"></td>
                    </tr>
                    <tr class="set-center-text">
                        <td colspan="2"><input type="submit" class="submit-btn" value="update" name="action" ></td>
                    </tr>
                </table>
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
