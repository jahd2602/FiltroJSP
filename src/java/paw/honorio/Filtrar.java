/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.honorio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paw.honorio.modelo.Producto;

/**
 *
 * @author Sistemas
 */
@WebServlet(name = "Filtrar", urlPatterns = {"/servlet/Filtrar"})
public class Filtrar extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Producto> lista = null;
        String strChkCategoria = request.getParameter("chkCategoria");
        String strChkMarca = request.getParameter("chkMarca");
        boolean filtrarCategoria = false;
        boolean filtrarMarca = false;
        if (strChkCategoria != null && strChkCategoria.equals("true")) {
            filtrarCategoria = true;
        }
        if (strChkMarca != null && strChkMarca.equals("true")) {
            filtrarMarca = true;
        }
        try {
            BeanDatos datos = new BeanDatos();
            if (filtrarCategoria) {
                if (filtrarMarca) {
                    lista = datos.getProductosPorCategoriaYMarca(new Integer(request.getParameter("categoria")), new Integer(request.getParameter("marca")));

                } else {
                    lista = datos.getProductosPorCategoria(new Integer(request.getParameter("categoria")));
                }
            } else {
                if (filtrarMarca) {
                    lista = datos.getProductosPorMarca(new Integer(request.getParameter("marca")));
                } else {
                    lista = datos.getProductosSinFiltro();
                }
            }
            request.setAttribute("Lista", lista);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
