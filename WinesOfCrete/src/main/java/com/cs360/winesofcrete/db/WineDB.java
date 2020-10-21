package com.cs360.winesofcrete.db;

import com.cs360.winesofcrete.model.Wine;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
public class WineDB        
{
    /**
     * Get all wines
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Wine> getWines() throws ClassNotFoundException
    {
        List<Wine> wines = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM product;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    Wine wine = new Wine();
                    wine.setProductID(Integer.valueOf(res.getString("pID")));
                    wine.setName(res.getString("name"));
                    wine.setYear(Integer.valueOf(res.getString("year")));
                    wine.setColor(res.getString("color"));
                    wine.setPrice(Double.valueOf(res.getString("price")));
                    wine.setWinery(res.getString("winery"));
                    wine.setQuantity(Integer.valueOf(res.getString("quantity")));
                    wine.setImg(res.getString("image"));
                    wines.add(wine);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Wine.class.getName()).log(Level.SEVERE,null,ex);
        }

        return wines;
    }
    
    /**
     * Get all wineries with quantity remaining
     * @return
     * @throws ClassNotFoundException
     */
    public static ArrayList<String> getWinesByQuantityWinery() throws ClassNotFoundException
    {
        ArrayList<String> wineryQuantity = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT DISTINCT winery FROM product")
                    .append(" ORDER BY ")
                    .append(" quantity ;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    wineryQuantity.add(res.getString("winery"));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Wine.class.getName()).log(Level.SEVERE,null,ex);
        }
        System.out.println("\n\n\n"+wineryQuantity);
        return wineryQuantity;
    }
    
     /**
     * Get all wineries with quantity sold
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static HashMap<String,Integer> getWineriesAndQuantity() throws ClassNotFoundException
    {
        List<String> wineries = new ArrayList<>();
        HashMap<String,Integer> wineriesQuantity = new HashMap<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT DISTINCT winery FROM product;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    //String winery = res.getString("winery");
                    wineries.add(new String(res.getString("winery")));
                }
                
                StringBuilder Query = new StringBuilder();
                for(String winery: wineries)
                {
                    Query.append("SELECT quantity FROM product WHERE winery=").append(winery).append("';");
                    stmt.execute(insQuery.toString());
                    ResultSet ress = stmt.getResultSet();
                    Integer quantity = 0;
                    while (ress.next() == true)
                    {
                        quantity +=ress.getInt("quantity");
                        wineriesQuantity.put(winery, quantity);
                    }
                    
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Wine.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        // Sorts results by value in ascending order, because less quantity remaing of a product , means it has sold more.
        Object[] a = wineriesQuantity.entrySet().toArray();
        Arrays.sort(a, new Comparator() 
        {
            @Override
            public int compare(Object o1, Object o2) 
            {
                return ((Map.Entry<String, Integer>) o1).getValue().compareTo(((Map.Entry<String, Integer>) o2).getValue());
            }
        });
        return wineriesQuantity;
    }
    
   
    
    /**
     * Get all wines by color
     * @param color
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Wine> getWinesByColor(String color) throws ClassNotFoundException
    {
        List<Wine> wines = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM product")
                    .append(" WHERE quantity > 0 AND ")
                    .append(" color = ").append("'").append(color).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    Wine wine = new Wine();
                    wine.setProductID(Integer.valueOf(res.getString("pID")));
                    wine.setName(res.getString("name"));
                    wine.setYear(Integer.valueOf(res.getString("year")));
                    wine.setColor(res.getString("color"));
                    wine.setPrice(Double.valueOf(res.getString("price")));
                    wine.setWinery(res.getString("winery"));
                    wine.setQuantity(Integer.valueOf(res.getString("quantity")));
                    wine.setImg(res.getString("image"));
                    wines.add(wine);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Wine.class.getName()).log(Level.SEVERE,null,ex);
        }

        return wines;
    }
    
    /**
     * Get all wines by winery
     * @param winery
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Wine> getWinesByWinery(String winery) throws ClassNotFoundException
    {
        List<Wine> wines = new ArrayList<>();

        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM product")
                    .append(" WHERE quantity > 0 AND")
                    .append(" winery = ").append("'").append(winery).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true)
                {
                    Wine product = new Wine();
                    product.setProductID(Integer.valueOf(res.getString("pID")));
                    product.setName(res.getString("name"));
                    product.setYear(Integer.valueOf(res.getString("year")));
                    product.setColor(res.getString("color"));
                    product.setPrice(Double.valueOf(res.getString("price")));
                    product.setWinery(res.getString("winery"));
                    product.setQuantity(Integer.valueOf(res.getString("quantity")));
                    product.setImg(res.getString("image"));
                    wines.add(product);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Wine.class.getName()).log(Level.SEVERE,null,ex);
        }

        return wines;
    }

    /**
     * Get wines with productID
     *
     * @param productID
     * @return
     * @throws ClassNotFoundException
     */
    public static Wine getWineByID(int productID) throws ClassNotFoundException
    {
        Wine wine = new Wine();
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM product ")
                    .append(" WHERE ")
                    .append(" pID = ").append("'").append(productID).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                if (res.next() == true)
                {
                    wine.setProductID(Integer.valueOf(res.getString("pID")));
                    wine.setName(res.getString("name"));
                    wine.setYear(Integer.valueOf(res.getString("year")));
                    wine.setColor(res.getString("color"));
                    wine.setPrice(Double.valueOf(res.getString("price")));
                    wine.setWinery(res.getString("winery"));
                    wine.setQuantity(Integer.valueOf(res.getString("quantity")));
                    wine.setImg(res.getString("image"));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Wine.class.getName()).log(Level.SEVERE,null,ex);
        }

