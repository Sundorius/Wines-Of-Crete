package com.cs360.winesofcrete.model;

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
public class ConsistsOf
{
    private int product_id;
    private int variety_id;
    
    public ConsistsOf()
    {
        this.product_id = -1;
        this.variety_id = -1;
    }
    
    public ConsistsOf(int product_id, int variety_id)
    {
        this.product_id = product_id;
        this.variety_id = variety_id;
    }
    
    public void setProductID(int product_id)
    {
        this.product_id = product_id;
    }
    
    public int getProductID()
    {
        return this.product_id;
    }
    
    public void setVarietyID(int variety_id)
    {
        this.variety_id = variety_id;
    }
    
    public int getVarietyID()
    {
        return this.variety_id;
    }
    
    /**
     * Method that checks that all mandatory fields are set
     *
     * @throws Exception
     */
    public void checkFields() throws Exception
    {
        // Check that everything is ok
        if ((this.product_id == -1)
            || (this.variety_id == -1))
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
        sb.append("Product ID: ").append(this.product_id).append("\n")
                .append("Variety ID: ").append(this.variety_id).append("\n");
        return sb.toString();
    }
    
}
