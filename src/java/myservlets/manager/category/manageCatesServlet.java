/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlets.manager.category;

import basicobj.Category;
import dbacess.CategoryDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thinh
 */
public class manageCatesServlet extends HttpServlet {

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
            ArrayList<Category> listCate = new ArrayList<>();
            Category cate;
            String pid = (String) request.getAttribute("cateUpdateID");
            int newpid = 0;
            try {
                newpid = CategoryDao.getNewestCateId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            String created = (String) request.getAttribute("isCreated");
            
            if (pid != null && !pid.equals("") && !pid.isEmpty() ) {
                int updatePlantID = Integer.parseInt(pid);
                try {
                    cate = CategoryDao.getCategory(updatePlantID);
                    listCate.add(cate);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                request.setAttribute("success", "Category update successfull");
            } else if (created != null && !created.equals("") && !created.isEmpty()) {
                try {
                    cate = CategoryDao.getCategory(newpid);
                    listCate.add(cate);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                request.setAttribute("success", "Category create successfull");
            } else {
                try {
                    listCate = CategoryDao.getAllCategories();
                } catch (Exception ex) { 
                    ex.printStackTrace();
                }
            }
            request.setAttribute("listCate", listCate);
            request.getRequestDispatcher("ManageCategory.jsp").forward(request, response);
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
