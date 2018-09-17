package com.example.mustafakhaled.googlenews.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
 * Created by Mustafa Khaled on 9/15/2018.
 *
 */ public class NewResposeModel {


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public ArrayList<ArticalModel> getArticalModels() {
        return articalModels;
    }

    public void setArticalModels(ArrayList<ArticalModel> articalModels) {
        this.articalModels = articalModels;
    }

    @SerializedName("totalResults")
    int totalResult;
    @SerializedName("articles")
     ArrayList<ArticalModel> articalModels;
    @SerializedName("status")
    String status;
}
