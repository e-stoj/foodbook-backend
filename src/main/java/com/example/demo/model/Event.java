package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private int eventId;
    private String eventName;
    @ManyToOne
    private Local local;
    private LocalDate date;
    private LocalTime time;
    private String motive;
    @ManyToMany
    private List<User> participants;
    @JsonIgnore
    @OneToMany
    private List<Message> messages;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Confirm> confirmList;

    public Event(String eventName, LocalDate date, LocalTime time, String motive, List<User> participants) {
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.motive = motive;
        this.participants = participants;
        this.messages = new ArrayList<>();
        this.confirmList = new ArrayList<>();
    }

    public Event() {
        this.messages = new ArrayList<>();
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    @JsonIgnore
    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Confirm> getConfirmList() {
        return confirmList;
    }

    public void setConfirmList(List<Confirm> confirmList) {
        this.confirmList = confirmList;
    }
}
