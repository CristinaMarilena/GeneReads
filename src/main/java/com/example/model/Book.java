package com.example.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "book")
@Proxy(lazy = false)
public class Book implements BookInterface{

    @Id
    @Column(name = "bookId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "apiUrl")
    private String apiUrl;

    @Column(name = "bookImage")
    private String bookImage;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "books_authors",
            joinColumns = { @JoinColumn(name = "bookId") },
            inverseJoinColumns = { @JoinColumn(name = "authorId") })
    @JsonBackReference
    private Set<Author> authors;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "books_categories",
            joinColumns = { @JoinColumn(name = "bookId") },
            inverseJoinColumns = { @JoinColumn(name = "categoryId") })
    @JsonBackReference
    private Set<BookCategory> categories;

    public Book() {
    }

    public Book(String title, String apiUrl, String bookImage, String description, Set<Author> authors, Set<BookCategory> categories) {
        this.title = title;
        this.apiUrl = apiUrl;
        this.bookImage = bookImage;
        this.description = description;
        this.authors = authors;
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        if(description.length()<3000)
        this.description = description;
    }

    public Set<BookCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<BookCategory> categories) {
        this.categories = categories;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Author> getAuthors() {
        return authors;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }
}
