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

            <c:set var="list" value="${requestScope.listCate}"/>

            <c:if test="${list == null}">
                <c:redirect url="manageCatesServlet"/>
            </c:if>

            <c:if test="${requestScope.success == null}">
                <div style="margin: 10px 0; height: 100px; display: flex; align-items: center; justify-content: center;">
                    <div style="border: 1px #006666 solid; height: 100%; width: 300px; display: flex; align-items: center; justify-content: center;">
                        <a href="catesCreate.jsp">Create new category</a>
                    </div>
                </div>
            </c:if>
            
            <h1 class="set-center-text">CATEGORY</h1>

            <table class="order">
                <tr>
                    <th>Category ID</th>
                    <th>Category name</th>
                    <th>Action</th>
                </tr>

                <c:forEach var="cate" items="${list}">
                    <tr>
                        <td><c:out value="${ cate.getCateID() }"></c:out></td>
                        <td><c:out value="${ cate.getCateName() }"></c:out></td>
                        <td><a href="catesUpdate.jsp?cid=${cate.getCateID()}">Update</a></td>
                    </tr>
                </c:forEach>
            </table>
            <c:if test="${requestScope.success != null}">
                <h3 class="set-center-text" style="color: red; cursor: text;">${requestScope.success}</h3>
                <a href="manageCatesServlet">Back</a>
            </c:if>
        </section>
        <c:import url="footer.jsp"/>
    </body>
</html>
