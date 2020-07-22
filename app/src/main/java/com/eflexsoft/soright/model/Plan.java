package com.eflexsoft.soright.model;

public class Plan {

    private String text;
    private int imageUri;

    public Plan() {
    }

    public Plan(String text, int imageUri) {
        this.text = text;
        this.imageUri = imageUri;
    }

    public String getText() {
        return text;
    }

    public void setText(String  text) {
        this.text = text;
    }

    public int getImageUri() {
        return imageUri;
    }

    public void setImageUri(int imageUri) {
        this.imageUri = imageUri;
    }

}
