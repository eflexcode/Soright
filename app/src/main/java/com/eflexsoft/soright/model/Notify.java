package com.eflexsoft.soright.model;

public class Notify {

    String text;

    public Notify(String text) {
        this.text = text;
    }

    public Notify() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
