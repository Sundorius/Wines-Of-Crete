package com.cs360.winesofcrete.server;

import com.cs360.winesofcrete.db.WineDB;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
@WebServlet(name = "ShowAllProducts", urlPatterns ={"/ShowAllProducts"})
public class ShowAllProducts extends HttpServlet
{

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
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession(false);
        if (session == null) 
        {
            response.setStatus(401);
            return;
        }

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter())
        {
            out.append("<!-- /for inner main div --><br><div id=\"inner_main_div\" align=\"center\">\n");
            try
            {
                
                // Colors of Wines
                out.append("  <div class=\"btn-group\">\n" +
                            "    <button type=\"button\" class=\"btn btn-primary dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">Color</button>\n" +
                            "    <div class=\"dropdown-menu\" x-placement=\"bottom-start\" style=\"position: absolute; transform: translate3d(0px, 38px, 0px); top: 0px; left: 0px; will-change: transform;\">\n" +
                            "      <a class=\"dropdown-item\" onclick=\"showProductsOfSelection('color=red')\" href=\"#\">Red</a>\n" +
                            "      <a class=\"dropdown-item\" onclick=\"showProductsOfSelection('color=rose')\" href=\"#\">Rose</a>\n" +
                            "      <a class=\"dropdown-item\" onclick=\"showProductsOfSelection('color=white')\" href=\"#\">White</a>\n" +
                            "    </div>\n" +
                            "  </div><!-- /btn-group -->\n" +
                        
                        // Years of Wines
                            "  <div class=\"btn-group\">\n" +
                            "    <button type=\"button\" class=\"btn btn-secondary dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">Year</button>\n" +
                            "    <div class=\"dropdown-menu\" x-placement=\"bottom-start\" style=\"position: absolute; transform: translate3d(0px, 38px, 0px); top: 0px; left: 0px; will-change: transform;\">\n");
                            ArrayList<String> years = WineDB.getYearsOfWines();
                            for(int i=0; i<years.size(); i++)
                            {
                                out.append("    <a class=\"dropdown-item\" onclick=\"showProductsOfSelection('year='+this.innerHTML)\" href=\"#\">"+years.get(i)+"</a>\n");
                            }
                            out.append("    </div>\n" +
                            "  </div><!-- /btn-group -->\n" +
                                    
                                    
                            // Wineries of Wines
                            "  <div class=\"btn-group\">\n" +
                            "    <button type=\"button\" class=\"btn btn-secondary dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">Wineries</button>\n" +
                            "    <div class=\"dropdown-menu\" x-placement=\"bottom-start\" style=\"position: absolute; transform: translate3d(0px, 38px, 0px); top: 0px; left: 0px; will-change: transform;\">\n");
                            ArrayList<String> wineries = WineDB.getWineriesOfWines();
                            for(int i=0; i<wineries.size(); i++)
                            {
                                out.append("    <a class=\"dropdown-item\" onclick=\"showProductsOfSelection('winery='+this.innerHTML)\" href=\"#\">"+wineries.get(i)+"</a>\n");
                            }
                            out.append("    </div>\n" +
                            "  </div><!-- /btn-group -->\n"+
                            " </div><!-- /for inner main div -->");
                            
                    out.append("<!-- /for selection div --><br><div id=\"selection_div\" align=\"center\"></div><!-- /for selection div -->\n");
                            
                response.setStatus(200);
            }
            catch (ClassNotFoundException ex)
            {
                response.setStatus(500);
                Logger.getLogger(ShowAllProducts.class.getName()).log(Level.SEVERE, null, ex);
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
