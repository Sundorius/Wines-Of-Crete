package com.cs360.winesofcrete.server;

import com.cs360.winesofcrete.db.OrderDB;
import com.cs360.winesofcrete.model.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "GetUserOrders", urlPatterns ={"/GetUserOrders"})
public class GetUserOrders extends HttpServlet {

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
                
        String username=(String)session.getAttribute("username");
        
        List<Order> orders;
        try (PrintWriter out = response.getWriter()) 
        {
            try
            {
                out.append("<br><div id=\"inner_main_div\" align=\"center\">\n");
                orders=OrderDB.getOrdersOfUser(username);
                if(!orders.isEmpty())
                {
                    out.println("<h1>These are your orders!</h1><br><br><br>");
                    out.println("<ul>");
                    for(Order order:orders)
                    {
                        out.println("<li>"
                                +"<strong>Date</strong>: "+order.getDate()
                                +"<br><strong>Status</strong>: ");
                        switch(order.getStatus()) {
                            case 0:
                                out.println("<strong>Unpaid!</strong> You have "+order.getDaysLeftToPay()+" days left to pay!"
                                        + "<br> <a href=\"#\" onclick=\"showPaymentMenu("+order.getOrderID()+")\"><strong>Pay for this order.</strong></a>"
                                        +"<br><strong>Amount Due: </strong> "+order.getAmountDue());
                                break;
                            case 1:
                                out.println("Paid!");
                                break;
                            default:
                                out.println("This is your basket!");
                                break;
                        }
                        out.println("<br><strong>Discount</strong>: "+order.getDiscount()
                                 +"<br><strong>Total Cost</strong>: "+order.getAmount()
                                +"</li>");
                    }
                    out.println("</ul><br><br><br>");
                }
                else
                {
                    out.println("<br><br><br><br><center><p> You have no orders! </p></center>\n");
                }
                out.print("</div>");
                      
            }
            catch(ClassNotFoundException e)
            {
                response.setStatus(500);
                out.println(e);
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
