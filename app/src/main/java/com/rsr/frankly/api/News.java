package com.rsr.frankly.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("grammar")
    @Expose
    private String grammar;
    @SerializedName("abusive")
    @Expose
    private String abusive;
    @SerializedName("negative")
    @Expose
    private String negative;
    @SerializedName("neutral")
    @Expose
    private String neutral;
    @SerializedName("positive")
    @Expose
    private String positive;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("similar")
    @Expose
    private String similar;
    @SerializedName("dissimilar")
    @Expose
    private String dissimilar;

    /**
     * No args constructor for use in serialization
     *
     */
    public News() {
    }

    /**
     *
     * @param similar
     * @param negative
     * @param grammar
     * @param abusive
     * @param distance
     * @param neutral
     * @param time
     * @param positive
     * @param title
     * @param url
     * @param dissimilar
     */
    public News(String url, String title, String time, String grammar, String abusive, String negative, String neutral, String positive, String distance, String similar, String dissimilar) {
        super();
        this.url = url;
        this.title = title;
        this.time = time;
        this.grammar = grammar;
        this.abusive = abusive;
        this.negative = negative;
        this.neutral = neutral;
        this.positive = positive;
        this.distance = distance;
        this.similar = similar;
        this.dissimilar = dissimilar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public News withUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public News withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public News withTime(String time) {
        this.time = time;
        return this;
    }

    public String getGrammar() {
        return grammar;
    }

    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }

    public News withGrammar(String grammar) {
        this.grammar = grammar;
        return this;
    }

    public String getAbusive() {
        return abusive;
    }

    public void setAbusive(String abusive) {
        this.abusive = abusive;
    }

    public News withAbusive(String abusive) {
        this.abusive = abusive;
        return this;
    }

    public String getNegative() {
        return negative;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

    public News withNegative(String negative) {
        this.negative = negative;
        return this;
    }

    public String getNeutral() {
        return neutral;
    }

    public void setNeutral(String neutral) {
        this.neutral = neutral;
    }

    public News withNeutral(String neutral) {
        this.neutral = neutral;
        return this;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public News withPositive(String positive) {
        this.positive = positive;
        return this;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public News withDistance(String distance) {
        this.distance = distance;
        return this;
    }

    public String getSimilar() {
        return similar;
    }

    public void setSimilar(String similar) {
        this.similar = similar;
    }

    public News withSimilar(String similar) {
        this.similar = similar;
        return this;
    }

    public String getDissimilar() {
        return dissimilar;
    }

    public void setDissimilar(String dissimilar) {
        this.dissimilar = dissimilar;
    }

    public News withDissimilar(String dissimilar) {
        this.dissimilar = dissimilar;
        return this;
    }

}