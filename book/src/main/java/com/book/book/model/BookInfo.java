package com.book.book.model;

public class BookInfo {
    private Book book;
    private Rating rating;

    public BookInfo(Book book, Rating rating) {
        this.book = book;
        this.rating = rating;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
