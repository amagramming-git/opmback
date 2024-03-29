package com.openmemo.opmback.mapper.post;

import java.util.Date;

public class Post {
    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * post.id
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * post.customerId
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    private Integer customerid;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * post.content
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * post.created_at
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * post.updated_at
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    private Date updatedAt;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column post.id
     *
     * @return the value of post.id
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database
     * column post.id
     *
     * @param id the value for post.id
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column post.customerId
     *
     * @return the value of post.customerId
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public Integer getCustomerid() {
        return customerid;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database
     * column post.customerId
     *
     * @param customerid the value for post.customerId
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column post.content
     *
     * @return the value of post.content
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database
     * column post.content
     *
     * @param content the value for post.content
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column post.created_at
     *
     * @return the value of post.created_at
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database
     * column post.created_at
     *
     * @param createdAt the value for post.created_at
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column post.updated_at
     *
     * @return the value of post.updated_at
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database
     * column post.updated_at
     *
     * @param updatedAt the value for post.updated_at
     *
     * @mbg.generated Sun Dec 03 13:07:23 JST 2023
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
