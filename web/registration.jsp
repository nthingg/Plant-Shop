<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : registration
    Created on : Jun 2, 2022, 3:31:32 PM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css">
    </head>
    <body>
        <header>
            <%@include file="header.jsp"%>
        </header>
        <section class="set-center-item">
            <c:choose>
                <c:when test="${requestScope.otp == null}">
                    <form action="mainController" method="post" class="formregister">
                        <h1 class="set-center-text">REGISTER</h1>
                        <table>
                            <tr>
                                <td>Email</td>
                                <td><input class="normal-submit" type="text" name="txtemail" required value="${param.txtemail}"></td>
                            </tr>
                            <tr>
                                <td>Fullname</td>
                                <td><input class="normal-submit" type="text" name="txtname" required  value="${param.txtname}"></td>
                            </tr>
                            <tr>
                                <td>Password</td>
                                <td><input class="normal-submit" type="password" name="txtpass" required></td>
                            </tr>
                            <tr>
                                <td>Phone</td>
                                <td><input class="normal-submit" type="text" name="txtphone" value="${param.txtphone}"></td>
                            </tr>
                            <tr>
                                <h5 class="set-center-text" style="color: red;">${requestScope.emailerror}</h5>
                                <h5 class="set-center-text" style="color: red;">${requestScope.phoneerror}</h5>
                                <h5 class="set-center-text" style="color: red;">${requestScope.emaildupli}</h5>
                            </tr>
                            <tr class="set-center-text">
                                <td colspan="2"><input class="submit-btn" type="submit" value="register" name="action"></td>
                            </tr>
                        </table>
                    </form>  
                </c:when>
                <c:otherwise>
                    <h3 class="set-center-text">An OTP is sent to your email: ${requestScope.email_newAcc}</h3>
                </c:otherwise>
            </c:choose>
        </section>
        <section>
            <div class="filter-container">
                <div>
                    <a href="personalPage.jsp">MOVE TO PERSONAL PAGE</a>
                </div>
                <div>
                    <a href="index.jsp">MOVE TO HOME PAGE</a>
                </div>
            </div>
        </section>
        <footer>
            <%@include file="footer.jsp"%>
        </footer>
    </body>
</html>
