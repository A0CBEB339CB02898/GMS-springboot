package com.gms.entity;

public class Equipment {
    private String equipmentId;
    private String equipmentName;
    private int equipmentCost;
    private String equipmentStatus;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getEquipmentCost() {
        return equipmentCost;
    }

    public void setEquipmentCost(int equipmentCost) {
        this.equipmentCost = equipmentCost;
    }

    public String getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public String getEquipmentTime() {
        return equipmentTime;
    }

    public void setEquipmentTime(String equipmentTime) {
        this.equipmentTime = equipmentTime;
    }

    public String getEquipmentRenter() {
        return equipmentRenter;
    }

    public void setEquipmentRenter(String equipmentRenter) {
        this.equipmentRenter = equipmentRenter;
    }

    private String equipmentTime;
    private String equipmentRenter;


}
