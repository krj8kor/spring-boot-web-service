package com.kavya.assignment.view;

public class UrlState {

    private final long id;
    private final String content;

    public UrlState(long id, String status) {
        this.id = id;
        this.content = status;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
