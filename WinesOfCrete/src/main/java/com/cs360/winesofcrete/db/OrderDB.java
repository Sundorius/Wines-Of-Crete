package com.cs360.winesofcrete.db;

import com.cs360.winesofcrete.model.Order;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
public class OrderDB
{

    /**
     * Get all orders
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Order> getOrders() throws ClassNotFoundException
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM orders;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    Order order = new Order();
                    order.setOrderID(res.getInt("oID"));
                    order.setStatus(Integer.valueOf(res.getString("status")));
                    order.setAmount(Double.valueOf(res.getString("amount")));
                    order.setAmountDue(Double.valueOf(res.getString("amountDue")));
                    order.setDate(res.getString("date"));
                    order.setBuyerName(res.getString("clientName"));
                    order.setDaysLeftToPay(Integer.valueOf(res.getString("daysLeftToPay")));
                    order.setBuyerUsername(res.getString("clientUsername"));
                        
                    
                            // TODO
                    //order.setContent(res.getString("content"));
                    
                    
                    orders.add(order);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE,null,ex);
        }

        return orders;
    }
    
    /**
     * Get all orders with given status
     *
     * @param status
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Order> getOrdersByStatus(int status) throws ClassNotFoundException
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM orders")
                    .append(" WHERE ")
                    .append(" status = ").append("'").append(status).append("';");


                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    Order order = new Order();
                    order.setOrderID(res.getInt("oID"));
                    order.setStatus(Integer.valueOf(res.getString("status")));
                    order.setAmount(Double.valueOf(res.getString("amount")));
                    order.setAmountDue(Double.valueOf(res.getString("amountDue")));
                    order.setDate(res.getString("date"));
                    order.setBuyerName(res.getString("clientName"));
                    order.setDaysLeftToPay(Integer.valueOf(res.getString("daysLeftToPay")));
                    order.setBuyerUsername(res.getString("clientUsername"));
                        
                    
                            // TODO
                    //order.setContent(res.getString("content"));
                    
                    
                    orders.add(order);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE,null,ex);
        }

        return orders;
    }

    /**
     * Get order with orderID
     *
     * @param orderID
     * @return
     * @throws ClassNotFoundException
     */
    public static Order getOrderByID(int orderID) throws ClassNotFoundException
    {
        Order order = new Order();
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM orders ")
                    .append(" WHERE ")
                    .append(" oID = ").append("'").append(orderID).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                if (res.next() == true)
                {
                    order.setOrderID(res.getInt("oID"));
                    order.setStatus(Integer.valueOf(res.getString("status")));
                    order.setAmount(Double.valueOf(res.getString("amount")));
                    order.setAmountDue(Double.valueOf(res.getString("amountDue")));
                    order.setDate(res.getString("date"));
                    order.setBuyerName(res.getString("clientName"));
                    order.setDaysLeftToPay(Integer.valueOf(res.getString("daysLeftToPay")));
                    order.setBuyerUsername(res.getString("clientUsername"));

                    // IF WE ADD DISCOUNT IN TABLE order int sql.!!
                    //order.setDiscount(res.getString("discount"));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE,null,ex);
        }

        return order;
    }
    
