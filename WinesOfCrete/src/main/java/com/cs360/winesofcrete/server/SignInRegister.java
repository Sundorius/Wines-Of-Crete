package com.cs360.winesofcrete.server;
;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
@WebServlet(name = "SignInRegister",urlPatterns ={"/SignInRegister"})
public class SignInRegister extends HttpServlet
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
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter())
        {
            if (request.getParameterMap().containsKey("option"))
            {
                if (request.getParameter("option").equals("signIn"))
                {
                    out.print("<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "   <head>\n"
                        + "        <title>Wines of Crete</title>\n"
                        + "        <meta charset=\"UTF-8\">\n"
                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "\n"
                        + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" crossorigin=\"anonymous\">\n"
                        + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/base.css\">\n"
                        + "        <script src=\"js/signIn.js\"></script>\n"
                        + "    </head>\n"
                        + "\n"
                        + "    <body>\n"
                        + "         <nav class=\"navbar navbar-dark bg-dark navbar-fixed-top\">\n"
                        + "            <div class=\"container-fluid\">\n"
                        + "                <div class=\"navbar-header\">\n"
                        + "                    <label class=\"navbar-brand\">Wines of Crete</label>\n"
                        + "                </div>\n"
                        + "                <div class=\"navbar-right\">\n" 
                        + "                    <ul class=\"nav\">\n"
                        + "                        <li class=\"nav-item space-after\">\n"
                        + "                                <div id=\"username_holder\" class=\"username_holder\"></div>\n"
                        + "                        </li>\n"
                        + "                        <li class=\"nav-item space-after\">\n"
                        + "                            <a class=\"navbar-brand\" href=\"#\" onclick=\"changePage('signIn')\">\n"
                        + "                                <div id=\"sign_in\" class=\"username_holder\">Sign In</div>\n"
                        + "                            </a>\n"
                        + "                        </li>\n"
                        + "                        <li class=\"nav-item\">\n"
                        + "                            <a class=\"navbar-brand\" href=\"#\" onclick=\"changePage('register')\">\n"
                        + "                                <div id=\"register\" class=\"username_holder\">Register</div>\n"
                        + "                            </a>\n"
                        + "                        </li>\n"
                        + "                    </ul>    \n"
                        + "                </div>\n"
                        + "        </nav>\n"
                        + "        <div class=\"row\">\n"
                        + "            <div class=\"col-sm-3 col-md-4 sidebar my-sidebar\">\n"
                        + "                <ul class=\"nav-sidebar\" >\n"
                        + "                </ul>\n"
                        + "            </div>\n"
                        + "            <div id=\"main_div\" class=\"col-sm-3 main\">"
                        + "                 <div class=\"form-group\">\n"
                        + "                     <br><label for=\"username\">Username</label>\n"
                        + "                     <input class=\"form-control\" type=\"text\" id=\"username\" placeholder=\"Enter username\" name=\"username\" pattern=\"[A-Za-z]{8,}\"  title=\"Username can only contain letters e.g. Sundorius. Min length: 8.\" required>\n"
                        + "                 </div>\n"
                        + "                 <div class=\"form-group\">\n"
                        + "                     <label for=\"password\">Password</label>\n"
                        + "                      <input type=\"password\" class=\"form-control\" id=\"password\" placeholder=\"Enter password\" name=\"password\" pattern=\"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,10}$\" minlength=\"8\" maxlength=\"10\"  title=\"Password must contain at least a letter, a number and a symbol, of maximum length 10 chars.\" required>\n"
                        + "                  </div>\n"
                        + "                 <center><button type=\"submit\" onclick=\"SubmitSignInInputsToDB()\" class=\"btn btn-primary\">Submit</button><br>"
                        + "              <br><span id=\"error_span\"></span>\n"
                        + "             </div>\n"
                        + "            <div class=\"col-sm-2 \"></div>\n"
                        + "        </div>"
                        + "        <script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" crossorigin=\"anonymous\"></script>\n"
                        + "        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js\"  crossorigin=\"anonymous\"></script>\n"
                        + "        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\" crossorigin=\"anonymous\"></script>"
                        + "    </body>\n"
                        + "</html>");

                    response.setStatus(200);
                }
                else if (request.getParameter("option").equals("register"))
                {
                    out.print("<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "   <head>\n"
                        + "        <title>Wines of Crete</title>\n"
                        + "        <meta charset=\"UTF-8\">\n"
                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "\n"
                        + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" crossorigin=\"anonymous\">\n"
                        + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/base.css\">\n"
                        + "       <script async defer src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyB8bvMvt1XqEs_17jsbibgGSqmS_0AZj6c\"></script>\n"
                        + "        <script src=\"js/signIn.js\"></script>\n"
                        + "        <script src=\"js/register.js\"></script>\n"
                        + "    </head>\n"
                        + "\n"
                        + "<body>\n"
                        + "         <nav class=\"navbar navbar-dark bg-dark navbar-fixed-top\">\n"
                        + "            <div class=\"container-fluid\">\n"
                        + "                <div class=\"navbar-header\">\n"
                        + "                    <label class=\"navbar-brand\">Wines of Crete</label>\n"
                        + "                </div>\n"
                        + "                <div class=\"navbar-right\">\n"
                        + "                    <ul class=\"nav\">\n"
                        + "                        <li class=\"nav-item space-after\">\n"
                        + "                                <div id=\"username_holder\" class=\"username_holder\"></div>\n"
                        + "                        </li>\n"
                        + "                        <li class=\"nav-item space-after\">\n"
                        + "                            <a class=\"navbar-brand\" href=\"#\" onclick=\"changePage('signIn')\">\n"
                        + "                                <div id=\"sign_in\" class=\"username_holder\">Sign In</div>\n"
                        + "                            </a>\n"
                        + "                        </li>\n"
                        + "                        <li class=\"nav-item\">\n"
                        + "                            <a class=\"navbar-brand\" href=\"#\" onclick=\"changePage('register')\">\n"
                        + "                                <div id=\"register\" class=\"username_holder\">Register</div>\n"
                        + "                            </a>\n"
                        + "                        </li>\n"
                        + "                    </ul>    \n"
                        + "                </div>\n"
                        + "        </nav>\n"
                        + "        <div class=\"row\">\n"
                        + "            <div class=\"col-sm-3 col-md-2 sidebar my-sidebar\">\n"
                        + "                <ul class=\"nav-sidebar\" >\n"
                        + "                </ul>\n"
                        + "            </div>\n"
                        + "            <div align=\"center\" id=\"main_div\" class=\"col-sm-1 col-md-9 main\">"
                        + "		<div class=\"col-md-4\" id=\"username_div\">\n"
                        + "			<br><label for=\"username\">Username</label>\n"
                        + "			<br><input type=\"text\" class=\"form-control\" id=\"username\" placeholder=\"Username\" name=\"username\" pattern=\"[A-Za-z]{8,}\"  title=\"Username can only contain letters e.g. Sundorius. Min length: 8.\" onchange=\"validateUsernameFromDB()\" required>\n"
                        + "            <span id=\"username_result\"><strong></strong></span>\n"
                        + "		</div>\n"
                        + "\n"
                        + "		<div class=\"col-md-4\">\n"
                        + "			<label for=\"password\" >Password</label>\n"
                        + "    		<input type=\"password\" class=\"form-control\" id=\"password\" placeholder=\"Password\" pattern=\"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,10}$\" minlength=\"8\" maxlength=\"10\"  title=\"Password must contain at least a letter, a number and a symbol, of maximum length 10 chars.\" required >\n"
                        + "		</div>\n"
                        + "\n"
                        + "		<div class=\"col-md-4\">\n"
                        + "			<label for=\"password_confirm\">Password Validation</label>\n"
                        + "    		<input type=\"password\" class=\"form-control\" id=\"password_confirm\" placeholder=\"Password Validation\" minlength=\"8\" maxlength=\"10\"  title=\"This field must match the Password field.\" onkeyup=\"checkPassword()\" required >\n"
                        + "			<span id=\"password_result\"><strong>Must match Password.</strong></span><br><br>\n"
                        + "		</div>\n"
                        + "\n"
                        + "		<div class=\"col-md-4\">\n"
                        + "			<label for=\"email\">Email</label>\n"
                        + "   			<input type=\"email\" class=\"form-control\" id=\"email\" placeholder=\"name@example.com\" pattern=\"[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$\"  title=\"Please include an '@' in the email address.\" onchange=\"validateEmailFromDB()\" required>\n"
                        + "            <span id=\"email_result\"><strong></strong></span>\n"
                        + "		</div>\n"
                        + "\n"
                        + "		<div class=\"col-md-4\">\n"
                        + "			<label for=\"name\">Name</label>\n"
                        + "			<br><input type=\"text\" class=\"form-control\" id=\"onoma\" placeholder=\"Name\" name=\"name\" pattern=\"[A-Za-z]{0,20}\" maxlength=\"20\" title=\"Name can only contain letters, e.g. John. Min length: 0  Max length: 20. \" required>\n"
                        + "		</div>\n"
                        + "\n"
                        + "		<div class=\"col-md-4\">\n"
                        + "			<label for=\"phone\">Phone Number</label>\n"
                        + "			<br><input type=\"text\" class=\"form-control\" id=\"phone\" placeholder=\"Phone\" name=\"phone\" pattern=\"[0-9]{10,10}\" minlength=\"10\" maxlength=\"10\" title=\"A mobile phone number must have 10 digits ranging from 0 to 9\" required"
                        + "		</div>\n"
                        + "\n"
                        + "	  	<div class=\"col-md-4\" id=\"account_type_div\" required>\n"
                        + "	  		<label for=\"gender\">Account Type</label><br>\n"
                        + "		  	<input required type=\"radio\" name=\"account_type\" value=\"customer\" checked=\"checked\"> Customer<br>\n"
                        + "		  	<input required type=\"radio\" name=\"account_type\" value=\"merchant\"> Merchant<br>\n"
                        + "		</div>\n"
                        + "			<div class=\"col-md-4col-md-offset-5\" id=\"city_address\" required>\n"
                        + "				<br><label for=\"address\">Address</label>\n"
                        + "				<br><input type=\"text\" class=\"form-control\" id=\"address\" placeholder=\"Address\" name=\"address\" required>\n"
                        + "			</div>\n"
                        +"\n"
                        + "			<div class=\"col-md-4col-md-offset-5\" >\n"
                        + "				<br><label for=\"debit_card_number\">Debit Card Number</label>\n"
                        + "				<br><input type=\"text\" class=\"form-control\" id=\"debit_card_number\" placeholder=\"Debit Card Number\" pattern=\"[0-9]{16,16}\" minlength=\"12\" maxlength=\"16\" name=\"debit_card_number\" required>\n"
                        + "			</div>\n"
                        + "\n"
                        + "			<div class=\"col-md-4col-md-offset-5\" >\n"
                        + "				<br><label for=\"verification_code\">Verification Code</label>\n"
                        + "				<br><input type=\"text\" class=\"form-control\" id=\"verification_code\" pattern=\"[0-9]{4,4}\" minlength=\"4\" maxlength=\"4\" placeholder=\"Verification Code\" name=\"verification_code\" required>\n"
                        + "			</div>\n"
                        + "\n"
                        + "         <div class=\"col-md-4col-md-offset-5\">\n"
                        + "             <label for=\"expires\" >Expiration Date</label>\n"
                        + "             <div class=\"col-md-4col-md-offset-5\">\n"
                        + "                 <input id=\"expiration_date\" type=\"date\" required>"
                        + "             </div>"
                        + "         </div>"
                        + "\n"
                        + "			<div class=\"col-md-4col-md-offset-5\">\n"
                        + "				<br><label for=\"type\">Type</label>\n"
                        + "				<br><input type=\"text\" class=\"form-control\" id=\"type\" placeholder=\"Type e.g. Visa, MasterCard\" name=\"type\" required>\n"
                        + "			</div>\n"
                        + "\n"
                        + "		<div class=\"col-md-2\">\n"
                        + "			<input type=\"submit\" value=\"Register\" class=\"btn btn-primary\" onclick=\"SubmitRegisterInputsToDB()\">\n"
                        + "		</div>\n"
                        + "   </div>\n"
                        + "	</body>"
                        + "</html>");

                    response.setStatus(200);
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
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException
    {
        processRequest(request,response);
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
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException
    {
        processRequest(request,response);
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
