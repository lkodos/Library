package ru.lkodos.library.model;

import jakarta.validation.constraints.*;

public class Book {

    private Integer id;

    @NotEmpty(message = "The field must not be empty.")
    @Size(min = 3, max = 30, message = "The title must be 3 to 30 characters long.")
    private String title;

    @NotEmpty(message = "The field must not be empty.")
    @Size(min = 3, max = 30, message = "The name must be 3 to 30 characters long.")
    private String author;

    @NotNull(message = "The field must not be empty.")
    @Min(value = 1926, message = "Year must be a 4-digit number and not earlier than 1927")
    @Max(value = 9999, message = "Year must be a 4-digit number and not earlier than 1927")
    private Integer year;

    private Integer personId;

    public Book(Integer id, String title, String author, Integer year, Integer personId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.personId = personId;
    }

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
