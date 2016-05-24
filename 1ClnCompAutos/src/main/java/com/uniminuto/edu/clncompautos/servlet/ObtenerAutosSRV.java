/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.clncompautos.servlet;

import com.uniminuto.edu.clncompautos.wscln.CompAutosWS;
import com.uniminuto.edu.clncompautos.wscln.CompAutosWS_Service;
import com.uniminuto.edu.clncompautos.wscln.VntCarro;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author fercris
 */
@WebServlet(name = "ObtenerAutosSRV", urlPatterns = {"/ObtenerAutosSRV"})
public class ObtenerAutosSRV extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_38080/2WsCompAutos/CompAutosWS.wsdl")
    private CompAutosWS_Service service;

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

            try { // Call Web Service Operation
                CompAutosWS port = service.getCompAutosWSPort();
                // TODO process result here
                List<VntCarro> result = port.getLstCarros();
                out.println(" <table id=\"tablaAutos\" >");
                out.println("<tbody>");

                result.stream().forEach((vc) -> {
                    out.println("<tr><td>" + vc.getCarNombre() + "</td><td>" + vc.getCarFoto() + "</td></tr>");
                });
                out.println(" </tbody>");
                out.println("</table>");

            } catch (Exception ex) {
                // TODO handle custom exceptions here
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
