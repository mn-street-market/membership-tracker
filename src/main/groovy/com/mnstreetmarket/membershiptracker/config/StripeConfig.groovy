package com.mnstreetmarket.membershiptracker.config

import com.stripe.Stripe
import io.github.cdimascio.dotenv.Dotenv
import org.springframework.context.annotation.Configuration

@Configuration
class StripeConfig {

    final Dotenv dotenv = Dotenv.load()
    final String secretKey
    final String publicKey
    final String domainUrl
    final String webhookSecret
    final String paymentMethodTypes
    
    StripeConfig() {
        secretKey = dotenv.get("STRIPE_PUBLISHABLE_KEY")
        publicKey = dotenv.get("STRIPE_SECRET_KEY")
        domainUrl = dotenv.get("STRIPE_WEBHOOK_SECRET")
        webhookSecret = dotenv.get("DOMAIN")
        paymentMethodTypes = dotenv.get("PAYMENT_METHOD_TYPES")

        Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY")
        Stripe.setAppInfo(
                "stripe-samples/checkout-one-time-payments",
                "0.0.1",
                "https://github.com/stripe-samples/checkout-one-time-payments"
        )
    }
    
}
