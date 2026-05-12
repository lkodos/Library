package ru.lkodos.library.model;

import jakarta.validation.constraints.*;

public class Person {

    private Integer id;

    @NotEmpty(message = "The field must not be empty.")
    @Size(min = 3, max = 30, message = "The name must be 3 to 30 characters long.")
    private String name;

    @NotNull(message = "The field must not be empty.")
    @Min(value = 1926, message = "Year must be a 4-digit number and not earlier than 1927")
    @Max(value = 9999, message = "Year must be a 4-digit number and not earlier than 1927")
    private Integer year;

    public Person() {
    }

    public Person(Integer id, String name, Integer year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
