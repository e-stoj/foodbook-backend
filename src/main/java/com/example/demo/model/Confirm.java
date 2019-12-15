package com.example.demo.model;

import com.example.demo.model.enums.Confirmation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Confirm {

    @Id
    @GeneratedValue
    private Integer confirmId;
    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Event event;

    @Enumerated(EnumType.STRING)
    private Confirmation confirmation;

    public Confirm(User user, Event event) {
        this.user = user;
        this.event = event;
        this.confirmation = Confirmation.OCZEKUJACE;
    }

    public Confirm() {
    }

    public Integer getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(Integer confirmId) {
        this.confirmId = confirmId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Confirmation getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Confirmation confirmation) {
        this.confirmation = confirmation;
    }
}
