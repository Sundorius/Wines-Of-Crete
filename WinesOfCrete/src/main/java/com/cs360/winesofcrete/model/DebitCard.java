package com.cs360.winesofcrete.model;

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
public class DebitCard
{
    private String number;
    private int verificationCode;
    private String expirationDate;
    private String type;
    
    public DebitCard()
    {
        this.number = "";
        this.verificationCode = -1;
        this.type = "";
        this.expirationDate = "";
    }
    
    public DebitCard(String number,
            int verificationCode,
            String expirationDate,
            String type)
    {
        this.number = number;
        this.verificationCode = verificationCode;
        this.expirationDate = expirationDate;
        this.type = type;
    }
    
    public void setNumber(String number)
    {
        this.number = number;
    }
    
    public String getNumber()
    {
        return this.number;
    }
    
    public void setVerificationCode(int verificationCode)
    {
        this.verificationCode = verificationCode;
    }
    
    public int getVerificationCode()
    {
        return this.verificationCode;
    }
    
    public void setExpirationDate (String expirationDate)
    {
        this.expirationDate = expirationDate;
    }
    
    public String getExpirationDate()
    {
        return this.expirationDate;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    
    /**
     * Method that checks that all mandatory fields are set
     *
     * @throws Exception
     */
    public void checkFields() throws Exception
    {
        // Check that everything is ok
        if ((this.number == null || this.number.trim().isEmpty())
            || (this.verificationCode == -1)
            || (this.type == null || this.type.trim().isEmpty())
            || (this.expirationDate == null || this.expirationDate.trim().isEmpty()))
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
        sb.append("Number: ").append(this.number).append("\n")
                .append("Verification Code: ").append(this.verificationCode).append("\n")
                .append("Type: ").append(this.type).append("\n")
                .append("Expiration Date: ").append(this.expirationDate).append("\n");
        return sb.toString();
    }
}
