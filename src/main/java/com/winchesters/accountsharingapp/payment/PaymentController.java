package com.winchesters.accountsharingapp.payment;

import com.stripe.exception.StripeException;
import com.winchesters.accountsharingapp.payment.entity.ChargeRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/payment")
public class PaymentController implements IPaymentController{
    private static final Logger Log = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    @PostMapping(value = "/testing")
    @ResponseStatus( HttpStatus.CREATED )

    private ResponseEntity<String> test( HttpServletRequest request) throws StripeException {

return paymentService.test("oubauda56@gma.com","token",100);
    }

    @PostMapping(value = "/checkout",consumes = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public void checkout(@RequestBody ChargeRequest chargeRequest) throws StripeException {
         paymentService.checkout(chargeRequest);
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
