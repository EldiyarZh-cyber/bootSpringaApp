package com.example.bootspring.models;



import javax.persistence.*;
import java.sql.Blob;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    @Lob
    private Blob photo;
    @Column(unique = true)
    private String password;

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @OneToMany(mappedBy = "publishedBy")
    private Set<Post> publishedEstates;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Blob getPhoto() {
        return photo;
    }

    public User setPhoto(Blob photo) {
        this.photo = photo;
        return this;
    }

    public Set<Post> getPublishedEstates() {
        return publishedEstates;
    }

    public User setPublishedEstates(Set<Post> publishedEstates) {
        this.publishedEstates = publishedEstates;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public User setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }
}
