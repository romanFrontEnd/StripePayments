package com.stripe.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import com.stripe.service.WebHookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookController {

    private static final Gson gson = new Gson();
    private final WebHookService webHookService;
    @Value("${stripe.secret.key}")
    private String apiKey = "apiKeyFromYourDashBoard";

    @Value("${stripe.webhook.secret}")
    private String endpointSecret = "endpointSecretFromYourCliOrDashboard";

    public WebhookController(WebHookService webHookService) {
        this.webHookService = webHookService;
    }

    @PostMapping("/webhook")
    @ResponseBody
    public ResponseEntity<String> webHookHandler(
            @RequestHeader("Stripe-Signature") String stripeSignature,
            @RequestBody String webHookBody
    ) {

        Stripe.apiKey = apiKey;
        Event event;
        try {
            event = Webhook.constructEvent(
                    webHookBody, stripeSignature, endpointSecret
            );
        } catch (JsonSyntaxException e) {
            return ResponseEntity.badRequest().body("Invalid payload");
        } catch (SignatureVerificationException e) {
            return ResponseEntity.badRequest().body("Invalid Signature");
        }

        // Deserialize the nested object inside the event
        webHookService.deserialize(event);
        // Handle the event
        webHookService.webHookHandler(event);

        return ResponseEntity.ok(gson.toJson(event.getType()));
    }
}
