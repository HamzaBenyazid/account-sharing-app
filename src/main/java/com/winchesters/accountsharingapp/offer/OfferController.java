package com.winchesters.accountsharingapp.offer;


import com.winchesters.accountsharingapp.mapper.EntityToDtoMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/offer")
@RequiredArgsConstructor
public class OfferController {
    //private static final Logger LOG = LoggerFactory.getLogger(OfferController.class);
    private final OfferService offerService;
    private final int pageSize;

    @PostMapping("/create")
    @ResponseStatus( HttpStatus.CREATED )
    private void connect(@Valid @RequestBody Offer offer) {
        offerService.createOffer(offer);
    }

    @GetMapping("/offers")
    public List<Offer> listOffers(@RequestParam int pageNumber){
        return offerService.getOffers(pageNumber,pageSize).getContent();
    }

    @GetMapping("/offers/{offerId}")
    public @ResponseBody OfferResponseDto getOffer(@PathVariable("offerId") Long offerId){
        return  EntityToDtoMapper.offerToOfferResponseDto(offerService.getOfferById(offerId));
    }




}
