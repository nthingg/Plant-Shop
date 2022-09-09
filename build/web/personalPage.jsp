<%-- 
Document   : personalpage
Created on : Jun 2, 2022, 4:02:46 PM
Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="basicobj.Account"%>
<%@page import="dbacess.AccountDao"%>
<%@page import="dbacess.OrderDao"%>
<%@page import="basicobj.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css">
    </head>
    <body>
        <c:set var="name" value="${sessionScope.name}" ></c:set>
        <c:choose>
            <c:when test="${name == null}">
                <c:redirect url="login.jsp?warn=login"></c:redirect>
            </c:when>
            <c:otherwise>
                <header>
                    <%@include file="header_loginedUser.jsp"%>
                </header>

                <section>
                    <h3>Welcome ${name} <a href="mainController?action=logout">Logout</a></h3>
                </section>

                <section>

                    <c:choose>
                        <c:when test="${requestScope.listFilterOrder != null}">
                            <c:set var="listOrder" value="${requestScope.listFilterOrder}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="listOrder" value="${sessionScope.listOrder}"/>
                        </c:otherwise>
                    </c:choose>

                    
                    <h1 class="set-center-text">ORDER</h1>
                    <c:choose>
                        <c:when test="${listOrder != null}">
                            <table class="order">
                                <tr>
                                    <td>Order ID</td>
                                    <td>Order Date</td>
                                    <td>Ship Date</td>
                                    <td>Order's status</td>
                                    <td>Action</td>
                                    <td>More</td>
                                </tr>
                                <c:forEach var="order" items="${listOrder}">
                                    <tr>
                                        <td>${order.getOrderID()}</td>
                                        <td>${order.getOrderDate()}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${ order.getShipDate() != null}">
                                                    <c:out value="${ order.getShipDate() }"></c:out>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="N/A"></c:out>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${order.getStatus() == 1}">
                                                    Processing
                                                </c:when>
                                                <c:when test="${order.getStatus() == 3}">
                                                    Canceled
                                                </c:when>
                                                <c:otherwise>
                                                    Completed
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:if test="${order.getStatus() == 1 or order.getStatus() == 3 }">
                                                <c:url var="mylink" value="mainController">
                                                    <c:param name="orderid" value="${order.getOrderID()}"/>
                                                    <c:param name="status" value="${order.getStatus()}"/>
                                                    <c:param name="orderDate" value="${order.getOrderDate()}"/>
                                                    <c:param name="action" value="cancel"/>
                                                </c:url>
                                                <a href="${mylink}">Change status</a>
                                            </c:if>
                                            <c:if test="${order.getStatus() == 2}">
                                                <a href="#" style="color: black; cursor: text;">none</a>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:url var="orderlink" value="mainController">
                                                <c:param name="orderid" value="${order.getOrderID()}"/>
                                                <c:param name="action" value="orderDetail"/>
                                            </c:url>
                                            <a href="${orderlink}">detail</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <h3 style="color: red;">You don't have any order!!!</h3>
                        </c:otherwise>
                    </c:choose>
                </section>

                <footer>
                    <%@include file="footer.jsp" %>
                </footer>
            </c:otherwise>
        </c:choose>
    </body>
</html>
