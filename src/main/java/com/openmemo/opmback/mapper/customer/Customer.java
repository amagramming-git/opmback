package com.openmemo.opmback.mapper.customer;

public class Customer {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.id
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.email
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.username
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.password
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    private String password;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.id
     *
     * @return the value of customer.id
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.id
     *
     * @param id the value for customer.id
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.email
     *
     * @return the value of customer.email
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.email
     *
     * @param email the value for customer.email
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.username
     *
     * @return the value of customer.username
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.username
     *
     * @param username the value for customer.username
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.password
     *
     * @return the value of customer.password
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.password
     *
     * @param password the value for customer.password
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public void setPassword(String password) {
        this.password = password;
    }
}