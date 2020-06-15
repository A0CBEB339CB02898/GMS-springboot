package com.gms.entity;

/**
 * Created by PASSERFBER on 2020/6/10 18:03
 */

public class Appointment {
    private int idAppointment;
    private int idPlace;
    private int startAppointment;
    private int  overAppointment;
    private int userId;
    private String purpose;
    private int idCharge;
    private String placeName;
    private String location;
    private String userName;
    private int cost;
    private int idDelete;

    public int getIdDelete() {
        return idDelete;
    }

    public void setIdDelete(int idDelete) {
        this.idDelete = idDelete;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getIdCharge() {
        return idCharge;
    }

    public void setIdCharge(int idCharge) {
        this.idCharge = idCharge;
    }
}
