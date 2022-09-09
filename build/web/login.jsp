<%-- 
    Document   : login
    Created on : Jun 2, 2022, 3:24:45 PM
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
        <section>
            <c:if test="${param.warn != null}">
                <h4 class="set-center-text" style="color: red;">YOU MUST LOGIN TO ENTER PERSONAL PAGE</h4>
            </c:if>
            <c:if test="${param.updatewarning != null}">
                <h4 class="set-center-text" style="color: red;">YOU MUST LOGIN TO UPDATE INFORMATION</h4>
            </c:if>
            <c:if test="${param.adminwarning != null}">
                <h4 class="set-center-text" style="color: red;">YOU MUST LOGIN TO ENTER ADMIN PAGE</h4>
            </c:if>
        </section>
        <section class="set-center-item">
            <form action="mainController" method="post" class="formregister">
                <h1 class="set-center-text">LOGIN</h1>
                <table>
                    <tr>
                        <td>Email</td>
                        <td><input class="normal-submit" type="text" name="txtemail"></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input class="normal-submit" type="password" name="txtpass"></td>
                    </tr>
                    <tr class="set-center-text">
                        <td colspan="2"><input class="submit-btn" type="submit" value="login" name="action"></td>
                    </tr>
                    <tr>
                        <c:if test="${requestScope.errorlogin != null}">
                        <h5 class="set-center-text" style="color: red;">Wrong email or password!</h5>
                        </c:if>
                    </tr>
                    <tr class="set-center-text">
                        <td colspan="2"><input type="checkbox" value="savelogin" name="savelogin">Stay signed in</td>
                    </tr>
                </table>
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp"%>
        </footer>
    </body>
</html>
