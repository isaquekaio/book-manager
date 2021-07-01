package com.example.bookmanager.domain;

public class Book {
    private Long id;
    private String title;
    private String author;
    private String publishing_house;
    private boolean borrowed;

    public Book(Long id, String title, String author, String publishing_house, boolean borrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishing_house = publishing_house;
        this.borrowed = borrowed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishing_house() {
        return publishing_house;
    }

    public void setPublishing_house(String publishing_house) {
        this.publishing_house = publishing_house;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
}
