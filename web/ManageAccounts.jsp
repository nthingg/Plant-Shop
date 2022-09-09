<%-- 
    Document   : ManageAccounts
    Created on : Jun 12, 2022, 4:21:02 PM
    Author     : thinh
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <c:import url="header_loginedAdmin.jsp"/>
        </header>
        <section>
            <form action="mainController" method="post" style="padding: 10px 10px;">
                <input class="normal-submit" type="text" name="txtSearch">
                <input class="submit-btn" type="submit" value="searchAccount" name="action">
            </form>
            <h1 class="set-center-text">ACCOUNT</h1>
            <table class="order">
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Fullname</th>
                    <th>Status</th>
                    <th>Phone</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
                <c:set var="accountList" value="${requestScope.listAcc}"/>
                <c:forEach var="acc" items="${accountList}">
                    <tr>
                        <td><c:out value="${ acc.getAccid() }"></c:out></td>
                        <td><c:out value="${ acc.getEmail() }"></c:out></td>
                        <td><c:out value="${ acc.getFullname() }"></c:out></td>
                        <td><c:choose>
                                <c:when test="${acc.getStatus() eq 1}">Active</c:when>
                                <c:otherwise>Inactive</c:otherwise>
                            </c:choose></td>
                        <td><c:out value="${acc.getPhone()}"></c:out></td>
                        <td><c:choose>
                                <c:when test="${acc.getRole() eq 1}">Admin</c:when>
                                <c:otherwise>User</c:otherwise>
                            </c:choose></td>
                        <td><c:if test="${acc.getRole() eq 0}"> 
                                <c:url var="mylink" value="mainController">
                                    <c:param name="email" value="${acc.getEmail()}"></c:param>
                                    <c:param name="status" value="${acc.getStatus()}"></c:param>
                                    <c:param name="action" value="updateStatusAccount"></c:param>
                                </c:url>
                                <a href="${mylink}">Block/Unblock</a>
                            </c:if>
                            <c:if test="${acc.getRole() eq 1}">
                                <a href="#" style="color: black; cursor: text;">NONE</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </section>
        <c:import url="footer.jsp"></c:import>
    </body>
</html>
