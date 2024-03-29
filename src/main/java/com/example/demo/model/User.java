package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer userId;
    private String name;
    private String surname;
    @Column(unique = true)
    private String login;
    private String password;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    @ManyToMany
    private Set<User> friendsList;
    @JsonIgnore
    @ManyToMany
    private List<Event> events;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Confirm> confirmList;

    public User(String name, String surname, String login, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.friendsList = new HashSet<>();
        this.events = new ArrayList<>();
        this.confirmList = new ArrayList<>();
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<User> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(Set<User> friendsList) {
        this.friendsList = friendsList;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Confirm> getConfirmList() {
        return confirmList;
    }

    public void setConfirmList(List<Confirm> confirmList) {
        this.confirmList = confirmList;
    }
}
