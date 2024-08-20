package com.stripe.controller;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.dto.CreatePayment;
import com.stripe.dto.CreatePaymentResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private static final Gson gson = new Gson();
    private final String USD = "usd";
    private final PaymentService paymentService;
    @Value("${stripe.secret.key}")
    private String apiKey = "default";


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-payment-intent")
    @ResponseBody
    public String createPaymentIntent(
            @RequestBody CreatePayment createPayment
    ) throws StripeException {

        Stripe.apiKey = apiKey;

        PaymentIntentCreateParams params = paymentService.preparePaymentIntent(createPayment, USD);
        // Create a PaymentIntent with the order amount and currency
        PaymentIntent paymentIntent = PaymentIntent.create(params);

        CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret());
        return gson.toJson(paymentResponse);
    }
}

