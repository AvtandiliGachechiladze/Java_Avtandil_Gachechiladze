package com.exam.assignment;

import java.time.LocalDate;

public class Post {
    private int id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDate creationDate;

    private Post(int id, String title, String content, String author, LocalDate creationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.creationDate = creationDate;
    }

    private Post(String title, String content, String author, LocalDate creationDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.creationDate = creationDate;
    }

    public static Post of(int id, String title, String content, String author, LocalDate creationDate){
        return new Post(id, title, content, author, creationDate);
    }

    public static Post of(String title, String content, String author){
        return new Post(title, content, author, null);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