        return wine;
    }   
    
    
    /**
     * Get wine by name
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    public static Wine getWineByName(String name) throws ClassNotFoundException
    {
        Wine wine = new Wine();
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM product ")
                    .append(" WHERE ")
                    .append(" name = ").append("'").append(name).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                if (res.next() == true)
                {
                    wine.setProductID(Integer.valueOf(res.getString("pID")));
                    wine.setName(res.getString("name"));
                    wine.setYear(Integer.valueOf(res.getString("year")));
                    wine.setColor(res.getString("color"));
                    wine.setPrice(Double.valueOf(res.getString("price")));
                    wine.setWinery(res.getString("winery"));
                    wine.setQuantity(Integer.valueOf(res.getString("quantity")));
                    wine.setImg(res.getString("image"));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Wine.class.getName()).log(Level.SEVERE,null,ex);
        }

        return wine;
    }
    
    
    /**
     * Get wines by year
     *
     * @param year
     * @return
     * @throws ClassNotFoundException
     */
    public static ArrayList<Wine> getWinesByYear(String year) throws ClassNotFoundException
    {
        ArrayList<Wine> wines = new ArrayList<>();
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM product ")
                    .append(" WHERE quantity > 0 AND")
                    .append(" year = ").append("'").append(year).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                if (res.next() == true)
                {
                    Wine wine = new Wine();
                    wine.setProductID(Integer.valueOf(res.getString("pID")));
                    wine.setName(res.getString("name"));
                    wine.setYear(Integer.valueOf(res.getString("year")));
                    wine.setColor(res.getString("color"));
                    wine.setPrice(Double.valueOf(res.getString("price")));
                    wine.setWinery(res.getString("winery"));
                    wine.setQuantity(Integer.valueOf(res.getString("quantity")));
                    wine.setImg(res.getString("image"));
                    wines.add(wine);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Wine.class.getName()).log(Level.SEVERE,null,ex);
        }

        return wines;
    }   

    /**
     * Get years of wines
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static ArrayList<String> getYearsOfWines() throws ClassNotFoundException
    {
        ArrayList<String> years = new ArrayList<>();
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT DISTINCT year FROM product ORDER BY year; ");
                    
                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while(res.next())
                {
                    years.add(res.getString("year"));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Wine.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        System.out.println(years);
        return years;
    }
    
    /**
     * Get wineries of wines
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static ArrayList<String> getWineriesOfWines() throws ClassNotFoundException
    {
        ArrayList<String> wineries = new ArrayList<>();
        try
        {
            try (Connection con = CS360DB.getConnection();
                Statement stmt = con.createStatement())
            {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT DISTINCT winery FROM product ORDER BY winery; ");
                    
                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while(res.next())
                {
                    wineries.add(res.getString("winery"));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        }
        catch (SQLException ex)
        {
            // Log exception
            Logger.getLogger(Wine.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        System.out.println(wineries);
        return wineries;
    }
}
