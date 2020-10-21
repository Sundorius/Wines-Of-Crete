package com.cs360.winesofcrete.server;

import com.cs360.winesofcrete.db.UserDB;
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
 * @author crow
 */
@WebServlet(name = "ShowGoodUsers", urlPatterns ={"/ShowGoodUsers"})
public class ShowGoodUsers extends HttpServlet {

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
            ArrayList<String> goodUsers = new ArrayList<>();
            out.append("<br><div id=\"inner_main_div\" align=\"center\">\n");
            
            try
            {
                goodUsers = UserDB.getGoodUsers();
                if(goodUsers.size() > 0)
                {
                    
  
                    out.append("<div class=\"bs-example\" bad_customers_div=\"table-within-panel\">\n" +
                        "     <div class=\"panel panel-default\">\n" +
                        "     <div class=\"panel-heading\"><strong>Top Good Customers</strong></div>\n" +
                        "     <div class=\"panel-body\"> \n" +
                        "     </div> <table class=\"table\">\n" +
                        "       <thead> \n" +
                        "       <tr>\n" +
                        "       <th>#</th>\n" +
                        "           <th>Username</th> \n" +
                        "               </tr>\n" +
                        "               </thead> \n" +
                        "               <tbody> \n");
                    int j=0;
                    for(int i=0; i<goodUsers.size(); i++)
                    {
                        j=i+1;
                        out.append( "  <tr>\n" +
                            "       <th scope=\"row\">"+j+"</th>\n" +
                            "       <td>"+goodUsers.get(i)+"</td> \n" +
                            "       </tr>\n");
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
                    out.append("<br><br><br><br><center><p> There Are No Good Customers</p></center></div>\n");
                }
                response.setStatus(200);
            }
            catch (ClassNotFoundException ex)
            {
                response.setStatus(500);
                Logger.getLogger(ShowBadUsers.class.getName()).log(Level.SEVERE, null, ex);
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
