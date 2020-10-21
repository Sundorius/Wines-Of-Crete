package com.cs360.winesofcrete.db;

import com.cs360.winesofcrete.model.DebitCard;
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
public class DebitCardDB
{
    /**
     * Get all debit cards
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static List<DebitCard> getDebitCards() throws ClassNotFoundException
    {
        List<DebitCard> debitCards = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM debitcard;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    DebitCard debitCard = new DebitCard();
                    debitCard.setNumber(res.getString("number"));
                    debitCard.setType(res.getString("type"));
                    debitCard.setExpirationDate(res.getString("expirationDate"));
                    debitCard.setVerificationCode(Integer.valueOf(res.getString("verificationCode")));
                    debitCards.add(debitCard);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(DebitCard.class.getName()).log(Level.SEVERE,null,ex);
        }

        return debitCards;
    }

    /**
     * Get debit card by number
     *
     * @param number
     * @return
     * @throws ClassNotFoundException
     */
    public static DebitCard getDebitCardByNumber(String number) throws ClassNotFoundException
    {
        DebitCard debitCard = new DebitCard();
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM debitcard ")
                    .append(" WHERE ")
                    .append(" number = ").append("'").append(number).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                if (res.next() == true)
                {
                    debitCard.setNumber(res.getString("number"));
                    debitCard.setType(res.getString("type"));
                    debitCard.setExpirationDate(res.getString("expirationDate"));
                    debitCard.setVerificationCode(Integer.valueOf(res.getString("verificationCode")));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(DebitCard.class.getName()).log(Level.SEVERE,null,ex);
        }

        return debitCard;
    }    
    
    /**
     * Get debit card by type
     *
     * @param type
     * @return
     * @throws ClassNotFoundException
     */
    public static List<DebitCard> getDebitCardsByType(String type) throws ClassNotFoundException
    {
        List<DebitCard> debitCards = new ArrayList<>();
        
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM debitcard ")
                    .append(" WHERE ")
                    .append(" type = ").append("'").append(type).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                if (res.next() == true)
                {
                    DebitCard debitCard = new DebitCard();
                    debitCard.setNumber(res.getString("number"));
                    debitCard.setType(res.getString("type"));
                    debitCard.setExpirationDate(res.getString("expirationDate"));
                    debitCard.setVerificationCode(Integer.valueOf(res.getString("verificationCode")));
                    debitCards.add(debitCard);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(DebitCard.class.getName()).log(Level.SEVERE,null,ex);
        }

        return debitCards;
    }

    /**
     * Establish a database connection and add a debit card in the database.
     *
     * @param debitCard
     * @throws ClassNotFoundException
     */
    public static void addDebitCard(DebitCard debitCard) throws ClassNotFoundException
    {
        // Check that we have all we need
        try
        {
            debitCard.checkFields();

        }
        catch (Exception ex)
        {
            // Log exception
            Logger.getLogger(DebitCard.class.getName()).log(Level.SEVERE,null,ex);
        }
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {
                StringBuilder insQuery = new StringBuilder();

                insQuery.append("INSERT INTO ")
                    .append(" debitcard (number, verificationCode, expirationDate, type)")
                    .append(" VALUES (")
                    .append("'").append(debitCard.getNumber()).append("',")
                    .append("'").append(debitCard.getVerificationCode()).append("',")
                    .append("'").append(debitCard.getExpirationDate()).append("',")
                    .append("'").append(debitCard.getType()).append("');");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The debit card was successfully added in the database.");

                // Close connection
                stmt.close();
                con.close();

            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(DebitCard.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    /**
     * Updates information for specific debitCard
     *
     * @param debitCard
     * @throws ClassNotFoundException
     */
    public static void updateDebitCard(DebitCard debitCard) throws ClassNotFoundException
    {
        // Check that we have all we need
        try
        {
            debitCard.checkFields();

        }
        catch (Exception ex)
        {
            // Log exception
            Logger.getLogger(DebitCard.class.getName()).log(Level.SEVERE,null,ex);
        }
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("UPDATE debitcard ")
                    .append(" SET ")
                    .append(" number = ").append("'").append(debitCard.getNumber()).append("',")
                    .append(" verificationCode = ").append("'").append(debitCard.getVerificationCode()).append("',")
                    .append(" type = ").append("'").append(debitCard.getType()).append("',")
                    .append(" expirationDate = ").append("'").append(debitCard.getExpirationDate()).append("'")
                    .append(" WHERE number = ").append("'").append(debitCard.getNumber()).append("';");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The debit card was successfully updated in the database.");

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(DebitCard.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    /**
     * Delete information for specific debit card
     *
     * @param debitCardNumber
     * @throws ClassNotFoundException
     */
    public static void deleteDebitCard(String debitCardNumber) throws ClassNotFoundException {

        try {
            try (Connection con = CS360DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("DELETE FROM debitcard ")
                        .append(" WHERE ")
                        .append(" number = ").append("'").append(debitCardNumber).append("';");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The debit card was successfully deleted from the database.");

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(DebitCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
