package com.rsr.frankly.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("single_user")
    @Expose
    private List<SingleUser> singleUser = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public SingleResponse() {
    }

    /**
     *
     * @param singleUser
     * @param error
     */
    public SingleResponse(Boolean error, List<SingleUser> singleUser) {
        super();
        this.error = error;
        this.singleUser = singleUser;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public SingleResponse withError(Boolean error) {
        this.error = error;
        return this;
    }

    public List<SingleUser> getSingleUser() {
        return singleUser;
    }

    public void setSingleUser(List<SingleUser> singleUser) {
        this.singleUser = singleUser;
    }

    public SingleResponse withSingleUser(List<SingleUser> singleUser) {
        this.singleUser = singleUser;
        return this;
    }

}