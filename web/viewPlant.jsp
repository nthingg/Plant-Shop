<%-- 
    Document   : viewPlant
    Created on : Jun 12, 2022, 1:56:30 PM
    Author     : thinh
--%>

<%@page import="basicobj.Plant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css">
    </head>
    <body>
        <header>
            <%@include file="header_loginedUser.jsp"%>
        </header>
        <section>
            <h3>Product Detail</h3>
            <h3><a href="manageCartServlet">Back to Cart</a></h3>
            
            <!--EL-->
            <table class="order">
                <tr>
                    <td>Image</td>
                    <td>Product ID</td>
                    <td>Product Name</td>
                    <td>Price</td>
                    <td>Status</td>
                    <td>Category ID</td>
                    <td>Category name</td>
                </tr>
                <tr>
                    <td><img src="${plantObj.imgpath}" class="plantimg"></td>
                    <td>${plantObj.id}</td>
                    <td>${plantObj.name}</td>
                    <td>${plantObj.price}</td>
                    <td>${plantObj.status}</td>
                    <td>${plantObj.cateId}</td>
                    <td>${plantObj.cateName}</td>
                </tr>
            </table>


        </section>

        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
