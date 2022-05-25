package com.winchesters.accountsharingapp.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.winchesters.accountsharingapp.payment.entity.ChargeRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Charge checkout(ChargeRequest chargeRequest) throws StripeException {
        Map<String,Object> chargeParameters = Map.of(
                "currency",chargeRequest.getCurrency(),
                "amount",chargeRequest.getAmount(),
                "source",chargeRequest.getStripeToken(),
                "description",chargeRequest.getDescription()
//                ",email",chargeRequest.getStripeEmail()
        );
        return Charge.create(chargeParameters);
    }
    public String createCharge(String email, String token, int amount) throws StripeException {

        String chargeId = null;

            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("description","Charge for "+email);
            chargeParams.put("currency","usd");
            chargeParams.put("amount",amount);
            chargeParams.put("source",token);

            Charge charge = Charge.create(chargeParams);

            chargeId = charge.getId();
        return chargeId;
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

    public ResponseEntity<String> test(String email, String token, int amount) throws StripeException {
        Customer customer = createCustomer(email,token);
        String charge = createCharge(customer.getEmail(),token,amount);
        return new ResponseEntity<>(charge, HttpStatus.OK);
    }
    public Customer createCustomer(String email, String token) throws StripeException {
        String id = null;
            Map<String, Object> customerParams = new HashMap<>();
            customerParams.put("description", "Customer for " + email);
            customerParams.put("email", email);
            // obtained with stripe.js
            // token id
            customerParams.put("source", token);
            return Customer.create(customerParams);
    }

}
