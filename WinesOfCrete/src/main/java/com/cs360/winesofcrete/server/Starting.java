package com.cs360.winesofcrete.server;

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
@WebServlet(name = "Starting", urlPatterns ={"/Starting"})
public class Starting extends HttpServlet
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
            out.append("<!DOCTYPE html>\n"
                + "<html>\n"
                + "   <head>\n"
                + "        <title>Wines of Crete</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "\n"
                + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" crossorigin=\"anonymous\">\n"
                + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/base.css\">\n"
                + "        <script src=\"js/startingLoggedOut.js\"></script>\n"
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
                + "            <div class=\"col-sm-3 col-md-2 sidebar my-sidebar\">\n"
                + "                <ul class=\"nav-sidebar\" >\n"
                + "                </ul>\n"
                + "            </div>\n"
                + "            <div id=\"main_div\" class=\"col-sm-8 main\">"
                + "            <br><br><center><p style=\"font-size:150%; id=\"welcome\"><strong>Welcome to Wines of Crete</strong></p></center>\n"
                + "            <center><p style=\"font-size:130%; id=\"note\">You can choose to Sign In, or Register to our eShop</p></center>\n"
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
