# Getting Started

This is Stripe Payment API BE side.
We have 3 endpoints:

* [API health check](http://localhost:4242/actuator/health)
* [POST Method that manage payments](http://localhost:4242/create-payment-intent)
* [POST method that interacts with stripe](http://localhost:4242/webhook)

### How to Configure

In order to start this BE service locally you have to

* Register your test account in stripe. [Stripe Test Dashboard](https://dashboard.stripe.com/test/dashboard)
* [Obtain your public and secret keys](https://dashboard.stripe.com/test/apikeys):
    - stripe.publishable.key=pk_test_5...
    - stripe.secret.key=sk_test_...
      Put them into application.properties file inside your project

* [Install stripe cli locally](https://docs.stripe.com/stripe-cli)
* Login in stripe cli
* [Configure your webhook](https://dashboard.stripe.com/test/webhooks
* Follow the instruction how to after + Add local listener
* stripe login
* stripe listen --forward-to localhost:4242/webhook
* Obtain your stripe.webhook.secret: whsec_7a75b3d5 ...
* Put it in application properties file:
  stripe.webhook.secret=whsec_7a75b3d5ad525085ce082f650fe9a20b4ee6956c478366cfc1d3ea00a6fcbae7

### Build and run

* Open project in IntellijIdea and build it with mvn clean install then run it
* If you are using console app then you need to install and configure maven on you local machine
* Apache Maven installation and configuration:
* [Windows](https://phoenixnap.com/kb/install-maven-windows))
* [Mac](https://www.digitalocean.com/community/tutorials/install-maven-mac-os)
* verify that maven was installed correctly: mvn -v
* Go to the root folder of the project where pom.xml is located
* Run the command mvn clean, mnv install
* Run the server: mcn spring-boot:run
*

[Verify you server is started]:(http://localhost:4242/actuator/health)

* Up and run client side [React application]: http://localhost:3000
* This will automatically send an order to the server and redirect you to the checkout page
* Verify that you can see payment related events in the stripe cli
* Verify that you can see payment related events on the [dashBoard](https://dashboard.stripe.com/test/events)
* Verify you payment is visible on the [Stripe transaction page](https://dashboard.stripe.com/test/payments)
