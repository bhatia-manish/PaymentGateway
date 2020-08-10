package com.paymentgateway.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentgateway.demo.model.*;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@Service
public class PaymentServiceImpl implements PaymentService {
    public PaymentResponse makePayment(Payment payment) {
        PaymentInfo paymentInfo = new PaymentInfo(payment.getMerchantRefNum(), payment.getAmount(), payment.getPaymentHandleToken());
        paymentInfo.setMerchantCustomerId(payment.getCustomerId());
        final String base64 = "Basic cHJpdmF0ZS03NzUxOkItcWEyLTAtNWYwMzFjZGQtMC0zMDJkMDIxNDQ5NmJlODQ3MzJhMDFmNjkwMjY4ZDNiOGViNzJlNWI4Y2NmOTRlMjIwMjE1MDA4NTkxMzExN2YyZTFhODUzMTUwNWVlOGNjZmM4ZTk4ZGYzY2YxNzQ4";
        ObjectMapper mapper = new ObjectMapper();
        String jsonInputString = null;
        try {
            jsonInputString = mapper.writeValueAsString(paymentInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        final JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Client client = ClientBuilder.newClient(new ClientConfig(jacksonJsonProvider));
        Entity payload = Entity.json(jsonInputString);
        Response response = client.target("https://api.test.paysafe.com/paymenthub/v1/payments")
                .request(String.valueOf(MediaType.APPLICATION_JSON))
                .header("Authorization", base64)
                .header("Simulator", "\"EXTERNAL\"")
                .header("Access-Control-Allow-Origin", "*")
                .post(payload);

        System.out.println("status: " + response.getStatus());
        System.out.println("headers: " + response.getHeaders());
        response.getHeaders().putSingle(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        PaymentResponse paymentResponse = response.readEntity(PaymentResponse.class);
        return paymentResponse;
    }

    @Override
    public TokenResponse getSingleUseCustomerToken(TokenInfo tokenInfo) {
        final String base64 = "Basic cHJpdmF0ZS03NzUxOkItcWEyLTAtNWYwMzFjZGQtMC0zMDJkMDIxNDQ5NmJlODQ3MzJhMDFmNjkwMjY4ZDNiOGViNzJlNWI4Y2NmOTRlMjIwMjE1MDA4NTkxMzExN2YyZTFhODUzMTUwNWVlOGNjZmM4ZTk4ZGYzY2YxNzQ4";
        ObjectMapper mapper = new ObjectMapper();
        String jsonInputString = null;
        try {
            jsonInputString = mapper.writeValueAsString(tokenInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        final JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Client client = ClientBuilder.newClient(new ClientConfig(jacksonJsonProvider));
        Entity payload = Entity.json(jsonInputString);
        Response response = client.target("https://api.test.paysafe.com/paymenthub/v1/customers/" + tokenInfo.getCustomerId() + "/singleusecustomertokens")
                .request(String.valueOf(MediaType.APPLICATION_JSON))
                .header("Authorization", base64)
                .header("Simulator", "\"EXTERNAL\"")
                .post(payload);

        System.out.println("status: " + response.getStatus());
        System.out.println("headers: " + response.getHeaders());
        response.getHeaders().putSingle(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        return response.readEntity(TokenResponse.class);


    }
}
