package com.winchesters.accountsharingapp.payment;

import com.stripe.exception.StripeException;
import com.winchesters.accountsharingapp.payment.entity.ChargeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/payment")
public interface IPaymentController {

    @RequestMapping("/checkout")
    @ResponseStatus( HttpStatus.OK )
    String checkout(Model model);

    @PostMapping("/charge")
    @ResponseStatus( HttpStatus.OK )
    void charge(ChargeRequest chargeRequest, Model model) throws StripeException;

    @PostMapping("/create-subscription")
    @ResponseStatus( HttpStatus.CREATED )
    String createSubscription(String email, String token, String plan);

    @DeleteMapping("/cancel-subscription/{id}")
    @ResponseStatus( HttpStatus.OK )
    void cancelSubscription(@PathVariable String id);
}
