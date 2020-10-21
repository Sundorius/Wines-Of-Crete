package com.cs360.winesofcrete.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crow
 */
public class Wine {
    private String name;
    private String winery;
    private List<String> varieties;
    private String color;
    private int year;
    private String img;
    private int product_id;
    private int quantity;
    private double price;
    
    public Wine()
    {
        this.product_id = -1;
        this.name = "";
        this.year = -1;
        this.color = "";
        this.price = -1;        
        this.winery = "";
        this.quantity = -1;
        this.img = null;
        this.varieties = null;
    };

    public Wine(int product_id,
            String name,
            String winery,
            String color,
            int year, 
            String img,
            double price,
            int quantity,
            List<String> varieties) {
        if(!color.equals("red")&&!color.equals("white")&&!color.equals("pink")){
            throw new IllegalArgumentException("The wine can only be red, white or pink!");
        }
        this.product_id = product_id;
        this.name = name;
        this.winery = winery;
        this.varieties = new ArrayList<>();
        this.color = color;
        this.year = year;
        this.img = img;
        this.price = price;
        this.quantity = quantity;
        this.varieties = varieties;
    }
    
   

    /**
     * @return the product_id
     */
    public int getProductID() {
        return this.product_id;
    }

    /**
     * @param product_id the product_id to set
     */
    public void setProductID(int product_id) {
        this.product_id = product_id;
    }
    
    
    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the winery
     */
    public String getWinery() {
        return this.winery;
    }

    /**
     * @param winery the winery to set
     */
    public void setWinery(String winery) {
        this.winery = winery;
    }

    /**
     * @return the varieties
     */
    public List<String> getVarieties() {
        return varieties;
    }

    /**
     * @param varieties the varieties to set
     */
    public void setVarieties(List<String> varieties) {
        this.varieties = varieties;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        if(!color.equals("red")&&!color.equals("white")&&!color.equals("rose")){
            throw new IllegalArgumentException("The wine can only be red, white or pink!");
        }        
        this.color = color;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the img
     */
    public String getImg() {
        return this.img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }
    
    /**
     * @return the price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * @return the quantity
     */
    public double getQuantity() {
        return this.quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
            || (this.name == null || this.name.trim().isEmpty())
            || (this.color == null || this.color.trim().isEmpty())
            || (this.winery == null || this.winery.trim().isEmpty())
            || (this.varieties == null || this.varieties.isEmpty())
            || (this.quantity == -1)
            || (this.year == -1)
            || (this.price == -1)
            || (this.img == null))
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
        sb.append("Wine: ").append(this.name).append("\n")
                .append("Wine ID: ").append(this.product_id).append("\n")
                .append("Color: ").append(this.color).append("\n")
                .append("Year: ").append(this.year).append("\n")
                .append("Winery: ").append(this.winery).append("\n")
                .append("Price: ").append(this.price).append("\n")
                .append("Varieties: ").append(this.varieties).append("\n")
                .append("Quantity: ").append(this.quantity).append("\n");
        return sb.toString();
    }
}
