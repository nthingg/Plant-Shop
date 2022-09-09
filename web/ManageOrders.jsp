<%-- 
    Document   : manageOrders
    Created on : Jun 19, 2022, 8:54:46 PM
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
        <c:import url="header_loginedAdmin.jsp"/>
        <section>

            <div class="filter-container">
                <div class="filter-acc">
                    <form action="mainController" method="post" style="padding: 10px 10px;">
                        <input class="normal-submit" type="text" name="txtSearch">
                        <input class="submit-btn" type="submit" value="filterAccount" name="action">
                    </form>
                </div>
                <div class="filter-date">
                    <form action="mainController" method="post">
                        <p>FROM <input type="date" class="f-size-15" name="from"> TO <input class="f-size-15" type="date" name="to"></p>
                        <input type="submit" class="submit-btn" value="filterDate" name="action">
                    </form>
                </div>
            </div>

            <c:set var="list" value="${requestScope.listOrder}"/>
            <c:if test="${list == null}">
                <c:redirect url="manageOrdersServlet"/>
            </c:if>

            <h1 class="set-center-text">ORDER</h1>
            <table class="order">
                <tr>
                    <th>Order ID</th>
                    <th>Order date</th>
                    <th>Ship date</th>
                    <th>Status</th>
                    <th>Account ID</th>
                    <td>Action</td>
                </tr>

                <c:forEach var="order" items="${list}">
                    <tr>
                        <td><c:out value="${ order.getOrderID() }"></c:out></td>
                        <td><c:out value="${ order.getOrderDate() }"></c:out></td>
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
                        <td><c:out value="${ order.getAccID() }"></c:out></td>
                        <td>
                            <c:if test="${order.getStatus() == 1 or order.getStatus() == 3 }">
                                <c:url var="mylink" value="mainController">
                                    <c:param name="orderid" value="${order.getOrderID()}"/>
                                    <c:param name="status" value="${order.getStatus()}"/>
                                    <c:param name="orderDate" value="${order.getOrderDate()}"/>
                                    <c:param name="action" value="changeStatus"/>
                                </c:url>
                                <a href="${mylink}">Change status</a>
                            </c:if>
                            <c:if test="${order.getStatus() == 2 }">
                                <a href="#" style="color: black; cursor: text;">NONE</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <c:if test="${requestScope.success != null}">
                <a href="manageOrdersServlet">Back</a>
            </c:if>
        </section>
        <c:import url="footer.jsp"/>
    </body>
</html>
