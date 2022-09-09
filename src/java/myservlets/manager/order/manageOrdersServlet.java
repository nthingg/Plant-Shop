/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlets.manager.order;

import basicobj.Order;
import dbacess.OrderDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thinh
 */
public class manageOrdersServlet extends HttpServlet {

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
            String email = (String) request.getParameter("txtSearch");
            String stringOrderID = (String) request.getParameter("orderid");
            String orderDate = (String) request.getParameter("orderDate");
            ArrayList<Order> listOrder = new ArrayList<>();
            if (email != null && !email.equals("")) {
                try {
                    listOrder = OrderDao.getOrders(email);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                request.setAttribute("success", "success");
            } else if (stringOrderID != null && !stringOrderID.equals("")) {
                int status = Integer.parseInt(request.getParameter("status"));
                if (status == 1) {
                    try {
                        OrderDao.changeOrderStatus(stringOrderID, 3);
                        OrderDao.changeOrderShipdate(stringOrderID, null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (status == 3) {
                    try {
                        OrderDao.changeOrderStatus(stringOrderID, 1);
                        OrderDao.changeOrderShipdate(stringOrderID, orderDate);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    listOrder = OrderDao.getAllOrders();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                request.setAttribute("success", "success");
            } else {
                try {
                    listOrder = OrderDao.getAllOrders();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            request.setAttribute("listOrder", listOrder);
            request.getRequestDispatcher("ManageOrders.jsp").forward(request, response);
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
