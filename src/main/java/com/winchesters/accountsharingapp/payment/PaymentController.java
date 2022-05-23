package com.winchesters.accountsharingapp.payment;

import com.stripe.exception.StripeException;
import com.winchesters.accountsharingapp.payment.entity.ChargeRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequiredArgsConstructor
public class PaymentController implements IPaymentController{
    private static final Logger Log = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    @GetMapping("/test")
    @ResponseStatus( HttpStatus.CREATED )
    private ResponseEntity<String> test() {
        return new ResponseEntity<>("tested",HttpStatus.OK);
    }

    @Override
    public String checkout(Model model) {
        return paymentService.checkout(model);
    }

    @Override
    public void charge(ChargeRequest chargeRequest, Model model)
            throws StripeException {
        paymentService.charge(chargeRequest,model);
    }

    @Override
    public String createSubscription(String email, String token, String plan) {
        return paymentService.createSubscription(email,token,plan);
    }

    @Override
    public void cancelSubscription( @PathVariable String id) {
        paymentService.cancelSubscription(id);
    }
}
