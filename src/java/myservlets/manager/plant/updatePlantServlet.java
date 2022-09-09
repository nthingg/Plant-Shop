/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlets.manager.plant;

import basicobj.Category;
import basicobj.Plant;
import dbacess.CategoryDao;
import dbacess.PlantDao;
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
public class updatePlantServlet extends HttpServlet {

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
            String pid = request.getParameter("pid");
            String plantName = request.getParameter("plantName");
            String plantPrice = request.getParameter("plantPrice");
            String plantImg = request.getParameter("plantImg");
            String plantDescription = request.getParameter("plantDescription");
            String plantStatus = request.getParameter("plantStatus");
            String cateID = request.getParameter("cateID");
            
            if (cateID != null && !cateID.equals("") && !cateID.isEmpty()) {
                int cID = Integer.parseInt(cateID);
                ArrayList<Category> cateList = null;
                try {
                    cateList = CategoryDao.getAllCategories();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                boolean checkCate = false;
                for (Category category : cateList) {
                    if (category.getCateID() == cID) {
                        checkCate = true;
                    }
                }
                if (checkCate == false) {
                    request.setAttribute("alert", "Category not existed!!!");
                    request.getRequestDispatcher("plantsUpdate.jsp").forward(request, response);
                }
            }
            
            boolean result = PlantDao.updatePlant(pid, plantName, plantPrice, plantImg, plantDescription, plantStatus, cateID);
            if (result) {
                request.setAttribute("plantUpdatedID", pid);
                request.getRequestDispatcher("managePlantsServlet").forward(request, response);
            } else {
                request.setAttribute("alert", "update failed");
                request.getRequestDispatcher("plantsUpdate.jsp").forward(request, response);
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
