package com.cs360.winesofcrete.server;

import com.cs360.winesofcrete.db.ContainsDB;
import com.cs360.winesofcrete.db.OrderDB;
import com.cs360.winesofcrete.db.WineDB;
import com.cs360.winesofcrete.model.Contains;
import com.cs360.winesofcrete.model.Order;
import com.cs360.winesofcrete.model.Wine;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
 * @author crow
 */
@WebServlet(name = "ShowBasket", urlPatterns ={"/ShowBasket"})
public class ShowBasket extends HttpServlet {

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
        Order order;
        List<Contains> containers;
        Wine wine;
        
        try (PrintWriter out = response.getWriter()) 
        {
            out.append("<br><div id=\"inner_main_div\" align=\"center\">\n");
            try
            {
                
                if(!OrderDB.getOrdersByStatus(2).isEmpty())
                {//If basket is not empty
                    out.println("<h1> These are the items that currently are in your basket:</h1><br><br><br>");
                    order=OrderDB.getOrdersByStatus(2).get(0);
                    containers=ContainsDB.getContainsByOrderID(order.getOrderID());
                    out.println("<ul>");
                    for(Contains cont:containers)
                    {
                        wine=WineDB.getWineByID(cont.getProductID());
                        out.println("<li>"
                                +"Name:"+wine.getName()
                                +"<br>ID:"+cont.getProductID()
                                +"<br>Price per unit:"+wine.getPrice()
                                +"<br>Quantity selected:"+cont.getQuantity()
                                +"</li>");
                    }
                    out.println("</ul><br><br><br>");
                    out.println("<button type=\"button\" onclick=\"makeOrder()\">Make an order!</button>");
                }
                else
                {
                    out.println("<br><br><br><br><center><p> Your Basket Is Empty! </p></center>");
                }
                out.append("</div>");
            }
            catch(ClassNotFoundException ex)
            {
                response.setStatus(500);
                Logger.getLogger(ShowBasket.class.getName()).log(Level.SEVERE, null, ex);
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
