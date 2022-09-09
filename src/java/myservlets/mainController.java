/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thinh
 */
public class mainController extends HttpServlet {

    private String url = "errorpage.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");

            switch (action) {
                case "search":
                    url = "searchPlantServlet";
                    break;
                case "":
                    url = "index.jsp";
                    break;
                case "login":
                    url = "loginServlet";
                    break;
                case "register":
                    url = "registerServlet";
                    break;
                case "logout":
                    url = "logoutServlet";
                    break;
                case "update":
                    url = "updateServlet";
                    break;
                case "cancel":
                    url = "cancelServlet";
                    break;
                case "addtocart":
                    url = "addToCartServlet";
                    break;
                case "viewcart":
                    url = "manageCartServlet";
                    break;
                case "updateCart":
                    url = "updateCartServlet";
                    break;
                case "deleteCart":
                    url = "deleteCartServlet";
                    break;
                case "saveOrder":
                    url = "saveShoppingCartServlet";
                    break;
                case "searchDate":
                    url = "searchDateServlet";
                    break;
                case "manageAccounts":
                    url = "manageAccountsServlet";
                    break;
                case "searchAccount":
                    url = "manageAccountsServlet";
                    break;
                case "updateStatusAccount":
                    url = "updateStatusAccountServlet";
                    break;
                case "orderDetail":
                    url = "orderDetailServlet";
                    break;
                case "managePlants":
                    url = "managePlantsServlet";
                    break;
                case "UpdatePlant":
                    url = "updatePlantServlet";
                    break;
                case "CreatePlant":
                    url = "createPlantServlet";
                    break;
                case "manageOrders":
                    url = "manageOrdersServlet";
                    break;
                case "filterDate":
                    url = "filterOrderDateServlet";
                    break;
                case "filterAccount":
                    url = "manageOrdersServlet";
                    break;
                case "changeStatus":
                    url = "manageOrdersServlet";
                    break;
                case "manageCates":
                    url = "manageCatesServlet";
                    break;
                case "updateCate":
                    url = "updateCateServlet";
                    break;
                case "createCate":
                    url = "createCateServlet";
                    break;
                case "completeOrders":
                    url = "filterOrderServlet";
                    break;
                case "cancelOrders":
                    url = "filterOrderServlet";
                    break;
                case "processOrders":
                    url = "filterOrderServlet";
                    break;
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
