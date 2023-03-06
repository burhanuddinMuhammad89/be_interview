package com.example.demo.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EroWebReq {
    @JsonProperty("original_title")
    private String original_title;

    @NotBlank
    @JsonProperty("name_file")
    private String name_file;

    @JsonProperty("vote_average")
    private double vote_average;

    @JsonProperty("createdAt")
    private Date createdAt;


    public String getOriginal_title() {
        return this.original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getName_file() {
        return this.name_file;
    }

    public void setName_file(String name_file) {
        this.name_file = name_file;
    }

    public double getVote_average() {
        return this.vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }


    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
