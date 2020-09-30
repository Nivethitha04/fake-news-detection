package com.rsr.frankly.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    @SerializedName("error")
    @Expose
    private Boolean err;
    @SerializedName("message")
    @Expose
    private String msg;

    public Boolean getErr() {
        return err;
    }

    public void setErr(Boolean err) {
        this.err = err;
    }

    public DefaultResponse withErr(Boolean err) {
        this.err = err;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DefaultResponse withMsg(String msg) {
        this.msg = msg;
        return this;
    }

}
