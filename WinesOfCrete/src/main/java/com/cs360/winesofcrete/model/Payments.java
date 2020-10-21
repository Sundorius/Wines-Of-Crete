package com.cs360.winesofcrete.model;

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
public class Payments
{
    private int paymentID;
    private double amountPaid;
    private String buyerUsername;
    
    public Payments(int paymentID, double amountPaid, String buyerUsername)
    {
        this.paymentID = paymentID;
        this.amountPaid = amountPaid;
        this.buyerUsername = buyerUsername;
    }

    public Payments() 
    {
        this.paymentID = -1;
        this.amountPaid = -1;
        this.buyerUsername = "";
    }
    
    public void setPaymentID(int paymentID)
    {
        this.paymentID = paymentID;
    }
    
    public int getPaymentID()
    {
        return this.paymentID;
    }
    
    public void setAmountPaid(double amountPaid)
    {
        this.amountPaid = amountPaid;
    }
    
    public double getAmountPaid()
    {
        return this.amountPaid;
    }
    
    public void setBuyerUsername(String buyerUsername)
    {
        this.buyerUsername = buyerUsername;
    }
    
    public String getBuyerUsername()
    {
        return this.buyerUsername;
    }
    
    /**
     * Method that checks that all mandatory fields are set
     *
     * @throws Exception
     */
    public void checkFields() throws Exception
    {
        // Check that everything is ok
        if ((this.amountPaid == -1)
            || (this.buyerUsername == null || this.buyerUsername.trim().isEmpty()))
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
        sb.append("Payment ID: ").append(this.paymentID).append("\n")
                .append("Amount Paid: ").append(this.amountPaid).append("\n")
                .append("Buyer Username: ").append(this.buyerUsername).append("\n");
        return sb.toString();
    }
}
