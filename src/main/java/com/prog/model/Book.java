package com.prog.model;

/**
 * Represents a book in the personal library.
 * Contains all data associated with a book such as title, author, status,
 * rating and cover.
 */
public class Book {
    private int id, rating;
    private String title, author, notes, date, status, coverURL;

    /**
     * Creates a new book with all its data.
     * 
     * @param id       Unique identifier of the book
     * @param title    Title of the book
     * @param author   Author of the book
     * @param rating   Rating from 1 to 5, 0 if not rated
     * @param notes    Personal notes about the book
     * @param date     Reading date in dd/mm/yyyy format, empty if not applicable
     * @param status   Book status: LEIDO, LEYENDO, PROXIMO or QUIERO_LEER
     * @param coverURL Cover URL obtained from Google Books, empty if not fetched
     */
    public Book(int id, String title, String author, int rating, String notes, String date, String status,
            String coverURL) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.notes = notes;
        this.date = date;
        this.status = status;
        this.coverURL = coverURL;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", rating=" + rating + ", notes=" + notes
                + ", date=" + date + ", status=" + status + ", coverURL=" + coverURL + "]";
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

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }
}