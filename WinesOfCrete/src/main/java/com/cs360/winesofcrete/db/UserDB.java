package com.cs360.winesofcrete.db;

import com.cs360.winesofcrete.model.User;
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
public class UserDB
{

    /**
     * Get all users
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static List<User> getUsers() throws ClassNotFoundException
    {
        List<User> users = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM client;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    User user = new User();
                    user.setUsername(res.getString("username"));
                    user.setEmail(res.getString("email"));
                    user.setPassword(res.getString("password"));
                    user.setName(res.getString("name"));
                    user.setAddress(res.getString("address"));
                    user.setPhoneNumber(res.getString("phoneNumber"));
                    user.setDebitCardNumber(res.getString("debitCardNumber"));
                    user.setDebt(res.getDouble("debt"));
                    user.setAccountType(res.getString("accountType"));
                    users.add(user);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE,null,ex);
        }

        return users;
    }
    
    /**
     * Get all good users
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static ArrayList<String> getGoodUsers() throws ClassNotFoundException
    {
        ArrayList<String> goodUsers = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT username FROM client WHERE debt = 0;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    goodUsers.add(res.getString("username"));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE,null,ex);
        }

        return goodUsers;
    }
    
    /**
     * Get all bad users
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static ArrayList<String> getBadUsers() throws ClassNotFoundException
    {
        ArrayList<String> badUsers = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT username FROM client WHERE debt > 0 ORDER BY debt;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    badUsers.add(res.getString("username"));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE,null,ex);
        }

        return badUsers;
    }

    /**
     * Get user with username
     *
     * @param username
     * @return
     * @throws ClassNotFoundException
     */
    public static User getUser(String username) throws ClassNotFoundException
    {
        User user = new User();
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM client ")
                    .append(" WHERE ")
                    .append(" username = ").append("'").append(username).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                if (res.next() == true)
                {
                    user.setUsername(res.getString("username"));
                    user.setEmail(res.getString("email"));
                    user.setPassword(res.getString("password"));
                    user.setName(res.getString("name"));
                    user.setAddress(res.getString("address"));
                    user.setPhoneNumber(res.getString("phoneNumber"));
                    user.setDebitCardNumber(res.getString("debitCardNumber"));
                    user.setDebt(res.getDouble("debt"));
                    user.setAccountType(res.getString("accountType"));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE,null,ex);
        }

        return user;
    }

    /**
     * Establish a database connection and add the member in the database.
     *
     * @param user
     * @throws ClassNotFoundException
     */
    public static void addUser(User user) throws ClassNotFoundException
    {
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                //Date date = new Date();
                //Timestamp timestamp = new Timestamp(date.getTime());
                StringBuilder insQuery = new StringBuilder();

                insQuery.append("INSERT INTO ")
                    .append(" client (username, email, password, name, "
                        + "address, phoneNumber, debitCardNumber,  debt, accountType) ")
                    .append(" VALUES (")
                    .append("'").append(user.getUsername()).append("',")
                    .append("'").append(user.getEmail()).append("',")
                    .append("'").append(user.getPassword()).append("',")
                    .append("'").append(user.getName()).append("',")
                    .append("'").append(user.getAddress()).append("',")
                    .append("'").append(user.getPhoneNumber()).append("',")
                    .append("'").append(user.getDebitCardNumber()).append("',")
                    .append("'").append(user.getDebt()).append("',")
                    .append("'").append(user.getAccountType()).append("');");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The member was successfully added in the database.");

                // Close connection
                stmt.close();
                con.close();

            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    /**
     * Updates information for specific user
     *
     * @param user
     * @throws ClassNotFoundException
     */
    public static void updateUser(User user) throws ClassNotFoundException
    {
        
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("UPDATE client ")
                    .append(" SET ")
                    .append(" email = ").append("'").append(user.getEmail()).append("',")
                    .append(" password = ").append("'").append(user.getPassword()).append("',")
                    .append(" name = ").append("'").append(user.getName()).append("',")
                    .append(" address = ").append("'").append(user.getAddress()).append("',")
                    .append(" phoneNumber = ").append("'").append(user.getPhoneNumber()).append("',")
                    .append(" debt = ").append("'").append(user.getDebt()).append("',")
                    .append(" accountType = ").append("'").append(user.getAccountType()).append("'")
                    .append(" WHERE username = ").append("'").append(user.getUsername()).append("';");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The member was successfully updated in the database.");

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public static boolean checkValidUserName(String userName) throws ClassNotFoundException
    {
        boolean valid = true;
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM client ")
                    .append(" WHERE ")
                    .append(" username = ").append("'").append(userName).append("';");

                stmt.execute(insQuery.toString());
                System.out.println(stmt.getResultSet());
                if (stmt.getResultSet().next() == true)
                {
                    System.out.println("#DB: The member already exists");
                    valid = false;
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE,null,ex);
        }

        return valid;
    }

    public static boolean checkValidEmail(String email) throws ClassNotFoundException
    {
        boolean valid = true;
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM client ")
                    .append(" WHERE ")
                    .append(" email = ").append("'").append(email).append("';");

                stmt.execute(insQuery.toString());
                if (stmt.getResultSet().next() == true)
                {
                    System.out.println("#DB: The member alreadyExists");
                    valid = false;
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE,null,ex);
        }

        return valid;
    }
    
    /**
     * Delete information for specific user
     *
     * @param user
     * @throws ClassNotFoundException
     */
    public static void deleteUser(User user) throws ClassNotFoundException {

        try {
            try (Connection con = CS360DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("DELETE FROM client ")
                        .append(" WHERE ")
                        .append(" username = ").append("'").append(user.getUsername()).append("';");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The member was successfully deleted from the database.");

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Delete information for specific user
     *
     * @param username
     * @throws ClassNotFoundException
     */
    public static void deleteUser(String username) throws ClassNotFoundException {

        try {
            try (Connection con = CS360DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("DELETE FROM client ")
                        .append(" WHERE ")
                        .append(" username = ").append("'").append(username).append("';");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The member was successfully deleted from the database.");

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
