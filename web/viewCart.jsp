<%-- 
    Document   : viewCart
    Created on : Jun 7, 2022, 10:31:14 AM
    Author     : thinh
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dbacess.PlantDao"%>
<%@page import="basicobj.Plant"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
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
            <%@include file="header_loginedUser.jsp"%>
        </header>
        <section>
            <c:if test="${sessionScope.name != null}">
                <h3>Welcome ${name} <a href="mainController?action=logout">Logout</a></h3>
            </c:if>
            <h3 class="set-center-text" style="color: red;">${requestScope.WARNING}</h3>
            <table width="100%" class="order">
                <tr>
                    <td>Product ID</td>
                    <td>Image</td>
                    <td>Price</td>
                    <td>Quantity</td>
                    <td>Action</td>
                </tr>
                
                <c:set var="listCart" value="${requestScope.listCart}"/>
                <c:set var="total" value="${requestScope.total}"/>
                <c:set var="cart" value="${sessionScope.cart}" />
                <h1 class="set-center-text">CART</h1>
                <c:choose>
                    <c:when test="${listCart != null}">
                        <c:forEach var="plant" items="${listCart}" >
                            <form action="mainController" method="post">
                                <tr>
                                    <td><input type="hidden" value="${plant.getId()}" name="pid"><a href="getPlantServlet?pid=${plant.getId()}">${plant.getId()}</a></td>
                                    <td><img src="${plant.getImgpath()}" class="plantimg"></td>
                                    <td>${plant.getPrice()}</td>
                                    <c:forEach var="item" items="${cart}">
                                        <c:if test="${item.key == plant.getId() }">
                                            <c:set var="quantity" value="${item.value}"/>
                                        </c:if>
                                    </c:forEach>
                                    <td><input type="number" name="quantity" min="0" value="${quantity}" ></td> <!-- ????????? -->
                                    <td>
                                        <input type="submit" class="submit-btn" value="updateCart" name="action" min="1" max="20">
                                        <input type="submit" class="submit-btn" value="deleteCart" name="action">
                                        <!--onclick="return warning()"-->
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
<!--                        <h3 class="set-center-text" style="color: red;">Your cart is empty</h3>-->
                    </c:otherwise>
                </c:choose>
            </table>
        </section>
        <section>
            <div class="set-center-text p-top-20">
                <p>Total money: ${total}</p>
                <p>Order date: ${requestScope.date}</p>
                <p>Ship date: N/A</p>
            </div>
            <form action="mainController" method="post" class="set-center-text p-bot-20">
                <input type="submit" class="submit-btn" value="saveOrder" name="action" class="submitorder">
            </form>
            <a href="resetPersonalPage">Personal Page</a>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>

        <script>
            function warning() {
                var a = window.confirm("sure?");
                if (a == true) {
                    return true;
                } else {
                    return  false;
                }
            }
        </script>
    </body>
</html>
