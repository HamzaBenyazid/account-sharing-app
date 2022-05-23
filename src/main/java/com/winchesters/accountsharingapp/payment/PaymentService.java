package com.winchesters.accountsharingapp.payment;

import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.winchesters.accountsharingapp.account.AccountService;
import com.winchesters.accountsharingapp.payment.entity.ChargeRequest;
import com.winchesters.accountsharingapp.payment.entity.Currency;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class PaymentService {
    private static final Logger Log = LoggerFactory.getLogger(PaymentService.class);
    @Value("${stripe_publishable_key}")
    private String stripePublicKey;
    @Value("${stripe_secret_key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public String checkout(Model model) {
        //TODO
        return null;
    }
    public Charge charge(ChargeRequest chargeRequest)
            throws AuthenticationException, StripeException{
        //TODO
        return null;
    }
    public void charge(ChargeRequest chargeRequest,Model model)
            throws AuthenticationException, StripeException{
        //TODO
    }

    public String createSubscription(String email, String token, String plan) {
        //TODO
        return null;
    }

    public void cancelSubscription(String id) {
        // TODO
    }
}
