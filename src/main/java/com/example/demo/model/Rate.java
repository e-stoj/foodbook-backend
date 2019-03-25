package com.example.demo.model;

public class Rate {
    private int rateId;
    private int climateRate;
    private int foodRate;
    private int serviceRate;
    private User user;
    private Local local;

    public Rate(int climateRate, int foodRate, int serviceRate, User user, Local local) {
        this.climateRate = climateRate;
        this.foodRate = foodRate;
        this.serviceRate = serviceRate;
        this.user = user;
        this.local = local;
    }

    public Rate() {
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public int getClimateRate() {
        return climateRate;
    }

    public void setClimateRate(int climateRate) {
        this.climateRate = climateRate;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public int getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(int serviceRate) {
        this.serviceRate = serviceRate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
}