    /**
     * Get all orders of specific user
     *
     * @param username
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Order> getOrdersOfUser(String username) throws ClassNotFoundException
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM orders")
                    .append(" WHERE ")
                    .append(" clientUsername = ").append("'").append(username).append("';");


                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    Order order = new Order();
                    System.out.println(order);
                    order.setOrderID(res.getInt("oID"));
                    order.setStatus(Integer.valueOf(res.getString("status")));
                    order.setAmount(Double.valueOf(res.getString("amount")));
                    order.setAmountDue(Double.valueOf(res.getString("amountDue")));
                    order.setDate(res.getString("date"));
                    order.setBuyerName(res.getString("clientName"));
                    order.setDaysLeftToPay(Integer.valueOf(res.getString("daysLeftToPay")));
                    order.setBuyerUsername(res.getString("clientUsername"));
                        
                    
                            // TODO
                    //order.setContent(res.getString("content"));
                    
                    
                    orders.add(order);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE,null,ex);
        }

        return orders;
    }
    
    /**
     * Get all orders within the date range of the parameters
     *
     * @param dateFrom
     * @param dateTo
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Order> getOrdersWithinDateRange(String dateFrom, String dateTo) throws ClassNotFoundException
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM orders")
                    .append(" WHERE ")
                    .append(" date >= ").append("'").append(dateFrom)
                    .append("' AND date <= ").append("'").append(dateTo).append("';");


                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    Order order = new Order();
                    order.setOrderID(res.getInt("oID"));
                    order.setStatus(Integer.valueOf(res.getString("status")));
                    order.setAmount(Double.valueOf(res.getString("amount")));
                    order.setAmountDue(Double.valueOf(res.getString("amountDue")));
                    order.setDate(res.getString("date"));
                    order.setBuyerName(res.getString("clientName"));
                    order.setDaysLeftToPay(Integer.valueOf(res.getString("daysLeftToPay")));
                    order.setBuyerUsername(res.getString("clientUsername"));
                        
                    
                            // TODO
                    //order.setContent(res.getString("content"));
                    
                    
                    orders.add(order);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE,null,ex);
        }

        return orders;
    }

    
    /**
     * Establish a database connection and add an order in the database.
     *
     * @param order
     * @return 
     * @throws ClassNotFoundException
     */
    public static int addOrder(Order order) throws ClassNotFoundException
    {
        // Check that we have all we need
        try
        {
            order.checkFields();

        }
        catch (Exception ex)
        {
            // Log exception
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        int newOrderID = -1;
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();
                

                insQuery.append("INSERT INTO ")
                    .append(" orders (status, amount, amountDue, date, clientName, daysLeftToPay, clientUsername)")
                    .append(" VALUES (")
                    .append("'").append(order.getStatus()).append("',")
                    .append("'").append(order.getAmount()).append("',")
                    .append("'").append(order.getAmountDue()).append("',")
                    .append("'").append(order.getDate()).append("',")
                    .append("'").append(order.getBuyerName()).append("',")
                    .append("'").append(order.getDaysLeftToPay()).append("',")
                    .append("'").append(order.getBuyerUsername()).append("');");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The order was successfully added in the database.");

                StringBuilder Query = new StringBuilder();
                Query.append("SELECT MAX(oID) FROM orders;");
                stmt.execute(Query.toString());

                ResultSet res = stmt.getResultSet();

                if (res.next() == true)
                {
                	newOrderID = res.getInt("oID");
                }


                // Close connection
                stmt.close();
                con.close();

            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE,null,ex);
        }
        return newOrderID;
    }

    /**
     * Updates information for specific order
     *
     * @param order
     * @throws ClassNotFoundException
     */
    public static void updateOrder(Order order) throws ClassNotFoundException
    {
        // Check that we have all we need
        try
        {
            order.checkFields();

        }
        catch (Exception ex)
        {
            // Log exception
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE,null,ex);
        }
        try
        {
            
            System.out.println(order.toString());
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();
                
                // Change date to current date.
                LocalDate localDate = LocalDate.now();
                order.setDate(localDate.toString());

                insQuery.append("UPDATE orders ")
                    .append(" SET ")
                    .append(" oID = ").append("'").append(order.getOrderID()).append("',")
                    .append(" status = ").append("'").append(order.getStatus()).append("',")
                    .append(" amount = ").append("'").append(order.getAmount()).append("',")
                    .append(" amountDue = ").append("'").append(order.getAmountDue()).append("',")
                    .append(" discount = ").append("'").append(order.getDiscount()).append("',")
                    .append(" date = ").append("'").append(order.getDate()).append("',")
                    .append(" clientName = ").append("'").append(order.getBuyerName()).append("',")
                    .append(" daysLeftToPay = ").append("'").append(order.getDaysLeftToPay()).append("',")
                    .append(" clientUsername = ").append("'").append(order.getBuyerUsername()).append("'")
                    .append("WHERE ")
                    .append("oID = ").append("'").append(order.getOrderID()).append("';");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The order was successfully updated in the database.");

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    /**
     * Delete information for specific order by orderID
     *
     * @param orderID
     * @throws ClassNotFoundException
     */
    public static void deleteOrderByID(int orderID) throws ClassNotFoundException {

        try {
            try (Connection con = CS360DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("DELETE FROM orders ")
                        .append(" WHERE ")
                        .append(" oID = ").append("'").append(orderID).append("';");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The order was successfully deleted from the database.");

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Delete all orders of specific user
     *
     * @param username
     * @throws ClassNotFoundException
     */
    public static void deleteAllOrdersOfUser(String username) throws ClassNotFoundException {

        try {
            try (Connection con = CS360DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("DELETE FROM orders ")
                        .append(" WHERE ")
                        .append(" clientUsername = ").append("'").append(username).append("';");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The order was successfully deleted from the database.");

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
