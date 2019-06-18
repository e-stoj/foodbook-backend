package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Local {

    @Id
    @GeneratedValue
    private int localId;
    private String name;
    private String address;
    private String phoneNumber;
    @ElementCollection(targetClass=Motives.class)
    @Enumerated(EnumType.STRING)
    private List<Motives> motives;

    public Local(String name, String address, String phoneNumber, List<Motives> motives) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.motives = motives;
    }

    public Local() {
    }

    public int getLocalId() {
        return localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Motives> getMotives() {
        return motives;
    }

    public void setMotives(List<Motives> motives) {
        this.motives = motives;
    }

}
