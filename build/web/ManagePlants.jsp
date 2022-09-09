<%-- 
    Document   : ManagePlants
    Created on : Jun 15, 2022, 8:19:16 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"/>
        <section>

            <c:set var="list" value="${requestScope.listPlant}"/>

            <c:if test="${list == null}">
                <c:redirect url="managePlantsServlet.jsp"/>
            </c:if>

            <c:if test="${requestScope.success == null}">
                <div style="margin: 10px 0; height: 100px; display: flex; align-items: center; justify-content: center;">
                    <div style="border: 1px #006666 solid; height: 100%; width: 200px; display: flex; align-items: center; justify-content: center;">
                        <a href="plantsCreate.jsp">Create new plant</a>
                    </div>
                </div>
            </c:if>
            
            <h1 class="set-center-text">PLANT</h1>

            <table class="order">
                <tr>
                    <th>Plant ID</th>
                    <th>Plant name</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Cate ID</th>
                    <th>Cate name</th>
                    <th>Action</th>
                </tr>

                <c:forEach var="plant" items="${list}">
                    <tr>
                        <td><c:out value="${ plant.getId() }"></c:out></td>
                        <td><c:out value="${ plant.getName() }"></c:out></td>
                        <td><c:out value="${ plant.getPrice() }"></c:out></td>
                        <td><img src="${ plant.getImgpath()}" class="plantimg"/></td>
                        <td><c:out value="${ plant.getDescription() }"></c:out></td>
                        <td><c:choose>
                                <c:when test="${plant.getStatus() eq 1}">Available</c:when>
                                <c:otherwise>Out of stock</c:otherwise>
                            </c:choose></td>
                        <td><c:out value="${plant.getCateId()}"></c:out></td>
                        <td><c:out value="${ plant.getCateName() }"></c:out></td>
                        <td><a href="plantsUpdate.jsp?pid=${plant.getId()}">Update</a></td>
                    </tr>
                </c:forEach>
            </table>
            <c:if test="${requestScope.success != null}">
                <h3 class="set-center-text" style="color: red; cursor: text;">${requestScope.success}</h3>
                <a href="managePlantsServlet">Back</a>
            </c:if>
        </section>
        <c:import url="footer.jsp"/>
    </body>
</html>
