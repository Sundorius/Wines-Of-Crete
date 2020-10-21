package com.cs360.winesofcrete.server;

import com.cs360.winesofcrete.db.ContainsDB;
import com.cs360.winesofcrete.db.OrderDB;
import com.cs360.winesofcrete.db.UserDB;
import com.cs360.winesofcrete.db.WineDB;
import com.cs360.winesofcrete.model.Contains;
import com.cs360.winesofcrete.model.Order;
import com.cs360.winesofcrete.model.User;
import com.cs360.winesofcrete.model.Wine;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
@WebServlet(name = "AddToBasket", urlPatterns ={"/AddToBasket"})
public class AddToBasket extends HttpServlet
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
        String username = (String) request.getAttribute("username");
        String itemName = request.getParameter("itemName");
        System.out.println("Product name is:" + itemName);
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        List<Order> orders;
        User user;
        Wine wine;
        Order order;
        Contains cont;
        try(PrintWriter out = response.getWriter()) 
        {
            try 
            {
                user = UserDB.getUser(username);
                wine = WineDB.getWineByName(itemName);
                orders = OrderDB.getOrdersByStatus(2);//there can only be only one order with status 2 which is the
                                                        //contents of the basket
                cont = new Contains();
                //if there is nothing in the basket yet
                if(orders.isEmpty()) 
                {
                    order = new Order();
                    order.setStatus(2);
                    order.setBuyerUsername(username);
                    order.setBuyerName(user.getName());
                    order.setDaysLeftToPay(0);
                    order.setAmountDue(0);
                    
                    DateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
                    LocalDate localDate = LocalDate.now();
                    order.setDate(df.format(localDate));
                    
                    cont.setProductID(wine.getProductID());
                    cont.setQuantity(quantity);
                    order.addWine(wine.getProductID());
                    order.setAmount(quantity * wine.getPrice());
                    
                    
                    cont.setOrderID(OrderDB.addOrder(order));
                    ContainsDB.addContains(cont);

                }
                else 
                {
                    order = orders.get(0);
                    
                    cont.setProductID(wine.getProductID());
                    cont.setQuantity(quantity);
                    cont.setOrderID(order.getOrderID());
                    ContainsDB.addContains(cont);
                    
                    //order.addWine(wine.getProductID());
                    order.setAmount(order.getAmount() + quantity * wine.getPrice());
                    OrderDB.updateOrder(order);

                }
                out.println("Product was successfully added to basket! Order id is:" + cont.getOrderID());
            }
            catch(ClassNotFoundException e) 
            {
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
