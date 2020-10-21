package com.cs360.winesofcrete.server;

import com.cs360.winesofcrete.db.DebitCardDB;
import com.cs360.winesofcrete.db.UserDB;
import com.cs360.winesofcrete.model.DebitCard;
import com.cs360.winesofcrete.model.User;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "UpdateUserInformation", urlPatterns ={"/UpdateUserInformation"})
public class UpdateUserInformation extends HttpServlet
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
            RequestDispatcher rd = request.getRequestDispatcher("Starting");
            rd.include(request,response);
            return;
        }

        Map<String,String> results = new HashMap<>();
        String username = new String();
        username = session.getAttribute("username").toString();
        
        
        if (request.getParameterMap().containsKey("option"))
        {
            if (request.getParameter("option").equals("updateUserInfo"))
            {

                if (request.getParameterMap().containsKey("password") && request.getParameterMap().containsKey("email")
                        && request.getParameterMap().containsKey("name") && request.getParameterMap().containsKey("phoneNumber")
                        && request.getParameterMap().containsKey("address") && request.getParameterMap().containsKey("account_type")
                        && request.getParameterMap().containsKey("type") && request.getParameterMap().containsKey("expirationDate")
                        && request.getParameterMap().containsKey("debitCardNumber") && request.getParameterMap().containsKey("verificationCode"))
                {
                    try
                    {

                        User user = UserDB.getUser(username);
                        DebitCard debit = DebitCardDB.getDebitCardByNumber(user.getDebitCardNumber());
                        
                        
                        debit.setExpirationDate(request.getParameter("expirationDate"));
                        debit.setType(request.getParameter("type"));
                        debit.setVerificationCode(Integer.parseInt(request.getParameter("verificationCode")));
                        debit.setNumber(request.getParameter("debitCardNumber"));

                        user.setPassword(request.getParameter("password"));
                        user.setEmail(request.getParameter("email"));
                        user.setName(request.getParameter("name"));
                        user.setPhoneNumber(request.getParameter("phoneNumber"));
                        user.setAddress(request.getParameter("address"));
                        user.setAccountType(request.getParameter("account_type"));
                        
                        DebitCardDB.updateDebitCard(debit);
                        UserDB.updateUser(user);
;

                        String main_div = "<br><div id=\"inner_main_div\" align=\"center\">\n"
                        + "        <div class=\"parent_group\" id=\"personal_info\">\n"
                        + "            <p id=\"username\"><strong>Username:</strong> " + user.getUsername() + "</p>\n"
                        + "            <p id=\"password\"><strong>Password:</strong> " + user.getPassword() + "</p>\n"
                        + "            <p id=\"email\"><strong>Email:</strong> " + user.getEmail() + "</p>\n"
                        + "            <p id=\"name\"><strong>Name:</strong> " + user.getName() + "</p>\n"
                        + "            <p id=\"address\"><strong>Address:</strong> " + user.getAddress() + "</p>\n"
                        + "            <p id=\"account_type\"><strong>Account Type:</strong> " + user.getAccountType() + "</p>\n"
                        + "            <p id=\"debit_card_number\"><strong>Debit Card Number:</strong> " + debit.getNumber()+ "</p>\n"
                        + "            <p id=\"verification_code\"><strong>Verification Code:</strong> " + debit.getVerificationCode() + "</p>\n"
                        + "            <p id=\"expiration_date\"><strong>Expiration Date:</strong> " + debit.getExpirationDate() + "</p>\n"
                        + "            <p id=\"type\"><strong>Debit Card Type:</strong> " + debit.getType() + "</p>\n"
                        + "        </div>\n"
                        + "     </div>\n";
                        results.put("main_div", main_div);
                        response.setStatus(201);
                    }
                    catch (ClassNotFoundException ex)
                    {
                        response.setStatus(500);
                        Logger.getLogger(UpdateUserInformation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    response.setStatus(400);
                }
            }
            else if (request.getParameter("option").equals("emailCheck"))
            {
                if (request.getParameterMap().containsKey("username"))
                {
                    if (request.getParameterMap().containsKey("email"))
                    {
                        try
                        {
                            User user = UserDB.getUser(username);
                            if (user.getEmail().equals(request.getParameter("email")))
                            {
                                results.put("email", "OK");
                            }
                            else
                            {
                                boolean email_result = UserDB.checkValidEmail(request.getParameter("email"));
                                if (!email_result)
                                {
                                    results.put("email", "TAKEN");
                                }
                                else
                                {
                                    results.put("email", "OK");
                                }
                            }
                            response.setStatus(200);
                        }
                        catch (ClassNotFoundException ex)
                        {
                            response.setStatus(500);
                            Logger.getLogger(UpdateUserInformation.class.getName()).log(Level.SEVERE, null, ex);
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

        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter())
        {
            // Convert HashMap to JSON via googles GSON.
            // Dependancy for GSON added to maven.
            String jsonString = new Gson().toJson(results);
            out.write(jsonString);
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
