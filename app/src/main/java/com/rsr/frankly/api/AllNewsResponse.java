package com.rsr.frankly.api;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllNewsResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("news")
    @Expose
    private List<News> news = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public AllNewsResponse() {
    }

    /**
     *
     * @param news
     * @param error
     */
    public AllNewsResponse(Boolean error, List<News> news) {
        super();
        this.error = error;
        this.news = news;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public AllNewsResponse withError(Boolean error) {
        this.error = error;
        return this;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public AllNewsResponse withNews(List<News> news) {
        this.news = news;
        return this;
    }

}