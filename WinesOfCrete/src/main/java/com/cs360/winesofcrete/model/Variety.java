package com.cs360.winesofcrete.model;

/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
public class Variety
{
    private int varietyID;
    private String name;
    
    public Variety()
    {
        this.varietyID = -1;
        this.name = "";
    }
    
    public Variety (int varietyID, String name)
    {
        this.varietyID = varietyID;
        this.name = name;
    }
    
    public void setVarietyID(int varietyID)
    {
        this.varietyID = varietyID;
    }
    
    public int getVarietyID()
    {
        return this.varietyID;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Method that checks that all mandatory fields are set
     *
     * @throws Exception
     */
    public void checkFields() throws Exception
    {
        // Check that everything is ok
        if ((this.varietyID == -1)
            || (this.name == null || this.name.trim().isEmpty()))
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
        sb.append("Variety ID: ").append(this.varietyID).append("\n")
                .append("Name: ").append(this.name).append("\n");
        return sb.toString();
    }
    
}
