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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thinh
 */
public class filterOrderDateServlet extends HttpServlet {

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

            String from = request.getParameter("from");
            String to = request.getParameter("to");
            ArrayList<Order> listOrder = new ArrayList<>();
            ArrayList<Order> listValid = new ArrayList<>();

            if (from == null || to == null || to.equals("") || from.equals("")) {
                response.sendRedirect("manageOrdersServlet");
            } else {

                try {
                    listOrder = OrderDao.getAllOrders();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fromDate = sdf.parse(from);
                java.util.Date toDate = sdf.parse(to);

                for (Order order : listOrder) {
                    java.util.Date todayDate = sdf.parse(order.getOrderDate());
                    if (todayDate.after(fromDate) && todayDate.before(toDate)) {
                        listValid.add(order);
                    }
                    if (todayDate.equals(fromDate) || todayDate.equals(toDate)) {
                        listValid.add(order);
                    }
                }
                if (listValid != null) {
                    request.setAttribute("listOrder", listValid);
                } else {
                    request.setAttribute("listOrder", null);
                }
                request.getRequestDispatcher("ManageOrders.jsp").forward(request, response);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
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
