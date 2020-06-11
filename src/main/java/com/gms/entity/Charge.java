package com.gms.entity;

/**
 * Created by PASSERFBER on 2020/6/10 17:53
 */
public class Charge {
    private int idCharge;
    private String placeName;
    private String week;
    private int startCharge;
    private int overCharge;
    private String light;
    private int cost;

    public int getIdCharge() {
        return idCharge;
    }

    public void setIdCharge(int idCharge) {
        this.idCharge = idCharge;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getStartCharge() {
        return startCharge;
    }

    public void setStartCharge(int startCharge) {
        this.startCharge = startCharge;
    }

    public int getOverCharge() {
        return overCharge;
    }

    public void setOverCharge(int overCharge) {
        this.overCharge = overCharge;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
