package com.gms.entity;

import java.util.Date;

public class Field {
    private int placeId;
    private String placeName;
    private Date appointment;
    private Date chargeableTime;
    private  String character;
    private  String location;
    private  int userId;
    private String charge;

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Date getAppointment() {
        return appointment;
    }

    public void setAppointment(Date appointment) {
        this.appointment = appointment;
    }

    public Date getChargeableTime() {
        return chargeableTime;
    }

    public void setChargeableTime(Date chargeableTime) {
        this.chargeableTime = chargeableTime;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "filed{" +
                "placeId=" + placeId +
                ", placeName='" + placeName + '\'' +
                ", appointment=" + appointment +
                ", chargeableTime=" + chargeableTime +
                ", character='" + character + '\'' +
                ", location='" + location + '\'' +
                ", userId=" + userId +
                ", charge='" + charge + '\'' +
                '}';
    }
}
