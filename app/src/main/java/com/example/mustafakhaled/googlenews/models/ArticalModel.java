package com.example.mustafakhaled.googlenews.models;

import com.google.gson.annotations.SerializedName;

/*
 * Created by Mustafa Khaled on 9/15/2018.
 *
 */ public class ArticalModel {

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SourceModel getSourceModel() {
        return sourceModel;
    }

    public void setSourceModel(SourceModel sourceModel) {
        this.sourceModel = sourceModel;
    }


    @SerializedName("author")
     String author;
    @SerializedName("title")
     String title;
     @SerializedName("description")
     String description;
     @SerializedName("url")
     String url;
     @SerializedName("urlToImage")
     String urlToImage;
     @SerializedName("publishedAt")
     String publishedAt;
     @SerializedName("content")
     String content;
     @SerializedName("source")
     SourceModel sourceModel;

     boolean loved;

    public boolean isLoved() {
        return loved;
    }

    public void setLoved(boolean loved) {
        this.loved = loved;
    }
}
