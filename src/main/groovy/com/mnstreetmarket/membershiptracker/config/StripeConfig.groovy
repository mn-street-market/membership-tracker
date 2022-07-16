package com.mnstreetmarket.membershiptracker.config

import com.stripe.Stripe
import io.github.cdimascio.dotenv.Dotenv
import org.springframework.context.annotation.Configuration

@Configuration
class StripeConfig {
    
    final String secretKey
    final String publicKey
    final String webhookSecret
    final String paymentMethodTypes
    final String membershipPrice
    final String studentPrice

    StripeConfig(Dotenv dotenv) {
        secretKey = dotenv.get("STRIPE_PUBLISHABLE_KEY")
        publicKey = dotenv.get("STRIPE_SECRET_KEY")
        webhookSecret = dotenv.get("STRIPE_WEBHOOK_SECRET")
        paymentMethodTypes = dotenv.get("PAYMENT_METHOD_TYPES")
        membershipPrice = dotenv.get("PRICE")
        studentPrice = dotenv.get("STUDENT_PRICE")

        Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY")
        Stripe.setAppInfo(
                "stripe-samples/checkout-one-time-payments",
                "0.0.1",
                "https://github.com/stripe-samples/checkout-one-time-payments"
        )
    }
    
}
