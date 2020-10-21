package com.cs360.winesofcrete.server;

import com.cs360.winesofcrete.db.DebitCardDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cs360.winesofcrete.model.User;
import com.cs360.winesofcrete.db.UserDB;
import com.cs360.winesofcrete.model.DebitCard;
/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
@WebServlet(name = "SuccessfulRegistration", urlPatterns ={"/SuccessfulRegistration"})
public class SuccessfulRegistration extends HttpServlet
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
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter())
        {
            out.print("<div id=\"inner_main_div\">\n");

            if (request.getParameterMap().containsKey("username"))
            {
                try
                {
                    User user = UserDB.getUser(request.getParameter("username"));
                    DebitCard debitCard = DebitCardDB.getDebitCardByNumber(user.getDebitCardNumber());
                    out.append("<br><div id=\"inner_main_div\" align=\"center\">\n"
                        + "        <div class=\"parent_group\" id=\"personal_info\">\n"
                        + "            <p id=\"username\"><strong>Username:</strong> " + user.getUsername() + "</p>\n"
                        + "            <p id=\"password\"><strong>Password:</strong> " + user.getPassword() + "</p>\n"
                        + "            <p id=\"email\"><strong>Email:</strong> " + user.getEmail() + "</p>\n"
                        + "            <p id=\"name\"><strong>Name:</strong> " + user.getName() + "</p>\n"
                        + "            <p id=\"address\"><strong>Address:</strong> " + user.getAddress() + "</p>\n"
                        + "            <p id=\"account_type\"><strong>Account Type:</strong> " + user.getAccountType() + "</p>\n"
                        + "            <p id=\"debit_card_number\"><strong>Debit Card Number:</strong> " + debitCard.getNumber()+ "</p>\n"
                        + "            <p id=\"verification_code\"><strong>Verification Code:</strong> " + debitCard.getVerificationCode() + "</p>\n"
                        + "            <p id=\"expiration_date\"><strong>Expiration Date:</strong> " + debitCard.getExpirationDate() + "</p>\n"
                        + "            <p id=\"type\"><strong>Debit Card Type:</strong> " + debitCard.getType() + "</p>\n"
                        + "        </div>\n"
                        + "     </div>\n");
                    response.setStatus(200);
                }
                catch (ClassNotFoundException ex)
                {
                    response.setStatus(500);
                    Logger.getLogger(SuccessfulRegistration.class.getName()).log(Level.SEVERE, null, ex);
                }
                     
            }
            else
            {
                response.setStatus(400);
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
