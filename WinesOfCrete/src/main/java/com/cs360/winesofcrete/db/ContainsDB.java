package com.cs360.winesofcrete.db;

import com.cs360.winesofcrete.model.Contains;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
public class ContainsDB
{

    /**
     * Get all contains
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Contains> getAllContains() throws ClassNotFoundException
    {
        List<Contains> allContains = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM contains;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    Contains contains = new Contains();
                    contains.setOrderID(Integer.valueOf(res.getString("orderID")));
                    contains.setProductID(Integer.valueOf(res.getString("productID")));
                    contains.setQuantity(Integer.valueOf(res.getString("quantity")));
                    allContains.add(contains);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Contains.class.getName()).log(Level.SEVERE,null,ex);
        }

        return allContains;
    }

    /**
     * Get contains with orderID
     *
     * @param orderID
     * @return
     * @throws ClassNotFoundException
     */
    // TODO BALE SWSTI PARAMETRO
    public static List<Contains> getContainsByOrderID(int orderID) throws ClassNotFoundException
    {
        List<Contains> allContains = new ArrayList<>();
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM contains ")
                    .append(" WHERE ")
                    .append(" orderID = ").append("'").append(orderID).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    Contains contains = new Contains();
                    contains.setOrderID(Integer.valueOf(res.getString("orderID")));
                    contains.setProductID(Integer.valueOf(res.getString("productID")));
                    contains.setQuantity(Integer.valueOf(res.getString("quantity")));
                    allContains.add(contains);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Contains.class.getName()).log(Level.SEVERE,null,ex);
        }

        return allContains;
    }

    /**
     * Establish a database connection and add a contains in the database.
     *
     * @param contains
     * @throws ClassNotFoundException
     */
    public static void addContains(Contains contains) throws ClassNotFoundException
    {
        // Check that we have all we need
        try
        {
            contains.checkFields();

        }
        catch (Exception ex)
        {
            // Log exception
            Logger.getLogger(Contains.class.getName()).log(Level.SEVERE,null,ex);
        }
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("INSERT INTO ")
                    .append(" contains (orderID, productID, quantity)")
                    .append(" VALUES (")
                    .append("'").append(contains.getOrderID()).append("',")
                    .append("'").append(contains.getProductID()).append("',")
                    .append("'").append(contains.getQuantity()).append("');");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The contains was successfully added in the database.");

                // Close connection
                stmt.close();
                con.close();

            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Contains.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    /**
     * Delete information for specific Contains by orderID
     *
     * @param orderID
     * @throws ClassNotFoundException
     */
    public static void deleteContains(int orderID) throws ClassNotFoundException {

        try {
            try (Connection con = CS360DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("DELETE FROM contains ")
                        .append(" WHERE ")
                        .append(" orderID = ").append("'").append(orderID).append("';");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The contains was successfully deleted from the database.");

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(Contains.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
