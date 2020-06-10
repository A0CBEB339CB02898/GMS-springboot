package com.gms.entity;

/**
 * Created by PASSERFBER on 2020/6/10 18:03
 */

public class Appointment {
    private int idAppointment;
    private int idPlace;
    private int startAppointment;
    private int overAppointment;
    private int userId;
    private String character;
    private int idCharge;

    public int getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(int idAppointment) {
        this.idAppointment = idAppointment;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public int getStartAppointment() {
        return startAppointment;
    }

    public void setStartAppointment(int startAppointment) {
        this.startAppointment = startAppointment;
    }

    public int getOverAppointment() {
        return overAppointment;
    }

    public void setOverAppointment(int overAppointment) {
        this.overAppointment = overAppointment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getIdCharge() {
        return idCharge;
    }

    public void setIdCharge(int idCharge) {
        this.idCharge = idCharge;
    }
}
