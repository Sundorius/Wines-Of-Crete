package com.cs360.winesofcrete.server;


import com.cs360.winesofcrete.db.DebitCardDB;
import com.cs360.winesofcrete.db.UserDB;
import com.cs360.winesofcrete.model.DebitCard;
import com.cs360.winesofcrete.model.User;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ChangeUserInformation", urlPatterns ={"/ChangeUserInformation"})
public class ChangeUserInformation extends HttpServlet
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
        HttpSession session = request.getSession(false);
        if (session == null)
        {
            response.setStatus(401);
            RequestDispatcher rd = request.getRequestDispatcher("Starting");
            rd.include(request,response);
            return;
        }

        try (PrintWriter out = response.getWriter())
        {
            out.print("<div id=\"inner_main_div\">\n");

            String username = new String();
            username = session.getAttribute("username").toString();
            try
            {
                User user = UserDB.getUser(username);
                DebitCard debit = DebitCardDB.getDebitCardByNumber(user.getDebitCardNumber());
                
                out.append("            <div align=\"center\" id=\"main\" class=\"col-sm-1 col-md-9 main\">"
                    + "		<div class=\"col-md-5\" id=\"username_div\">\n"
                    + "			<br><label id=\"username\"><strong>Username:</strong>&nbsp</label><label id=\"username_holder\">" + username + "</label><br>\n"
                    + "		</div>\n"
                    + "\n"
                    + "		<div class=\"col-md-5\">\n"
                    + "			<label for=\"password\" >Password</label>\n"
                    + "    		<input type=\"password\" class=\"form-control\" id=\"password\" value=\"" + user.getPassword() + "\" placeholder=\"Password\" pattern=\"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,10}$\" minlength=\"8\" maxlength=\"10\"  title=\"Password must contain at least a letter, a number and a symbol, of maximum length 10 chars.\" required >\n"
                    + "		</div>\n"
                    + "\n"
                    + "		<div class=\"col-md-5\">\n"
                    + "			<label for=\"password_confirm\">Password Validation</label>\n"
                    + "    		<input type=\"password\" class=\"form-control\" id=\"password_confirm\" placeholder=\"Password Validation\" minlength=\"8\" maxlength=\"10\"  title=\"This field must match the Password field.\" onkeyup=\"checkPassword()\" required >\n"
                    + "			<span id=\"password_result\"><strong>Must match Password.</strong></span><br><br>\n"
                    + "		</div>\n"
                    + "\n"
                    + "		<div class=\"col-md-5\">\n"
                    + "			<label for=\"email\">Email</label>\n"
                    + "   			<input type=\"email\" class=\"form-control\" id=\"email\" value=\"" + user.getEmail() + "\" placeholder=\"name@example.com\" pattern=\"[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$\"  title=\"Please include an '@' in the email address.\" onchange=\"validateEmailFromDB()\" required>\n"
                    + "            <span id=\"email_result\"><strong></strong></span>\n"
                    + "		</div>\n"
                    + "\n"
                    + "		<div class=\"col-md-5\">\n"
                    + "			<label for=\"name\">Name</label>\n"
                    + "			<br><input type=\"text\" class=\"form-control\" id=\"name\" value=\"" + user.getName() + "\" placeholder=\"Name\" name=\"name\" pattern=\"[A-Za-z]{0,20}\" maxlength=\"20\" title=\"Name can only contain letters, e.g. John. Min length: 0  Max length: 20. \" required>\n"
                    + "		</div>\n"
                    + "\n"
                    + "		<div class=\"col-md-4\">\n"
                    + "			<label for=\"phone\">Phone Number</label>\n"
                    + "			<br><input type=\"text\" class=\"form-control\" id=\"phone\" value=\"" + user.getPhoneNumber()+ "\"placeholder=\"Phone\" name=\"phone\" pattern=\"[0-9]{10,10}\" minlength=\"10\" maxlength=\"10\" title=\"A mobile phone number must have 10 digits ranging from 0 to 9\" required"
                    + "		</div>\n"
                    + "\n");
                switch (user.getAccountType().toLowerCase())
                {
                    case "customer":
                    {
                        out.append("	  	<div class=\"col-md-4\" id=\"account_type_div\" required>\n"
                    + "	  		<label for=\"gender\">Account Type</label><br>\n"
                    + "		  	<input required type=\"radio\" name=\"account_type\" value=\"customer\" checked=\"checked\"> Customer<br>\n"
                    + "		  	<input required type=\"radio\" name=\"account_type\" value=\"merchant\"> Merchant<br>\n"
                    + "		</div>\n");
                        break;
                    }
                    case "merchant":
                    {
                        out.append( "	  	<div class=\"col-md-4\" id=\"account_type_div\" required>\n"
                    + "	  		<label for=\"gender\">Account Type</label><br>\n"
                    + "		  	<input required type=\"radio\" name=\"account_type\" value=\"customer\" > Customer<br>\n"
                    + "		  	<input required type=\"radio\" name=\"account_type\" value=\"merchant\" checked=\"checked\"> Merchant<br>\n"
                    + "		</div>\n");
                        break;
                    }
                }

                    out.append("			<div class=\"col-md-4col-md-offset-5\" id=\"city_address\" >\n"
                    + "				<br><label for=\"address\">Address</label>\n"
                    + "				<br><input type=\"text\" class=\"form-control\" id=\"address\" value=\"" + user.getAddress()+ "\" placeholder=\"Address\" name=\"address\" required>\n"
                    + "			</div>\n"
                    +"\n"
                    + "			<div class=\"col-md-4col-md-offset-5\" >\n"
                    + "				<br><label for=\"debit_card_number\">Debit Card Number</label>\n"
                    + "				<br><input type=\"text\" class=\"form-control\" id=\"debit_card_number\" placeholder=\"Debit Card Number\" value=\"" +debit.getNumber()+ "\" pattern=\"[0-9]{16,16}\" minlength=\"12\" maxlength=\"16\" name=\"debit_card_number\" required>\n"
                    + "			</div>\n"
                    + "\n"
                    + "			<div class=\"col-md-4col-md-offset-5\" >\n"
                    + "				<br><label for=\"verification_code\">Verification Code</label>\n"
                    + "				<br><input type=\"text\" class=\"form-control\" id=\"verification_code\" value=\"" + debit.getVerificationCode()+ "\" pattern=\"[0-9]{4,4}\" minlength=\"4\" maxlength=\"4\" placeholder=\"Verification Code\" name=\"verification_code\" required>\n"
                    + "			</div>\n"
                    + "\n"
                    + "         <div class=\"col-md-4col-md-offset-5\">\n"
                    + "             <label for=\"expires\" >Expiration Date</label>\n"
                    + "             <div class=\"col-md-4col-md-offset-5\">\n"
                    + "                 <input id=\"expiration_date\" value=\"" + debit.getExpirationDate()+ "\" type=\"date\" required>"
                    + "             </div>"
                    + "         </div>"
                    + "\n"
                    + "			<div class=\"col-md-4col-md-offset-5\">\n"
                    + "				<br><label for=\"type\">Type</label>\n"
                    + "				<br><input type=\"text\" class=\"form-control\" id=\"type\" value=\"" + debit.getType()+ "\"placeholder=\"Type e.g. Visa, MasterCard\" name=\"type\" required>\n"
                    + "			</div>\n"
                    + "\n"
                    + "		<div class=\"col-md-2\">\n"
                    + "			<input type=\"submit\" value=\"Update Information\" class=\"btn btn-primary\" onclick=\"updateUserInformation()\">\n"
                    + "		</div>\n"
                    + "   </div>\n");
                   // + "	</body>"
                    //+ "</html>");

                response.setStatus(200);
            }
            catch (ClassNotFoundException ex)
            {
                response.setStatus(500);
                Logger.getLogger(ChangeUserInformation.class.getName()).log(Level.SEVERE, null, ex);
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
