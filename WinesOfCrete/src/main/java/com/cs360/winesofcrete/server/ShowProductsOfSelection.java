package com.cs360.winesofcrete.server;

import com.cs360.winesofcrete.db.ConsistsOfDB;
import com.cs360.winesofcrete.db.VarietyDB;
import com.cs360.winesofcrete.db.WineDB;
import com.cs360.winesofcrete.model.ConsistsOf;
import com.cs360.winesofcrete.model.Wine;
import com.cs360.winesofcrete.model.Variety;
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
@WebServlet(name = "ShowProductsOfSelection", urlPatterns ={"/ShowProductsOfSelection"})
public class ShowProductsOfSelection extends HttpServlet
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
            out.append("<!-- /for products div --><br><div id=\"products\" align=\"center\">\n");
            if(request.getParameterMap().containsKey("color")
                    || request.getParameterMap().containsKey("year")
                    || request.getParameterMap().containsKey("winery"))
            {
                if(request.getParameterMap().containsKey("color"))
                {
                    try 
                    {
                        ArrayList<Wine> wines = (ArrayList<Wine>) WineDB.getWinesByColor(request.getParameter("color"));
                        for(Wine wine: wines)
                        {
                            out.append("<!--Card-->\n" +
                                "<div class=\"card\">\n" +
                                "\n" +
                                "    <!--Card image-->\n" +
                                "    <div class=\"view overlay hm-white-slight\">\n" +
                                "        <img src=\""+wine.getImg()+"\" class=\"img-fluid\" alt=\"\">\n" +
                                "        <a href=\"#\">\n" +
                                "            <div class=\"mask\"></div>\n" +
                                "        </a>\n" +
                                "    </div>\n" +
                                "\n" +
                                "    <!--Card content-->\n" +
                                "    <div class=\"card-body\">\n" +
                                "        <!--Title-->\n" +
                                "        <h4 class=\"card-title\">"+wine.getName()+"</h4>\n" +
                                "        <!--Text-->\n" +
                                "        <p class=\"card-text\"><br>"+
                                "           <strong>Color:</strong> "+wine.getColor()+"<br>\n"+
                                "           <strong>Year: </strong>"+wine.getYear()+"<br>\n"+
                                "           <strong>Winery: </strong>"+wine.getWinery()+"<br>\n"+
                                "           <strong>Varieties: </strong>");
                            ArrayList<ConsistsOf> consists = (ArrayList<ConsistsOf>) ConsistsOfDB.getConsistsOfByProductID(wine.getProductID());
                            for(ConsistsOf consist: consists)
                            {
                                Variety variety = VarietyDB.getVariety(consist.getVarietyID());
                                out.append(variety.getName()+", ");
                            }
                     out.append("<br>           <font color=\"red\"><strong>Price: </strong></font>"+wine.getPrice()+"\n" +
                                "       </p>\n"+
                                "        <a href=\"#\" onclick=\"addToBasket('"+wine.getName()+"',quantity"+wine.getProductID()+".value)\" class=\"btn btn-primary\">Add to Basket</a><input type=\"number\" id=\"quantity"+wine.getProductID()+"\" min=\"\" max=\""+wine.getQuantity()+"\" value=\"0\">\n" +
                                "    </div>\n" +
                                "\n" +
                                "</div>\n" +
                                "<!--/.Card-->");
                        }
                    }
                    catch(ClassNotFoundException ex) 
                    {
                        response.setStatus(400);
                        Logger.getLogger(ShowProductsOfSelection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(request.getParameterMap().containsKey("year"))
                {
                    try 
                    {
                        ArrayList<Wine> wines = WineDB.getWinesByYear(request.getParameter("year"));
                        for(Wine wine: wines)
                        {
                            out.append("<!--Card-->\n" +
                                "<div class=\"card\">\n" +
                                "\n" +
                                "    <!--Card image-->\n" +
                                "    <div class=\"view overlay hm-white-slight\">\n" +
                                "        <img src=\""+wine.getImg()+"\" class=\"img-fluid\" alt=\"\">\n" +
                                "        <a href=\"#\">\n" +
                                "            <div class=\"mask\"></div>\n" +
                                "        </a>\n" +
                                "    </div>\n" +
                                "\n" +
                                "    <!--Card content-->\n" +
                                "    <div class=\"card-body\">\n" +
                                "        <!--Title-->\n" +
                                "        <h4 class=\"card-title\">"+wine.getName()+"</h4>\n" +
                                "        <!--Text-->\n" +
                                "        <p class=\"card-text\"><br>"+
                                "           <strong>Color: </strong>"+wine.getColor()+"<br>\n"+
                                "          <strong> Year: </strong>"+wine.getYear()+"<br>\n"+
                                "           <strong>Winery: </strong>"+wine.getWinery()+"<br>\n" +
                                "           <strong>Varieties: </strong>");
                            ArrayList<ConsistsOf> consists = (ArrayList<ConsistsOf>) ConsistsOfDB.getConsistsOfByProductID(wine.getProductID());
                            for(ConsistsOf consist: consists)
                            {
                                Variety variety = VarietyDB.getVariety(consist.getVarietyID());
                                out.append(variety.getName()+", ");
                            }
                     out.append("<br>           <font color=\"red\"><strong>Price: </strong></font>"+wine.getPrice()+"\n" +
                                "       </p>\n"+
                                "        <a href=\"#\" onclick=\"addToBasket('"+wine.getName()+"',quantity"+wine.getProductID()+".value)\" class=\"btn btn-primary\">Add to Basket</a><input type=\"number\" id=\"quantity"+wine.getProductID()+"\" min=\"0\" max=\""+wine.getQuantity()+"\" value=\"0\">\n" +
                                "    </div>\n" +
                                "\n" +
                                "</div>\n" +
                                "<!--/.Card-->");
                        }

                    }
                    catch(ClassNotFoundException ex) 
                    {
                        response.setStatus(400);
                        Logger.getLogger(ShowProductsOfSelection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(request.getParameterMap().containsKey("winery"))
                {
                    try 
                    {
                        ArrayList<Wine> wines = (ArrayList<Wine>) WineDB.getWinesByWinery(request.getParameter("winery"));
                        for(Wine wine: wines)
                        {
                            out.append("<!--Card-->\n" +
                                "<div class=\"card\">\n" +
                                "\n" +
                                "    <!--Card image-->\n" +
                                "    <div class=\"view overlay hm-white-slight\">\n" +
                                "        <img src=\""+wine.getImg()+"\" class=\"img-fluid\" alt=\"\">\n" +
                                "        <a href=\"#\">\n" +
                                "            <div class=\"mask\"></div>\n" +
                                "        </a>\n" +
                                "    </div>\n" +
                                "\n" +
                                "    <!--Card content-->\n" +
                                "    <div class=\"card-body\">\n" +
                                "        <!--Title-->\n" +
                                "        <h4 class=\"card-title\">"+wine.getName()+"</h4>\n" +
                                "        <!--Text-->\n" +
                                "        <p class=\"card-text\"><br>"+
                                "           <strong>Color: </strong>"+wine.getColor()+"<br>\n"+
                                "           <strong>Year: </strong>"+wine.getYear()+"<br>\n"+
                                "           <strong>Winery: </strong>"+wine.getWinery()+"<br>\n" +
                                "           <strong>Varieties: </strong>");
                            ArrayList<ConsistsOf> consists = (ArrayList<ConsistsOf>) ConsistsOfDB.getConsistsOfByProductID(wine.getProductID());
                            for(ConsistsOf consist: consists)
                            {
                                Variety variety = VarietyDB.getVariety(consist.getVarietyID());
                                out.append(variety.getName()+", ");
                            }
                     out.append("<br>           <font color=\"red\"><strong>Price: </strong></font>"+wine.getPrice()+"\n" +
                                "       </p>\n"+
                                "        <a href=\"#\" onclick=\"addToBasket('"+wine.getName()+"',quantity"+wine.getProductID()+".value)\" class=\"btn btn-primary\">Add to Basket</a><input type=\"number\" id=\"quantity"+wine.getProductID()+"\" min=\"0\" max=\""+wine.getQuantity()+"\" value=\"0\">\n" +
                                "    </div>\n" +
                                "\n" +
                                "</div>\n" +
                                "<!--/.Card-->");
                        }
                    }
                    catch(ClassNotFoundException ex) 
                    {
                        response.setStatus(400);
                        Logger.getLogger(ShowProductsOfSelection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    response.setStatus(400);
                }
            }
            else
            {
                response.setStatus(400);
            }
            out.append(" </div><!-- /for products div -->");
            response.setStatus(200);
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
