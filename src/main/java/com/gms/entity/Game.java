package com.gms.entity;

import java.util.Date;

public class Game {
    private int gameId;
    private String gameName;
    private String Event;
    private int holdingTime;
    private String sponsor;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public int getHoldingTime() {
        return holdingTime;
    }

    public void setHoldingTime(int holdingTime) {
        this.holdingTime = holdingTime;
    }


    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }


}
