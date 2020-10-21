package com.cs360.winesofcrete.model;

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
public class Contains
{

    private int orderID;
    private int productID;
    private int quantity;

    public Contains()
    {
        this.orderID = -1;
        this.productID = -1;
        this.quantity = -1;
    }
    public Contains(int orderID,int productID,int quantity)
    {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }

    public int getOrderID()
    {
        return this.orderID;
    }

    public void setProductID(int productID)
    {
        this.productID = productID;
    }

    public int getProductID()
    {
        return this.productID;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return this.quantity;
    }
    
    
    /**
     * Method that checks that all mandatory fields are set
     *
     * @throws Exception
     */
    public void checkFields() throws Exception
    {
        // Check that everything is ok
        if ((this.orderID == -1)
            || (this.productID == -1)
            || (this.quantity == -1))
        {
            throw new Exception("Missing fields!");  // Something went wrong with the fields
        }
    }
    
    /**
     * Returns a string representation of this object
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(this.orderID).append("\n")
                .append("Product ID: ").append(this.productID).append("\n")
                .append("Quantity: ").append(this.quantity).append("\n");
        return sb.toString();
    }
}
