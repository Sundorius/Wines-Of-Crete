package com.cs360.winesofcrete.model;

import java.io.Serializable;

/**
 *
 * @author crow
 */
public class User implements Serializable
{

    private String username;    // (unique)
    private String password;
    private String email;       // (unique)
    private String name;
    private String phone_number;
    private String account_type;
    private String address;
    private String debit_card_number;
    private double debt;

    /**
     * Default Constructor
     *
     */
    public User()
    {
        this.username = "";
        this.password = "";
        this.email = "";
        this.name = "";
        this.phone_number = "";
        this.account_type = "";
        this.address = "";
        this.debit_card_number = "";
        this.debt = 0.00;
    }

    /**
     * Constructor
     *
     * @param username
     * @param password
     * @param email
     * @param name
     * @param phone_number
     * @param account_type
     * @param address
     * @param debit_card_number
     *
     */
    public User(String username,
        String password,
        String email,
        String name,
        String phone_number,
        String account_type,
        String address,
        String debit_card_number)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone_number = phone_number;
        this.account_type = account_type;
        this.address = address;
        this.debit_card_number = debit_card_number;
        this.debt = 0;
    }

    /**
     * Method that checks that all mandatory fields are set
     *
     * @throws Exception
     */
    public void checkFields() throws Exception
    {
        // Check that everything is ok
        if ((this.username == null || this.username.trim().isEmpty())
            || (this.email == null || this.email.trim().isEmpty())
            || (this.password == null || this.password.trim().isEmpty())
            || (this.name == null || this.name.trim().isEmpty())
            || (this.phone_number == null || this.phone_number.trim().isEmpty())
            //|| (account_type == null || account_type.trim().isEmpty())
            || (this.address == null || this.address.trim().isEmpty())
            || (this.debit_card_number == null || this.debit_card_number.trim().isEmpty())
            || (this.debt == -1))
        {
            throw new Exception("Missing fields!");  // Something went wrong with the fields
        }
    }

    /* Getters and Setters */
    /**
     * Get the debt
     *
     * @return
     */
    public double getDebt()
    {
        return this.debt;
    }

    /**
     * Set the debt
     *
     * @param debt
     */
    public void setDebt(double debt)
    {
        this.debt = debt;
    }

    /**
     * Get the user name
     *
     * @return
     */
    public String getUsername()
    {
        return this.username;
    }

    /**
     * Set the username
     *
     * @param username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Get the email
     *
     * @return
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     * Set the email
     *
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Get the debit card number
     *
     * @return
     */
    public String getDebitCardNumber()
    {
        return this.debit_card_number;
    }

    /**
     * Set the debit card number
     *
     * @param debit_card_id
     */
    public void setDebitCardNumber(String debit_card_id)
    {
        this.debit_card_number = debit_card_id;
    }

    /**
     * Get the password
     *
     * @return
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * Set the password of this user
     *
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Returns the name of the user
     *
     * @return
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Sets the name
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Get phone number
     *
     * @return phone number
     */
    public String getPhoneNumber()
    {
        return this.phone_number;
    }

    /**
     * Set phone number name
     *
     * @param phone_number
     */
    public void setPhoneNumber(String phone_number)
    {
        this.phone_number = phone_number;
    }

    /**
     * Get address
     *
     * @return
     */
    public String getAddress()
    {
        return this.address;
    }

    /**
     * Set address
     *
     * @param address
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * Get account type
     *
     * @return
     */
    public String getAccountType() {
        return this.account_type;
    }

    /**
     * Set account type
     *
     * @param account_type
     */
    public void setAccountType(String account_type) {
        this.account_type = account_type;
    }
    /**
     * Returns a string representation of this object
     *
     * @return
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Username: ").append(this.username).append("\n")
            .append("Email: ").append(this.email).append("\n")
            .append("Password: ").append(this.password).append("\n")
            .append("Name: ").append(this.name).append("\n")
            .append("Phone Number: ").append(this.phone_number).append("\n")
            .append("Address: ").append(this.address).append("\n")
            .append("Debit Card Number: ").append(this.debit_card_number).append("\n")    
            .append("Debt: ").append(this.debt).append("\n");
        //.append("Account Type: ").append(this.account_type).append("\n");
        return sb.toString();
    }

}
