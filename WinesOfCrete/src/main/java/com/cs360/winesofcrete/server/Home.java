/*
@file:   Home.java
 */
package com.cs360.winesofcrete.server;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Home",urlPatterns ={"/Home"})
public class Home extends HttpServlet
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

            out.println("<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Wines of Crete</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "\n"
                + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" crossorigin=\"anonymous\">\n"
                + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/base.css\">\n"
                + "\n"
                + "        <script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" crossorigin=\"anonymous\"></script>\n"
                + "        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js\"  crossorigin=\"anonymous\"></script>\n"
                + "        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\" crossorigin=\"anonymous\"></script>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <nav class=\"navbar navbar-dark bg-dark navbar-fixed-top\">\n"
                + "            <div class=\"container-fluid\">\n"
                + "                <div class=\"navbar-header\">\n"
                + "                    <label class=\"navbar-brand\">Wines of Crete</label>\n"
                + "                </div>\n"
                + "                <div class=\"navbar-right\">\n"
                + "                    <ul class=\"nav\">\n"
                + "                        <li class=\"nav-item space-after\">\n"
                + "                            <a class=\"navbar-brand\" href=\"#\" onclick=\"getUserOrders()\">\n"
                + "                                <div id=\"username_holder\" class=\"username_holder\">"
                + session.getAttribute("username").toString()
                + "                                </div>\n"
                + "                            </a>\n"
                + "                        </li>\n"
                + "                        <li class=\"nav-item space-after\">\n"
                + "                            <a class=\"navbar-brand\" href=\"#\" onclick=\"showAllProducts()\">All Products</a>\n"
                + "                        </li>\n"
                + "                        <li class=\"nav-item\">\n"
                + "                            <a class=\"navbar-brand\" href=\"#\" onclick=\"logOut()\">Logout</a>\n"
                + "                        </li>\n"
                + "                    </ul>\n"
                + "                </div>\n"
                + "        </nav>\n"
                + "        <div class=\"row\">\n"
                + "            <div class=\"col-sm-2 sidebar my-sidebar\">\n"
                + "                <ul class=\"nav-sidebar\" >\n"
                + "                    <li class=\"active\">\n"
                + "                        <a href=\"#\" onclick=\"showAllProducts()\">All Products</a>\n"
                + "                    </li>\n"
                + "                    <li>\n"
                + "                        <a href=\"#\" onclick=\"showTopProducts()\">Top 10 Products - Wineries </a>\n"
                + "                    </li>\n"
                + "                    <li>\n"
                + "                        <a href=\"#\" onclick=\"showBadCustomers()\">Bad Customers</a>\n"
                + "                    </li>\n"
                + "                    <li>\n"
                + "                        <a href=\"#\" onclick=\"showGoodCustomers()\">Good Customers</a>\n"
                + "                    </li>\n"
                + "                    <li>\n"
                + "                        <a href=\"#\" onclick=\"showTransactions()\">Show Transactions History</a>\n"
                + "                    </li>\n"
                + "                    <li>\n"
                + "                        <a href=\"#\" onclick=\"changeUserInformation()\">Change Personsal Information</a>\n"
                + "                    </li>\n"
                + "                </ul>\n"
                + "            </div>\n"
                + "            <div id=\"main_div\" class=\"col-sm-8 main\" style=\"text-align: left;\">"
                // TODO fill main div!!
                + "            </div>\n"
                + "            <div id=\"right_div\" class=\"col-sm-2 sidebar my-sidebar col-right\">"
                + "              <ul id=\"inner_right_div\">\n"
                + "                 <li>"
                + "                     <a onclick=\"showBasket()\" href=\"#\">Basket</a>\n"
                + "                 </li>"
                + "                 <li>"
                + "                     <a onclick=\"getUserOrders()\" href=\"#\">Show Orders</a>\n"
                + "                 </li>"
                + "                 <li>"
                + "                     <a onclick=\"returnProductOrder()\" href=\"#\">Return Product-Order</a>\n"
                + "                 </li>"
                + "                 <li>"
                + "                     <a onclick=\"deleteAccount()\" href=\"#\">Delete Account</a>\n"
                + "                 </li>"
                + "             </ul>"
                + "           </div>"
                + "        </div>\n"
                + "\n"
                // TODO ADD ALL THE NECESSARY .js FILED BELOW!!!

                + "        <script src=\"js/account.js\"></script>\n"
                + "        <script src=\"js/basket.js\"></script>\n"
                + "        <script src=\"js/orders.js\"></script>\n"
                + "        <script src=\"js/logout.js\"></script>\n"
                + "        <script src=\"js/payOrder.js\"></script>\n"
                + "        <script src=\"js/showAllProducts.js\"></script>\n"
                + "        <script src=\"js/showBadCustomers.js\"></script>\n"
                + "        <script src=\"js/showGoodCustomers.js\"></script>\n"
                + "        <script src=\"js/showProductsOfSelection.js\"></script>\n"
                + "        <script src=\"js/showTopProducts.js\"></script>\n"
                + "        <script src=\"js/showTransactions.js\"></script>\n"
                + "        <script src=\"js/changeUserInformation.js\"></script>\n"
                + "        <script src=\"js/updateUserInformation.js\"></script>\n"
                + "\n"
                + "    </body>\n"
                + "</html>\n"
            );
        }
        response.setStatus(200);
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
