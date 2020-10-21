package com.cs360.winesofcrete.server;

import com.cs360.winesofcrete.db.OrderDB;
import com.cs360.winesofcrete.db.UserDB;
import com.cs360.winesofcrete.model.Order;
import com.cs360.winesofcrete.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "MakeOrder", urlPatterns ={"/MakeOrder"})
public class MakeOrder extends HttpServlet {

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
        /*HttpSession session = request.getSession(false);
        if (session == null) 
        {
            response.setStatus(401);
            return;
        }*/
        
        
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session=request.getSession(true);
        String username=(String)session.getAttribute("username");
        
        boolean canOrder=true;
        Order order;
        List<Order> orders;
        User user;
        try (PrintWriter out = response.getWriter()) {
            try{
                user=UserDB.getUser(username);
                orders=OrderDB.getOrdersOfUser(username);
                if(!orders.isEmpty()){
                    for(Order orderTmp:orders){
                        if(orderTmp.getDaysLeftToPay()<0){
                            canOrder=false;
                            break;
                        }
                    }
                }
            
                if(canOrder){
                    if(!OrderDB.getOrdersByStatus(0).isEmpty()){
                        order=OrderDB.getOrdersByStatus(2).get(0);
                        order.setStatus(0);//We make the order's status to 0 because it is not the basket anymore
                        order.setDaysLeftToPay(10);
                        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                        order.setDate(date);
                        double amount=order.getAmount();
                        if(amount<100){
                            order.setDiscount(0);
                        }else if(amount>=100&&amount<200){
                            order.setDiscount(5);
                            order.setAmount(order.getAmount()-order.getAmount()*5/100);
                        }else{
                            order.setDiscount(10);
                            order.setAmount(order.getAmount()-order.getAmount()*10/100);
                        }
                        order.setAmountDue(order.getAmount());
                        user.setDebt(user.getDebt()+order.getAmount());
                        UserDB.updateUser(user);
                        OrderDB.updateOrder(order);
                        response.setStatus(200);
                        out.println("Order was made successfully!");
                    }else{
                        response.setStatus(400);
                        out.println("ERROR! You cannot order with no contents in the basket!!");
                    }
                }else{
                    response.setStatus(200);
                    out.println("You cannot make any order because you are still in debt!");
                }
                
            }catch(ClassNotFoundException e){
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
