/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wikisearch.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.wikisearch.client.WikiSearchWebService;
import org.wikisearch.client.WikiSearchWebService_Service;

/**
 *
 * @author santiago
 */
@WebServlet(name = "WikiSearchServlet", urlPatterns = {"/WikiSearchServlet"})
public class WikiSearchServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nextPage;
        HttpSession session = request.getSession(true);

        /*
         * Al presionar Search al usuario se le muestra una página con algunos resultados obtenidos.
         * El número de resultados es en general 10.
         */
        if (request.getParameter("doSearch") != null) {
            try {
                ArrayList<String> result = new ArrayList<>();
                WikiSearchWebService_Service service = new WikiSearchWebService_Service();
                WikiSearchWebService port = service.getWikiSearchWebServicePort();
                result = (ArrayList<String>) port.doSearch(request.getParameter("keyword"));
                // Si no hay resultados se lleva al usuario a una pagina de error
                if (result.isEmpty()) {
                    nextPage = "/error.jsp";
                    reSend(request, response, nextPage);
                } else {
                    // En caso de que si haya resultados se redirige a la página donde se mostrarán.
                    session.setAttribute("result", result);
                    nextPage = "/result.jsp";
                    reSend(request, response, nextPage);
                }
            } catch (Exception ex) {
                Logger.getLogger(WikiSearchServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /*
         * Al presionar I'm Feeling Lucky el usuario sera redirigido a una página al azar de entre
         * todas las posibilidades (max 10) encontradas.
         */
        if (request.getParameter("feelingLucky") != null) {
            try {
                ArrayList<String> result = new ArrayList<>();
                WikiSearchWebService_Service service = new WikiSearchWebService_Service();
                WikiSearchWebService port = service.getWikiSearchWebServicePort();
                result = (ArrayList<String>) port.doSearch(request.getParameter("keyword"));
                // Si no hay resultados se lleva al usuario a una pagina de error
                if (result.isEmpty()) {
                    nextPage = "/error.jsp";
                    reSend(request, response, nextPage);
                } else {
                    Random rand = new Random();
                    ArrayList<String> titles = new ArrayList<>();
                    Iterator<String> resultIterator = result.iterator();
                    // Por facilidad primero creamos un ArrayList que sólo contenga los titulos de los resultados, 
                    // esto se hace porque la url de wikipedia es de la forma http://www.wikipedia.org/wiki/<title>.
                    // Los titulos estan en las posiciones pares del ArrayList (0, 2, 4, ...)
                    while (resultIterator.hasNext()) {
                        titles.add(resultIterator.next());
                        resultIterator.next();
                    }
                    // El número aleatorio debe estar entre 0 y el tamaño del ArrayList (size - 1)
                    int value = rand.nextInt(titles.size() - 1);
                    nextPage = "http://www.wikipedia.org/wiki/" + titles.get(value);
                    // redirigimos a http://www.wikipedia.org/wiki/<title>.
                    doGetCustom(request, response, nextPage);
                }
            } catch (Exception ex) {
                Logger.getLogger(WikiSearchServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        if (request.getParameter("back") != null) {
            nextPage = "/index.jsp";
            reSend(request, response, nextPage);
        }

        if (request.getParameter("backError") != null) {
            nextPage = "/index.jsp";
            reSend(request, response, nextPage);
        }

    }
    
    /**
     * Esta función permite redirigir a una web site, como por ejemplo a http://www.google.com
     * 
     * @param request
     * @param response
     * @param nextPage
     * @throws IOException 
     */
    public void doGetCustom(HttpServletRequest request, HttpServletResponse response, String nextPage) throws IOException{
        response.sendRedirect(nextPage);
    }

    public void reSend(HttpServletRequest request, HttpServletResponse response, String npage) {
        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(npage);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(WikiSearchServlet.class.getName()).log(Level.SEVERE, null, ex);
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
