package com.rsr.frankly.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleUser {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("mail_id")
    @Expose
    private String mailId;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("designation")
    @Expose
    private String designation;

    /**
     * No args constructor for use in serialization
     *
     */
    public SingleUser() {
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param dob
     * @param mailId
     * @param mobileNo
     * @param designation
     * @param userId
     */
    public SingleUser(String userId, String firstName, String lastName, String dob, String mailId, String mobileNo, String designation) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.mailId = mailId;
        this.mobileNo = mobileNo;
        this.designation = designation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SingleUser withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public SingleUser withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SingleUser withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public SingleUser withDob(String dob) {
        this.dob = dob;
        return this;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public SingleUser withMailId(String mailId) {
        this.mailId = mailId;
        return this;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public SingleUser withMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public SingleUser withDesignation(String designation) {
        this.designation = designation;
        return this;
    }

}