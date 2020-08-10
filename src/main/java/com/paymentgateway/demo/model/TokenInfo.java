package com.paymentgateway.demo.model;

import java.util.Arrays;
import java.util.List;

public class TokenInfo {
    private String merchantRefNum;
    private String customerId;
    List<String> paymentTypes = Arrays.asList("CARD");

    public String getMerchantRefNum() {
        return merchantRefNum;
    }

    public void setMerchantRefNum(String merchantRefNum) {
        this.merchantRefNum = merchantRefNum;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
