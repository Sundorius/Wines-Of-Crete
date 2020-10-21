package com.cs360.winesofcrete.server;

import com.cs360.winesofcrete.db.DebitCardDB;
import com.cs360.winesofcrete.db.OrderDB;
import com.cs360.winesofcrete.db.PaymentsDB;
import com.cs360.winesofcrete.db.UserDB;
import com.cs360.winesofcrete.model.Order;
import com.cs360.winesofcrete.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "DeleteAccount", urlPatterns ={"/DeleteAccount"})
public class DeleteAccount extends HttpServlet {

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
        
        boolean canDelete=false;
        int orderStatus = -1;
        
        User user;
        ArrayList<Order> orders;
        
        try (PrintWriter out = response.getWriter()) 
        {
            try
            {
                user=UserDB.getUser(username);
                orders=(ArrayList<Order>) OrderDB.getOrdersOfUser(username);
                for (Order order:orders) 
                {
                    //even if one order is not paid the user cannot delete their account
                    if(order.getStatus()==0||order.getStatus()==2)
                    {
                        canDelete=false;
                        orderStatus = order.getStatus();
                        break;
                    }
                    else
                    {
                        canDelete=true;
                    }
                }
                if(canDelete==false)
                {
                    //If the user has not paid for an order then the account cannot be deleted
                    //out.println("Account cannot be deleted. At least one order has not beed paid.");
                    if(orderStatus == 0)
                    {
                        out.append("<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> You can not delete your account until you pay the debt of your last order!!</p></center></div>\n");
                    }
                    else if(orderStatus == 2)
                    {
                        out.append("<br><div id=\"inner_main_div\">\n"
                                + "<br><br><br><br><center><p> You can not delete your account, you have products in your basket!!</p></center></div>\n");
                    }
                    
                    response.setStatus(400);
                }else
                {
                    //Delete the user's account
                    //Start by deleting the payments of the user from the database.
                    PaymentsDB.deleteAllPaymentsOfUser(username);
                    //Delete the user's debit card from the DB
                    DebitCardDB.deleteDebitCard(user.getDebitCardNumber());
                    //Delete all orders of user (we know that at this point all the user's order have been paid)
                    OrderDB.deleteAllOrdersOfUser(username);
                    //Finally delete the user from the database.
                    UserDB.deleteUser(username);
                    response.setStatus(200);
                    out.println("Your account was successfully removed from the database! Please logout!");
                    
                    response.setStatus(200);
                    RequestDispatcher rd = request.getRequestDispatcher("LogOut");
                    rd.forward(request, response);
                }
            }catch(ClassNotFoundException e)
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
