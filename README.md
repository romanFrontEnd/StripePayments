# StripePayments
Striper payment service integration FE->BE.
FE - React 18
BE - Java 21

How to build/run server BE: 
Follow the instruction stripe_be/Readme.md

How to build/run FE: 
Follow the instruction stripe_fe/Readme.md


# Stripe Payment Client app

For simplicity I have created an order from 1 prebuild item

items: [{ id: "xl-tshirt" , amount: "1"}] and immediatly send this order to the server
Then server redirect me to the Checkout page where you can enter card details and test your application 

## Running the sample

1. Build/run the server see stripe_be/Readme.md


2. Build the client app see stripe_fe/Readme.md


## Test cards 


## Verify you see notifications 