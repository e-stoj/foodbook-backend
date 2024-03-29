package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private int messageId;
    @ManyToOne
    private User user;
    private String text;
    @ManyToOne
    private Event event;

    public Message(User user, String text, Event event) {
        this.user = user;
        this.text = text;
        this.event = event;
    }

    public Message() {
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
