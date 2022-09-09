/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlets.manager.plant;

import basicobj.Plant;
import dbacess.PlantDao;
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
public class managePlantsServlet extends HttpServlet {

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
            ArrayList<Plant> listPlant = new ArrayList<>();
            Plant plant = null;
            String pid = (String) request.getAttribute("plantUpdatedID");
            int newpid = 0;
            try {
                newpid = PlantDao.getNewestPlantId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            String created = (String) request.getAttribute("isCreated");
            
            if (pid != null && !pid.equals("") && !pid.isEmpty() ) {
                int updatePlantID = Integer.parseInt(pid);
                try {
                    plant = PlantDao.getPlant(updatePlantID);
                    listPlant.add(plant);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                request.setAttribute("success", "Plant update successfull");
            } else if (created != null && !created.equals("") && !created.isEmpty()) {
                try {
                    plant = PlantDao.getPlant(newpid);
                    listPlant.add(plant);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                request.setAttribute("success", "Plant create successfull");
            } else {
                try {
                    listPlant = PlantDao.getAllPlants();
                } catch (Exception ex) { 
                    ex.printStackTrace();
                }
            }
            request.setAttribute("listPlant", listPlant);
            request.getRequestDispatcher("ManagePlants.jsp").forward(request, response);
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
