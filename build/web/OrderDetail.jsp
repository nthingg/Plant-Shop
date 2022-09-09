<%-- 
    Document   : OrderDetail
    Created on : Jun 2, 2022, 8:15:34 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dbacess.OrderDao"%>
<%@page import="basicobj.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <%@include file="header_loginedUser.jsp"%>
        </header>

        <section>
            <h3>Welcome ${sessionScope.name}</h3>
            <a href="mainController?action=logout">Logout</a>
        </section>

        <section>

            <c:set var="list" value="${requestScope.listOrderDetail}"/>
            <c:set var="money" value="${requestScope.money}"/>
            <c:choose>
                <c:when test="${list != null}">

                    <table class="order">
                        <tr>
                            <td>Order ID</td>
                            <td>Plant ID</td>
                            <td>Plant Name</td>
                            <td>Image</td>
                            <td>Quantity</td>
                        </tr>
                        <c:forEach var="orderDetail" items="${list}">
                            <tr>
                                <td>${ orderDetail.getOrderID() }</td>
                                <td>${ orderDetail.getPlantID() }</td>
                                <td>${ orderDetail.getPlantName() }</td>
                                <td> <img src='${ orderDetail.getImgPath() }' class="plantimg"/>
                                </td>
                                <td>${ orderDetail.getQuantity() }</td>
                            </tr>
                        </c:forEach>
                    </table>

                    <h3 class="set-center-text">Total money: ${money}</h3>
                </c:when>
                <c:otherwise>
                    <p><font color='red'>You don't have any order!!!</p>
                    </c:otherwise>
                </c:choose>
            <a href="personalPage.jsp">view all orders</a>        
        </section>

        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
