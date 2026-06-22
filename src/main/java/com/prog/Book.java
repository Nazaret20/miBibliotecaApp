package com.prog;

public class Book {
    private int id, rating;
    private String title, author, notes, date, status;

    public Book(int id, String title, String author, int rating, String notes, String date, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.notes = notes;
        this.date = date;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", rating=" + rating + ", notes=" + notes
                + ", date=" + date + ", status=" + status + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}