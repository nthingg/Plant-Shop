/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlets.user.status;

import basicobj.Account;
import basicobj.Order;
import dbacess.AccountDao;
import dbacess.OrderDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thinh
 */
public class loginServlet extends HttpServlet {

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
            HttpSession session = request.getSession(true);
            String email = request.getParameter("txtemail");
            String pass = request.getParameter("txtpass");
            String save = request.getParameter("savelogin");
            ArrayList<Order> listOrder = new ArrayList<>();
            Account acc = null;
            Cookie cookieCheck = null;
            boolean checkCookie = false;
            try {
                if (email == null || email.equals("") || pass == null || pass.equals("")) {
                    Cookie[] c = request.getCookies();
                    String token = "";
                    if (c != null) {
                        for (Cookie aCookie : c) {
                            if (aCookie.getName().equals("selector")) {
                                checkCookie = true;
                                token = aCookie.getValue();
                                //kiem tra token co ton tai trong db hay k, neu co tra ve acc
                                acc = AccountDao.getAccount(token);
                                if (acc != null) {
                                    String newName = acc.getFullname();
                                    String newEmail = acc.getEmail();
                                    session.setAttribute("name", newName);
                                    session.setAttribute("email", newEmail);
                                    try {
                                        listOrder = OrderDao.getOrders(newEmail);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    session.setAttribute("listOrder", listOrder);
                                } else {
                                    response.sendRedirect("index.jsp");
                                }
                            }
                        }
                        if (checkCookie == false) {
                            response.sendRedirect("index.jsp");
                        }
                        if (acc.getRole() == 1) {
                            response.sendRedirect("AdminIndex.jsp");
                        } else {
                            response.sendRedirect("personalPage.jsp");
                        } 
                    } else {
                        response.sendRedirect("index.jsp");
                    }
                } else {
                    acc = AccountDao.getAccount(email, pass);
                    if (acc != null) {
                        if (acc.getRole() == 1) {
                            HttpSession sessionAdmin = request.getSession(true);
                            if (sessionAdmin != null) {
                                sessionAdmin.setAttribute("name", acc.getFullname());
                                sessionAdmin.setAttribute("email", email);
                                if (save != null) {
                                    Random rand = new Random();
                                    int maxNumber = 1000000000;
                                    int tokenNum = rand.nextInt(maxNumber) + 1;
                                    String token = String.valueOf(tokenNum);
                                    AccountDao.updateAccountToken(email, token);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60*60);
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("AdminIndex.jsp");
                            }
                        } else {
                            HttpSession sessionUser = request.getSession(true);
                            if (sessionUser != null) {
                                sessionUser.setAttribute("name", acc.getFullname());
                                sessionUser.setAttribute("email", email);
                                try {
                                    listOrder = OrderDao.getOrders(email);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                sessionUser.setAttribute("listOrder", listOrder);
                                if (save != null) {
                                    Random rand = new Random();
                                    int maxNumber = 1000000000;
                                    int tokenNum = rand.nextInt(maxNumber) + 1;
                                    String token = String.valueOf(tokenNum);
                                    AccountDao.updateAccountToken(email, token);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60 * 60);
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("personalPage.jsp");
                            }
                        }
                    } else {
                        request.setAttribute("errorlogin", "errorlogin");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
