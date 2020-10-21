package com.cs360.winesofcrete.server;

import com.cs360.winesofcrete.db.OrderDB;
import com.cs360.winesofcrete.db.PaymentsDB;
import com.cs360.winesofcrete.db.UserDB;
import com.cs360.winesofcrete.model.Order;
import com.cs360.winesofcrete.model.Payments;
import com.cs360.winesofcrete.model.User;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "PayOrder", urlPatterns ={"/PayOrder"})
public class PayOrder extends HttpServlet {

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
        
        int orderID=Integer.parseInt(request.getParameter("orderID"));
        double payAmount=Double.parseDouble(request.getParameter("payAmount"));
        
        Order order;
        Payments payment;
        User user;
        try (PrintWriter out = response.getWriter()) 
        {
            out.append("<br><div id=\"inner_main_div\" align=\"center\">\n");
            try
            {
                user=UserDB.getUser(username);
                order=OrderDB.getOrderByID(orderID);
                if(order.getStatus()==0)
                {//If the order has not been paid yet.
                    payment=new Payments();
                    payment.setBuyerUsername(username);
                    //Time to pay 
                    if(order.getAmountDue()-payAmount>=0)
                    {
                        order.setAmountDue(order.getAmountDue()-payAmount);
                        if(order.getAmountDue()==0)
                        {
                            order.setStatus(1);
                        }
                        user.setDebt(user.getDebt()-payAmount);
                        payment.setAmountPaid(payAmount);
                    }
                    else
                    {
                        order.setAmountDue(0);
                        order.setStatus(1);//Order has been paid!
                        user.setDebt(user.getDebt()-(payAmount-order.getAmountDue()));
                        payment.setAmountPaid(payAmount-order.getAmountDue());
                    }
                    UserDB.updateUser(user);
                    OrderDB.updateOrder(order);
                    PaymentsDB.addPayment(payment);
                    
                     out.println("<br><br><br><br><center><p> Your Successfully Paid "+payAmount+"! </p></center>");
                }
                else
                {
                    out.println("<br><br><br><br><center><p> The order has already been paid for! </p></center>");
                }
                
                out.append("</div>");
                
            }
            catch(ClassNotFoundException ex)
            {
                response.setStatus(500);
                Logger.getLogger(PayOrder.class.getName()).log(Level.SEVERE, null, ex);
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
