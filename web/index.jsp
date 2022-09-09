<%-- 
    Document   : index
    Created on : Jun 2, 2022, 3:22:32 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dbacess.PlantDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="basicobj.Plant"%>
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


            <c:set var="listPlant" value="${requestScope.listPlant}"/>
            <c:if test="${listPlant == null}">
                <c:redirect url="searchPlantServlet"/>
            </c:if>

            <c:choose>
                <c:when test="${listPlant != null}">
                    <table class="order">
                        <tr>
                            <td>Image</td>
                            <td>Product ID</td>
                            <td>Product Name</td>
                            <td>Price</td>
                            <td>Status</td>
                            <td>Category</td>
                            <td>Action</td>
                        </tr>
                        <c:forEach var="plant" items="${listPlant}">
                            <tr>
                                <td><img src="${plant.getImgpath()}" class="plantimg"></td>
                                <td>${ plant.getId()}</td>
                                <td>${ plant.getName() }</td>
                                <td>${ plant.getPrice() }</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${ plant.getStatus() == 0}">
                                            Out of Stock
                                        </c:when>
                                        <c:otherwise>
                                            Available
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${ plant.getCateName() }</td>
                                <td>
                                    <c:url var="orderlink" value="mainController">
                                        <c:param name="pid" value="${ plant.getId()}"/>
                                        <c:param name="action" value="addtocart"/>
                                    </c:url>
                                    <a href="${orderlink}" onclick="return buy()">Add to Cart</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </c:when>
                <c:otherwise>
                    <p><font color='red'>You don't have any plant!!!</p>
                    </c:otherwise>
                </c:choose>

        </section>
        <footer>
            <%@include file="footer.jsp"%>
        </footer>

        <script>
            function buy() {
                alert("Added!");
            }
        </script>
    </body>
</html>
