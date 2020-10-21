package com.cs360.winesofcrete.db;

import com.cs360.winesofcrete.model.ConsistsOf;
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
public class ConsistsOfDB
{
    /**
     * Get all ConsistsOf
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static List<ConsistsOf> getAllConsistsOf() throws ClassNotFoundException
    {
        List<ConsistsOf> AllConsistsOf = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM consistsof;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    ConsistsOf consistsOf = new ConsistsOf();
                    consistsOf.setProductID(Integer.valueOf(res.getString("productID")));
                    consistsOf.setVarietyID(Integer.valueOf(res.getString("varietyID")));
                    AllConsistsOf.add(consistsOf);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(ConsistsOf.class.getName()).log(Level.SEVERE,null,ex);
        }

        return AllConsistsOf;
    }

    /**
     * Get consistsOf by productID
     *
     * @param productID
     * @return
     * @throws ClassNotFoundException
     */
    public static List<ConsistsOf> getConsistsOfByProductID(int productID) throws ClassNotFoundException
    {
        List<ConsistsOf> AllConsistsOf = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM consistsof")
                    .append(" WHERE ")
                    .append(" productID = ").append("'").append(productID).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    ConsistsOf consistsOf = new ConsistsOf();
                    consistsOf.setProductID(Integer.valueOf(res.getString("productID")));
                    consistsOf.setVarietyID(Integer.valueOf(res.getString("varietyID")));
                    AllConsistsOf.add(consistsOf);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(ConsistsOf.class.getName()).log(Level.SEVERE,null,ex);
        }

        return AllConsistsOf;
    }
    
    /**
     * Get ConsistsOf by varietyID
     *
     * @param varietyID
     * @return
     * @throws ClassNotFoundException
     */
    public static List<ConsistsOf> getConsistsOfByVarietyID(int varietyID ) throws ClassNotFoundException
    {
        List<ConsistsOf> AllConsistsOf = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM consistsof")
                    .append(" WHERE ")
                    .append(" varietyID = ").append("'").append(varietyID).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    ConsistsOf consistsOf = new ConsistsOf();
                    consistsOf.setProductID(Integer.valueOf(res.getString("productID")));
                    consistsOf.setVarietyID(Integer.valueOf(res.getString("varietyID")));
                    AllConsistsOf.add(consistsOf);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(ConsistsOf.class.getName()).log(Level.SEVERE,null,ex);
        }

        return AllConsistsOf;
    }
    
    
}
