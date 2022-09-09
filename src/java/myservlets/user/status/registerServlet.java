/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlets.user.status;

import basicobj.Account;
import dbacess.AccountDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thinh
 */
public class registerServlet extends HttpServlet {

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
            String email = request.getParameter("txtemail");
            String fullname = request.getParameter("txtname");
            String pass = request.getParameter("txtpass");
            String phone = request.getParameter("txtphone");

            Account acc = null;
            try {
                acc = AccountDao.getAccountEmail(email);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{3}$")) {
                request.setAttribute("emailerror", "Email invalid!");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            } else if (!phone.matches("^[0-9]{10,11}$")) {
                request.setAttribute("phoneerror", "Phone invalid!");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            } else if (acc != null) {
                request.setAttribute("emaildupli", "Email existed!");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            } else {
                int status = 1;
                int role = 0;
                if (AccountDao.insertAccount(email, pass, fullname, phone, status, role)) {
                    request.setAttribute("email_newAcc", email);
                    request.setAttribute("otp", "otp");
                    RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
                    rd.forward(request, response);
                } else {
                    response.sendRedirect("errorpage.jsp");
                }
            }
        } catch (Exception ex) {
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
