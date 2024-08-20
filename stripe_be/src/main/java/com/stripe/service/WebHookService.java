package com.stripe.service;

import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import org.springframework.stereotype.Service;

@Service
public class WebHookService {

    public void webHookHandler(Event event) {
        switch (event.getType()) {
            case "payment_intent.amount_capturable_updated": {
                // Then define and call a function to handle the event payment_intent.amount_capturable_updated
                System.out.println("payment_intent.amount_capturable_updated");
                break;
            }
            case "payment_intent.canceled": {
                // Then define and call a function to handle the event payment_intent.canceled
                System.out.println("payment_intent.canceled");
                break;
            }
            case "payment_intent.created": {
                // Then define and call a function to handle the event payment_intent.created
                System.out.println("payment_intent.created");
                break;
            }
            case "payment_intent.partially_funded": {
                // Then define and call a function to handle the event payment_intent.partially_funded
                System.out.println("payment_intent.partially_funded");
                break;
            }
            case "payment_intent.payment_failed": {
                // Then define and call a function to handle the event payment_intent.payment_failed
                System.out.println("payment_intent.payment_failed");
                break;
            }
            case "payment_intent.processing": {
                // Then define and call a function to handle the event payment_intent.processing
                System.out.println("payment_intent.processing");
                break;
            }
            case "payment_intent.requires_action": {
                // Then define and call a function to handle the event payment_intent.requires_action
                System.out.println("payment_intent.requires_action");
                break;
            }
            case "payment_intent.succeeded": {
                // Then define and call a function to handle the event payment_intent.succeeded
                System.out.println("payment_intent.succeeded");
                break;
            }
            // ... handle other event types
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }
    }

    public StripeObject deserialize(Event event) {
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }
        return stripeObject;
    }
}
