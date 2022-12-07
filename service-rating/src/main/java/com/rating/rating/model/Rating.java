package com.rating.rating.model;

public class Rating {

    private int id;
    private int bookId;
    private int stars;

    public Rating(int id, int bookId, int stars) {
        this.id = id;
        this.bookId = bookId;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
