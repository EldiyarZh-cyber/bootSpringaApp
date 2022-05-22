package com.example.bootspring.models;


import  javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String price;
    @Lob
    private byte[] photo;

    private LocalDateTime createdDate;
    @ManyToOne
    private User publishedBy;

    public Long getId() {
        return id;
    }

    public Post setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public Post setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public Post setPhoto(byte[] photo) {
        this.photo = photo;
        return this;
    }


    public User getPublishedBy() {
        return publishedBy;
    }

    public Post setPublishedBy(User createdBy) {
        this.publishedBy = createdBy;
        return this;
    }
}
