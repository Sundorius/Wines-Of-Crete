package com.cs360.winesofcrete.db;

import com.cs360.winesofcrete.model.Payments;
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
public class PaymentsDB
{
    /**
     * Get all payments
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Payments> getAllPayments() throws ClassNotFoundException
    {
        List<Payments> payments = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM payments;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    Payments payment = new Payments();
                    payment.setPaymentID(Integer.valueOf(res.getString("pID")));
                    payment.setBuyerUsername(res.getString("clientUsername"));
                    payment.setAmountPaid(Double.valueOf(res.getString("amountPaid")));
                    payments.add(payment);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Payments.class.getName()).log(Level.SEVERE,null,ex);
        }

        return payments;
    }

    /**
     * Get payment with paymentID
     *
     * @param paymentID
     * @return
     * @throws ClassNotFoundException
     */
    public static Payments getPaymentByID(int paymentID) throws ClassNotFoundException
    {
        Payments payment = new Payments();
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM payments ")
                    .append(" WHERE ")
                    .append(" pID = ").append("'").append(paymentID).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                if (res.next() == true)
                {
                    payment.setPaymentID(Integer.valueOf(res.getString("pID")));
                    payment.setBuyerUsername(res.getString("clientUsername"));
                    payment.setAmountPaid(Double.valueOf(res.getString("amountPaid")));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Payments.class.getName()).log(Level.SEVERE,null,ex);
        }

        return payment;
    }
    
    
    /**
     * Get all payments of a specific user 
     *
     * @param buyerUsername
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Payments> getPaymentsOfUser(String buyerUsername) throws ClassNotFoundException
    {
        List<Payments> payments = new ArrayList<>();
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM payments ")
                    .append(" WHERE ")
                    .append(" clientUsername = ").append("'").append(buyerUsername).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    Payments payment = new Payments();
                    payment.setPaymentID(Integer.valueOf(res.getString("pID")));
                    payment.setBuyerUsername(res.getString("clientUsername"));
                    payment.setAmountPaid(Double.valueOf(res.getString("amountPaid")));
                    payments.add(payment);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Payments.class.getName()).log(Level.SEVERE,null,ex);
        }

        return payments;
    }
    
    
     /**
     * Establish a database connection and add a payment in the database.
     *
     * @param payment
     * @throws ClassNotFoundException
     */
    public static void addPayment(Payments payment) throws ClassNotFoundException
    {
        // Check that we have all we need
        try
        {
            payment.checkFields();

        }
        catch (Exception ex)
        {
            // Log exception
            Logger.getLogger(Payments.class.getName()).log(Level.SEVERE,null,ex);
        }
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("INSERT INTO ")
                    .append(" payments (clientUsername, amountPaid)")
                    .append(" VALUES (")
                    .append("'").append(payment.getBuyerUsername()).append("',")
                    .append("'").append(payment.getAmountPaid()).append("');");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The payment was successfully added in the database.");

                // Close connection
                stmt.close();
                con.close();

            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Payments.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    /**
     * Updates information for specific payment
     *
     * @param payment
     * @throws ClassNotFoundException
     */
    public static void updatePayment(Payments payment) throws ClassNotFoundException
    {
        // Check that we have all we need
        try
        {
            payment.checkFields();

        }
        catch (Exception ex)
        {
            // Log exception
            Logger.getLogger(Payments.class.getName()).log(Level.SEVERE,null,ex);
        }
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("UPDATE payments ")
                    .append(" SET ")
                    .append(" pID = ").append("'").append(payment.getPaymentID()).append("',")
                    .append(" clientUsername = ").append("'").append(payment.getBuyerUsername()).append("',")
                    .append(" amountPaid = ").append("'").append(payment.getAmountPaid()).append("'")
                    .append("WHERE")
                    .append("pID = ").append("'").append(payment.getPaymentID()).append("';");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The payment was successfully updated in the database.");

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Payments.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    /**
     * Delete information for specific payment by paymentID
     *
     * @param paymentID
     * @throws ClassNotFoundException
     */
    public static void deletePayment(int paymentID) throws ClassNotFoundException {

        try {
            try (Connection con = CS360DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("DELETE FROM payments ")
                        .append(" WHERE ")
                        .append(" pID = ").append("'").append(paymentID).append("';");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The payment was successfully deleted from the database.");

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(Payments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Delete all payments of specific user
     *
     * @param username
     * @throws ClassNotFoundException
     */
    public static void deleteAllPaymentsOfUser(String username) throws ClassNotFoundException {

        try {
            try (Connection con = CS360DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("DELETE FROM payments ")
                        .append(" WHERE ")
                        .append(" clientUsername = ").append("'").append(username).append("';");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The payment was successfully deleted from the database.");

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(Payments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
