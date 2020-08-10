package com.paymentgateway.demo.controller;

import com.paymentgateway.demo.model.*;
import com.paymentgateway.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @CrossOrigin
    @PostMapping
    public PaymentResponse makePayment(@RequestBody Payment payment) {
        return paymentService.makePayment(payment);
    }

    @CrossOrigin
    @PostMapping("/tokens")
    public TokenResponse getSingleUseCustomerToken(@RequestBody TokenInfo tokenInfo) {
        return paymentService.getSingleUseCustomerToken(tokenInfo);
    }
}
