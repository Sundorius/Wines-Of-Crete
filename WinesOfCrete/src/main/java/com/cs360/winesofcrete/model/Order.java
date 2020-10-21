package com.cs360.winesofcrete.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crow
 */
public class Order
{

    private int order_id;
    private String buyer_username;
    private String buyer_name;

    //TODO We have to discuss if the date is going to be in string or object format <3
    private String date;
    private List<Integer> winesID;//the content of the order
    private double amount;
    private double amount_due;
    private int status; // 0 for not paid off  , 1 for paid off
    private double discount;  // TODO make it float or leave it int.
    private int days_left_to_pay;

    public Order()
    {
        //Default constructor initializes the day of the order with today's date.
        LocalDate localDate = LocalDate.now();
        
        winesID = new ArrayList<>();
        
        this.date = localDate.toString();
        this.order_id = -1;
        this.buyer_username = "";
        this.buyer_name = "";
        this.amount = 0.00;
        this.status = -1;
        this.amount_due = 0.00;
        this.discount = 0.00;
        this.days_left_to_pay = -1;

    }

    public Order(String client_id,
        String clientName,
        double amount,
        int status,
        int days_left_to_pay)
    {
        this();
        if (status != 0 && status != 1 && status != 2)
        {
            throw new IllegalArgumentException("The value for the order status is invalid!");
        }
        this.buyer_username = client_id;
        this.buyer_name = clientName;
        this.amount = amount;
        this.status = status;
        this.days_left_to_pay = days_left_to_pay;
    }

    public Order(String client_id,
        String clientName,
        double amount,
        int status)
    {
        this();
        if (status != 0 && status != 1 && status != 2)
        {
            throw new IllegalArgumentException("The value for the order status is invalid!");
        }
        this.buyer_username = client_id;
        this.buyer_name = clientName;
        this.amount = amount;
        this.status = status;
    }

    public void setOrderID(int order_id)
    {
        this.order_id = order_id;
    }

    public int getOrderID()
    {
        return this.order_id;
    }

    public void setBuyerUsername(String buyer_username)
    {
        this.buyer_username = buyer_username;
    }

    public String getBuyerUsername()
    {
        return this.buyer_username;
    }

    public void setDiscount(double discount)
    {
        this.discount = discount;
    }

    public double getDiscount()
    {
        return this.discount;
    }

    public String getBuyerName()
    {
        return this.buyer_name;
    }

    public void setBuyerName(String buyer_name)
    {
        this.buyer_name = buyer_name;
    }

    public String getDate()
    {
        return this.date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public List<Integer> getWinesID()
    {
        return this.winesID;
    }

    public void addWine(int wineID)
    {
        this.winesID.add(wineID);
    }

    public double getAmount()
    {
        return this.amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public void setAmountDue(double amount_due)
    {
        this.amount_due = amount_due;
    }

    public double getAmountDue()
    {
        return this.amount_due;
    }

    public int getStatus()
    {
        return this.status;
    }

    public void setDaysLeftToPay(int days_left_to_pay)
    {
        this.days_left_to_pay = days_left_to_pay;
    }

    public int getDaysLeftToPay()
    {
        return this.days_left_to_pay;
    }

    public void setStatus(int status)
    {
        if (status != 0 && status != 1 && status != 2)
        {
            throw new IllegalArgumentException("The value for the order status is invalid!");
        }
        this.status = status;
    }

    
    /**
     * Method that checks that all mandatory fields are set
     *
     * @throws Exception
     */
    public void checkFields() throws Exception
    {
        // Check that everything is ok
        if ((this.order_id == -1)
            || (this.status ==-1)
            || (this.amount == -1)
            || (this.amount_due == -1)
            || (this.days_left_to_pay == -1)
            || (this.buyer_username == null || this.buyer_username.trim().isEmpty())
            || (this.buyer_name == null || this.buyer_name.trim().isEmpty())
            || (this.date == null || this.date.trim().isEmpty()))
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
        sb.append("Order ID: ").append(this.order_id).append("\n")
                .append("Status: ").append(this.status).append("\n")
                .append("Buyer Username: ").append(this.buyer_username).append("\n")
                .append("Buyer Name: ").append(this.buyer_name).append("\n")
                .append("Amount: ").append(this.amount).append("\n")
                .append("Amount Due: ").append(this.amount_due).append("\n")
                .append("Date: ").append(this.date).append("\n")
                .append("Days Left to Pay: ").append(this.days_left_to_pay).append("\n");
        return sb.toString();
    }
}
