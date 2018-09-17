package com.example.mustafakhaled.googlenews.models;

import com.google.gson.annotations.SerializedName;

/*
 * Created by Mustafa Khaled on 9/15/2018.
 *
 */ public class SourceModel{
     @SerializedName("id")
    String id;
     @SerializedName("name")
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
