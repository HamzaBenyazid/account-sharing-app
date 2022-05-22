package com.winchesters.accountsharingapp.payment;

import com.winchesters.accountsharingapp.account.AccountService;
import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.offer.OfferService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private static final Logger Log = LoggerFactory.getLogger(PaymentController.class);
//    private final PaymentService paymentService;

    @GetMapping("/test")
    @ResponseStatus( HttpStatus.CREATED )
    private ResponseEntity<String> test() {
        return new ResponseEntity<>("tested",HttpStatus.OK);
    }
}
