package com.cs360.winesofcrete.db;

import com.cs360.winesofcrete.model.Variety;
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
public class VarietyDB
{
    /**
     * Get  varieties
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Variety> getVarieties() throws ClassNotFoundException {
        List<Variety> varieties = new ArrayList<>();

        try {
            try (Connection con = CS360DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM variety;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true) {
                    Variety variety = new Variety();
                    variety.setVarietyID(res.getInt("vID"));
                    variety.setName(res.getString("name"));
                    varieties.add(variety);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(Variety.class.getName()).log(Level.SEVERE, null, ex);
        }

        return varieties;
    }

    /**
     * Get variety by ID
     *
     * @param varietyID
     * @return
     * @throws ClassNotFoundException
     */
    public static Variety getVariety(int varietyID) throws ClassNotFoundException {
        Variety variety = new Variety();
        try {
            try (Connection con = CS360DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM variety ")
                        .append(" WHERE ")
                        .append(" vID = ").append("'").append(varietyID).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                if (res.next() == true) {
                    variety.setVarietyID(Integer.valueOf(res.getString("vID")));
                    variety.setName(res.getString("name"));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(Variety.class.getName()).log(Level.SEVERE, null, ex);
        }

        return variety;
    }
    
    /**
     * Get variety by name
     *
     * @param varietyName
     * @return
     * @throws ClassNotFoundException
     */
    public static Variety getVariety(String varietyName) throws ClassNotFoundException {
        Variety variety = new Variety();
        try {
            try (Connection con = CS360DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM variety ")
                        .append(" WHERE ")
                        .append(" name = ").append("'").append(varietyName).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                if (res.next() == true) {
                    variety.setVarietyID(Integer.valueOf(res.getString("vID")));
                    variety.setName(res.getString("name"));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(Variety.class.getName()).log(Level.SEVERE, null, ex);
        }

        return variety;
    }
    
}
