package com.winchesters.accountsharingapp.offer;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/offer")
@RequiredArgsConstructor
public class OfferController {
    //private static final Logger LOG = LoggerFactory.getLogger(OfferController.class);
    private final OfferService offerService;

    @PostMapping("/create")
    @ResponseStatus( HttpStatus.CREATED )
    private void connect(@Valid @RequestBody Offer offer) {
        offerService.createOffer(offer);
    }

}
