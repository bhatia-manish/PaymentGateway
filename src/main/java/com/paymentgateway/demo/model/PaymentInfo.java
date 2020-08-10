package com.paymentgateway.demo.model;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentInfo {
    private String merchantRefNum;
    private int amount;
    private String paymentHandleToken;
    private String merchantCustomerId;

    public String getMerchantCustomerId() {
        return merchantCustomerId;
    }

    public void setMerchantCustomerId(String merchantCustomerId) {
        this.merchantCustomerId = merchantCustomerId;
    }

    private boolean dupCheck = true;
    private boolean settleWithAuth = false;
    private String currencyCode = "USD";
    private String customerIp = "172.0.0.1";
    private String description = "Magazine subscription";

    public PaymentInfo(String merchantRefNum, int amount, String paymentHandleToken) {
        this.paymentHandleToken = paymentHandleToken;
        this.amount = amount;
        this.merchantRefNum = merchantRefNum;
    }

    public String getMerchantRefNum() {

        return merchantRefNum;
    }

    public void setMerchantRefNum(String merchantRefNum) {
        this.merchantRefNum = merchantRefNum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPaymentHandleToken() {
        return paymentHandleToken;
    }

    public void setPaymentHandleToken(String paymentHandleToken) {
        this.paymentHandleToken = paymentHandleToken;
    }

    public boolean isDupCheck() {
        return dupCheck;
    }

    public void setDupCheck(boolean dupCheck) {
        this.dupCheck = dupCheck;
    }

    public boolean isSettleWithAuth() {
        return settleWithAuth;
    }

    public void setSettleWithAuth(boolean settleWithAuth) {
        this.settleWithAuth = settleWithAuth;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
