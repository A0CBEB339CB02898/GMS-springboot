package com.gms.entity;

/**
 * Created by Orion on 2020/6/9 13:21
 */
public class Trading {
    private int tradingId;
    private int userId;
    private int tradingType;
    private long tradingTime;
    private String counterParty;
    private int transactionAmount;
    private String tradingContent;
    private int isDelete;



    public int getTradingId() {
        return tradingId;
    }

    public void setTradingId(int tradingId) {
        this.tradingId = tradingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTradingType() {
        return tradingType;
    }

    public void setTradingType(int tradingType) {
        this.tradingType = tradingType;
    }

    public long getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(long tradingTime) {
        this.tradingTime = tradingTime;
    }

    public String getCounterParty() {
        return counterParty;
    }

    public void setCounterParty(String counterParty) {
        this.counterParty = counterParty;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTradingContent() {
        return tradingContent;
    }

    public void setTradingContent(String tradingContent) {
        this.tradingContent = tradingContent;
    }

    public int isDelete() {
        return isDelete;
    }

    public void setIsDelete(int delete) {
        isDelete = delete;
    }
}
