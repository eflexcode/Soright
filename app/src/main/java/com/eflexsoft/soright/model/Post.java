package com.eflexsoft.soright.model;

public class Post {

    private String text;
    private String imageUri;

    public Post() {
    }

    public Post(String text, String imageUri) {
        this.text = text;
        this.imageUri = imageUri;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

}
