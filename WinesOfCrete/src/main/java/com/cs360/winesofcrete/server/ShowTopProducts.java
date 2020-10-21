package com.cs360.winesofcrete.server;

import com.cs360.winesofcrete.db.ContainsDB;
import com.cs360.winesofcrete.db.OrderDB;
import com.cs360.winesofcrete.db.WineDB;
import com.cs360.winesofcrete.model.Contains;
import com.cs360.winesofcrete.model.Order;
import com.cs360.winesofcrete.model.Wine;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
@WebServlet(name = "ShowTopProducts", urlPatterns ={"/ShowTopProducts"})
public class ShowTopProducts extends HttpServlet
{
     class ProductsQuantities 
    {
        Wine wine;
        int totalQuantity;

        ProductsQuantities() 
        {
            wine = null;
            totalQuantity=-1;
        }
        
        ProductsQuantities(Wine wineIn, int quantityIn) 
        {
            wine = wineIn;
            totalQuantity=quantityIn;
        }
        
        
    }

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

        try (PrintWriter out = response.getWriter())
        {
            out.print("<div id=\"inner_main_div\">\n");

            try
            {

                LocalDate localDate = LocalDate.now();
                String nowDate =localDate.toString();
                
                String[] monthBeforeDate = nowDate.split("-");
                
                
                
                monthBeforeDate[2]="01"; // First day of the month.
                String month1 = null;
                String month2 = null;
                String year1 = null;
                String year2 = null;
                switch(monthBeforeDate[2])
                {
                    case "01":
                        month1 = "12";
                        year1 = String.valueOf(Integer.parseInt(monthBeforeDate[0])-1);
                        month2 = "11";
                        year2 = String.valueOf(Integer.parseInt(monthBeforeDate[0])-1);
                        break;
                    case "02":
                        month1 = "01";
                        month2 = "12";
                        year2 = String.valueOf(Integer.parseInt(monthBeforeDate[0])-1);
                        break;
                    case "03":
                        month1 = "02";
                        month2 = "01";
                        break;
                    case "04":
                        month1 = "03";
                        month2 = "02";
                        break;
                    case "05":
                        month1 = "04";
                        month2 = "03";
                        break;
                    case "06":
                        month1 = "05";
                        month2 = "04";
                        break;
                    case "07":
                        month1 = "06";
                        month2 = "05";
                        break;
                    case "08":
                        month1 = "07";
                        month2 = "06";
                        break;
                    case "09":
                        month1 = "08";
                        month2 = "07";
                        break;
                    case "10":
                        month1 = "09";
                        month2 = "08";
                        break;
                    case "11":
                        month1 = "10";
                        month2 = "09";
                        break;
                    case "12":
                        month1 = "11";
                        month2 = "10";
                        break;
                }

                monthBeforeDate[1]=month1;// Now it is 1 month before;
                monthBeforeDate[0]=year1;
                String monthEnd = monthBeforeDate[0]+"-"+monthBeforeDate[1]+"-"+monthBeforeDate[2];

                monthBeforeDate[1]=month2;// Now it is 2 month before;
                monthBeforeDate[0]=year2;
                String monthStart = monthBeforeDate[0]+"-"+monthBeforeDate[1]+"-"+monthBeforeDate[2];

                // Has the orders in the range [two Months Before, one month before]
                ArrayList<Order> orders = (ArrayList<Order>) OrderDB.getOrdersWithinDateRange(monthStart, monthEnd);
                
                
                ArrayList<Contains> allTheProductsOfTheOrders = new ArrayList<>();
                ArrayList<Wine> redColorWines = new ArrayList<>();
                ArrayList<Wine> whiteColorWines = new ArrayList<>();
                ArrayList<Wine> roseColorWines = new ArrayList<>();
                
                HashMap<Integer, ProductsQuantities> winesWithTotalQuantity = new HashMap<>();
                

                //ArrayList<String> wineriesWithQuantitySold = new ArrayList<>();
                
                HashMap<String, Integer> wineriesWithQuantitySold = new HashMap<>();
     
                if(orders.size() <=0)
                {
                    out.print("<br><br><br><br><center><p>There were no orders on the previous month</p></center></div>\n");
                }
                else
                {
                    for(Order order: orders)
                    {
                        ArrayList<Contains> containsOfOrder = (ArrayList<Contains>) ContainsDB.getContainsByOrderID(order.getOrderID());
                        for(Contains cont: containsOfOrder )
                        {
                            allTheProductsOfTheOrders.add(cont);
                        }
                    }
                    
                    // Populate the HashMap with key: productID , value: quantity sold of that product!
                    for(Contains contains : allTheProductsOfTheOrders) 
                    {
                        int productID = contains.getProductID();
                        int quantity = contains.getQuantity();
                        if(winesWithTotalQuantity.containsKey(productID))
                        {
                            //Update the quantity
                            ProductsQuantities updatedEntry = winesWithTotalQuantity.get(productID);
                            updatedEntry.totalQuantity = updatedEntry.totalQuantity + quantity;
                            
                            // Update the HashMap.
                            winesWithTotalQuantity.put(productID, updatedEntry);
                        }
                        else
                        {
                            // Find the wine from the DB.
                            Wine wine = WineDB.getWineByID(productID);
                            ProductsQuantities newEntry = new ProductsQuantities(wine,quantity);
                            
                            // Add the new entry.
                            winesWithTotalQuantity.put(productID, newEntry);
                        }
                    }
                    
                    
                    Object[] productsQ =  winesWithTotalQuantity.values().toArray();
                    ArrayList<Wine> winesTotal = new ArrayList<>();
                    for(Object product: productsQ)
                    {
                        ProductsQuantities prod = (ProductsQuantities) product;
                        Wine wine = prod.wine;
                        wine.setQuantity(prod.totalQuantity);
                        winesTotal.add(wine);
                        System.out.println(wine.toString());
                    }
                    
                    class WineComparator implements Comparator<Wine> 
                    {
                        @Override
                        public int compare(Wine wine1, Wine wine2) 
                        {
                            double wine1Quant = wine1.getQuantity();
                            double wine2Quant = wine2.getQuantity();

                            if (wine1Quant > wine2Quant) 
                            {
                                return 1;
                            } 
                            else if (wine1Quant < wine2Quant) 
                            {
                                return -1;
                            } 
                            else 
                            {
                                return 0;
                            }
                        }
                    }
                    
                    // Sorted in descending order by total quantity!!
                    Collections.sort(winesTotal, new WineComparator().reversed());
                    for(Wine wine:winesTotal)
                    {
                        if(null != wine.getColor())
                        switch(wine.getColor()) 
                        {
                            case "red":
                                redColorWines.add(wine);
                                break;
                            case "rose":
                                roseColorWines.add(wine);
                                break;
                            case "white":
                                whiteColorWines.add(wine);
                                break;
                            default:
                                break;
                        }
                        String winery = wine.getWinery();
                        int quantity = (int)wine.getQuantity();
                        if(wineriesWithQuantitySold.containsKey(wine.getWinery()))
                        {
                            wineriesWithQuantitySold.put(winery, wineriesWithQuantitySold.get(winery) + quantity);
                        }
                        else
                        {
                            wineriesWithQuantitySold.put(winery, quantity);
                        }
                    }  
                    
                }


                out.append("<br><div id=\"inner_main_div\" align=\"center\">\n");

                if(redColorWines.size()>0)
                {
                    ProductsQuantities q = new ProductsQuantities();
                    
                    out.append("<div class=\"bs-example\" top_red_wines_div=\"table-within-panel\">\n" +
                        "     <div class=\"panel panel-default\">\n" +
                        "     <div class=\"panel-heading\"><strong>Top 10 Red Wines</strong></div>\n" +
                        "     <div class=\"panel-body\"> \n" +
                        "     </div> <table class=\"table\">\n" +
                        "           <thead> \n" +
                        "           <tr>\n" +
                        "           <th>#</th>\n" +
                        "               <th>Name</th>\n" +
                        "               <th>Winery</th> \n" +
                        "               <th>Year</th> \n" +
                        "               <th>Price</th> \n" +
                        "               <th>Quantity Sold</th> \n" +
                        "                   </tr>\n" +
                        "                   </thead> \n" +
                        "                   <tbody> ");
                    int counter=1;
                    for(Wine wine : redColorWines)
                    {
                        if(counter<=10)
                        {
                            out.append("<tr>\n" +
                                "   <th scope=\"row\">"+counter+"</th>\n" +
                                "   <td>"+wine.getName()+"</td> \n" +
                                "   <td>"+wine.getWinery()+"</td>\n" +
                                "   <td>"+wine.getYear()+"</td>\n" +
                                "   <td>"+wine.getPrice()+"</td>\n" +
                                "   <td>"+(int)wine.getQuantity()+"</td>\n" +
                                "    </tr>");

                           counter++;
                       }
                        else
                        {
                            break;
                        }
                    }
                    out.append("</tr>\n" +
                    "    </tbody>\n" +
                    "     </table>\n" +
                    "     </div> \n" +
                    "     </div><br>");
                }
                if(whiteColorWines.size()>0)
                {

                    out.append("<div class=\"bs-example\" top_red_wines_div=\"table-within-panel\">\n" +
                        "     <div class=\"panel panel-default\">\n" +
                        "     <div class=\"panel-heading\"><strong>Top 10 White Wines</strong></div>\n" +
                        "     <div class=\"panel-body\"> \n" +
                        "     </div> <table class=\"table\">\n" +
                        "           <thead> \n" +
                        "           <tr>\n" +
                        "           <th>#</th>\n" +
                        "               <th>Name</th>\n" +
                        "               <th>Winery</th> \n" +
                        "               <th>Year</th> \n" +
                        "               <th>Price</th> \n" +
                        "               <th>Quantity Sold</th> \n" +
                        "                   </tr>\n" +
                        "                   </thead> \n" +
                        "                   <tbody> ");
                    int counter=1;
                    for(Wine wine : whiteColorWines)
                    {
                        if(counter<=10)
                        {
                            out.append("<tr>\n" +
                                "   <th scope=\"row\">"+counter+"</th>\n" +
                                "   <td>"+wine.getName()+"</td> \n" +
                                "   <td>"+wine.getWinery()+"</td>\n" +
                                "   <td>"+wine.getYear()+"</td>\n" +
                                "   <td>"+wine.getPrice()+"</td>\n" +
                                "   <td>"+(int)wine.getQuantity()+"</td>\n" +
                                "    </tr>");

                           counter++;
                       }
                        else
                        {
                            break;
                        }
                    }
                    out.append("</tr>\n" +
                    "    </tbody>\n" +
                    "     </table>\n" +
                    "     </div> \n" +
                    "     </div><br>");
                }
                if(roseColorWines.size()>0)
                {

                    out.append("<div class=\"bs-example\" top_red_wines_div=\"table-within-panel\">\n" +
                        "     <div class=\"panel panel-default\">\n" +
                        "     <div class=\"panel-heading\"><strong>Top 10 Rose Wines</strong></div>\n" +
                        "     <div class=\"panel-body\"> \n" +
                        "     </div> <table class=\"table\">\n" +
                        "           <thead> \n" +
                        "           <tr>\n" +
                        "           <th>#</th>\n" +
                        "               <th>Name</th>\n" +
                        "               <th>Winery</th> \n" +
                        "               <th>Year</th> \n" +
                        "               <th>Price</th> \n" +
                        "               <th>Quantity Sold</th> \n" +
                        "                   </tr>\n" +
                        "                   </thead> \n" +
                        "                   <tbody> ");
                    int counter=1;
                    for(Wine wine : roseColorWines)
                    {
                        if(counter<=10)
                        {
                            out.append("<tr>\n" +
                                "   <th scope=\"row\">"+counter+"</th>\n" +
                                "   <td>"+wine.getName()+"</td> \n" +
                                "   <td>"+wine.getWinery()+"</td>\n" +
                                "   <td>"+wine.getYear()+"</td>\n" +
                                "   <td>"+wine.getPrice()+"</td>\n" +
                                "   <td>"+(int)wine.getQuantity()+"</td>\n" +
                                "    </tr>");
 
                           counter++;
                       }
                        else
                        {
                            break;
                        }
                    }
                    out.append("</tr>\n" +
                    "    </tbody>\n" +
                    "     </table>\n" +
                    "     </div> \n" +
                    "     </div><br>");
                }

                // Shows the top 10 Wineries in ascending order.
                if(wineriesWithQuantitySold.size()>0)
                {
 
                    out.append("<div class=\"bs-example\" top_wineries_div=\"table-within-panel\">\n" +
                    "     <div class=\"panel panel-default\">\n" +
                    "     <div class=\"panel-heading\"><strong>Top 10 Wineries</strong></div>\n" +
                    "     <div class=\"panel-body\"> \n" +
                    "     </div> <table class=\"table\">\n" +
                    "       <thead> \n" +
                    "       <tr>\n" +
                    "       <th>#</th>\n" +
                    "           <th>Winery</th> \n" +
                    "               </tr>\n" +
                    "               </thead> \n" +
                    "               <tbody> \n");
                int j=0;
                    int counter=0;
                    Object[] wineries = wineriesWithQuantitySold.keySet().toArray();
                    for(int i=0; i<wineries.length; i++)
                    {
                        if(counter<10)
                        {
                            j=i+1;
                            out.append( "  <tr>\n" +
                                "       <th scope=\"row\">"+j+"</th>\n" +
                                "       <td>"+wineries[i].toString()+"</td> \n" +
                                "       </tr>\n");
                            counter++;
                        }
                        else
                        {
                            break;
                        }                           
                    }
                    out.append("</tr>\n" +
                    "    </tbody>\n" +
                    "     </table>\n" +
                    "     </div> \n" +
                    "     </div>");
                }

                out.append("     </div>\n");
                
                
                response.setStatus(200);
            }
            catch (ClassNotFoundException ex)
            {
                response.setStatus(500);
                Logger.getLogger(ShowTopProducts.class.getName()).log(Level.SEVERE, null, ex);
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
