package com.stripe.service;

import com.stripe.dto.CreatePayment;
import com.stripe.dto.CreatePaymentItem;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public long calculateOrderAmount(CreatePaymentItem[] items) {
        // Calculate the order total on the server to prevent
        // people from directly manipulating the amount on the client
        long total = 0L;
        for (CreatePaymentItem item : items) {
            total += item.getAmount();
        }
        return total;
    }

    public PaymentIntentCreateParams preparePaymentIntent(CreatePayment createPayment, String currency) {
        return PaymentIntentCreateParams.builder()
                .setAmount(calculateOrderAmount(createPayment.getItems()))
                .setCurrency(currency)
                // In the latest version of the API, specifying the `automatic_payment_methods` parameter is optional because Stripe enables its functionality by default.
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods
                                .builder()
                                .setEnabled(true)
                                .build()
                )
                .build();
    }
}
