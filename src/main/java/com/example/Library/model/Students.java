package com.example.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

//@Entity
public class Students {
    // @Id
    private int id;
    private String name;
    private String surname;
    private int phone;
    private List<Book> books;


    public List<Book> getBooks() {

        return this.books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Students(int id, String name, String surname, int phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public Students() {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

}
