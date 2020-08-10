package com.paymentgateway.demo.service;

import com.paymentgateway.demo.model.Payment;
import com.paymentgateway.demo.model.PaymentResponse;
import com.paymentgateway.demo.model.TokenInfo;
import com.paymentgateway.demo.model.TokenResponse;

public interface PaymentService {
    PaymentResponse makePayment(Payment payment);

    TokenResponse getSingleUseCustomerToken(TokenInfo tokenInfo);
}
