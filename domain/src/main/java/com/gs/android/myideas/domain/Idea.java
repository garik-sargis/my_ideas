package com.gs.android.myideas.domain;


public class Idea {
    private final String text;

    public Idea(final String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}