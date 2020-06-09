package com.gms.entity;

/**
 * Created by Orion on 2020/6/9 13:21
 */
public class trading {
    private long tradingID;
    private int userID;
    private int tradingType;
    private String counterParty;
    private double transactionAmount;
    private String tradingContent;

    public long getTradingID() {
        return tradingID;
    }

    public void setTradingID(long tradingID) {
        this.tradingID = tradingID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTradingType() {
        return tradingType;
    }

    public void setTradingType(int tradingType) {
        this.tradingType = tradingType;
    }

    public String getCounterParty() {
        return counterParty;
    }

    public void setCounterParty(String counterParty) {
        this.counterParty = counterParty;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTradingContent() {
        return tradingContent;
    }

    public void setTradingContent(String tradingContent) {
        this.tradingContent = tradingContent;
    }
}
