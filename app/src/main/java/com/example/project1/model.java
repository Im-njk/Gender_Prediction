package com.example.project1;

import com.google.gson.annotations.SerializedName;

public class model {

    @SerializedName("name")
    private String name;
    @SerializedName("gender")
    private String gender;
    @SerializedName("probability")
    private Double probability;
    @SerializedName("count")
    private Integer count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
