package com.cs360.winesofcrete.server;

import com.cs360.winesofcrete.db.PaymentsDB;
import com.cs360.winesofcrete.model.Payments;
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
@WebServlet(name = "ShowTransactions", urlPatterns ={"/ShowTransactions"})
public class ShowTransactions extends HttpServlet
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
        
        String username = (String) session.getAttribute("username");
        System.out.print("username!!!!!!:   "+username);

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter())
        {
            out.append("<!-- /for inner main div --><br><div id=\"inner_main_div\" align=\"center\">\n");
            try
            {
                ArrayList<Payments> payments = (ArrayList<Payments>) PaymentsDB.getPaymentsOfUser(username);
                if(payments.size()>0)
                {
                    out.append("<div class=\"bs-example\" bad_customers_div=\"table-within-panel\">\n" +
                    "     <div class=\"panel panel-default\">\n" +
                    "     <div class=\"panel-heading\"><strong>Transactions</strong></div>\n" +
                    "     <div class=\"panel-body\"> \n" +
                    "     </div> <table class=\"table\">\n" +
                    "       <thead> \n" +
                    "       <tr>\n" +
                    "       <th>#</th>\n" +
                    "           <th>Amount</th> \n" +
                    "               </tr>\n" +
                    "               </thead> \n" +
                    "               <tbody> \n");
                    int counter = 1;
                    for(Payments payment: payments)
                    {
                        out.append( "  <tr>\n" +
                            "       <th scope=\"row\">"+counter+"</th>\n" +
                            "       <td>"+payment.getAmountPaid()+"</td> \n" +
                            "       </tr>\n");
                        counter ++;
                    }
                    out.append("</tr>\n" +
                        "    </tbody>\n" +
                        "     </table>\n" +
                        "     </div> \n" +
                        "     </div>"+
                        "   </div>");
                }
                else
                {
                    out.append("<br><br><br><br><center><p> There Are No Transactions</p></center></div>\n");
                }
                
                response.setStatus(200);
                
            }
            catch (ClassNotFoundException ex)
            {
                response.setStatus(500);
                Logger.getLogger(ShowTransactions.class.getName()).log(Level.SEVERE, null, ex);
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
