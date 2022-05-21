package com.winchesters.accountsharingapp.payment;

import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.offer.OfferService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/payment")
@RequiredArgsConstructor
public class PaymentController {
//    private final PaymentService paymentService;

    @GetMapping("/test")
    @ResponseStatus( HttpStatus.CREATED )
    private ResponseEntity<String> test() {
        return new ResponseEntity<>("tested",HttpStatus.OK);
    }
}
