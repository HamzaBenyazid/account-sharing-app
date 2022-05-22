package com.winchesters.accountsharingapp.offer;


import com.winchesters.accountsharingapp.account.AccountProvider;
import com.winchesters.accountsharingapp.dto.OfferResponseDto;
import com.winchesters.accountsharingapp.mapper.EntityToDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/offer")
@RequiredArgsConstructor
public class OfferController {
    //private static final Logger LOG = LoggerFactory.getLogger(OfferController.class);
    private final OfferService offerService;
    private final int pageSize=16;

    @PostMapping("/create")
    @ResponseStatus( HttpStatus.CREATED )
    private void connect(@Valid @RequestBody Offer offer) {
        offerService.createOffer(offer);
    }

    @GetMapping("/offers")
    public List<Offer> listOffers(@RequestParam int pageNumber,@RequestParam Double price,@RequestParam AccountProvider accountProvider){
       return  offerService.getByCalculatedPriceAndAccount_Provider(price,accountProvider,pageNumber,pageSize).stream().collect(Collectors.toList());
    }


    @GetMapping("/offers/{offerId}")
    public @ResponseBody OfferResponseDto getOffer(@PathVariable("offerId") Long offerId){
        return  EntityToDtoMapper.offerToOfferResponseDto(offerService.getOfferById(offerId));
    }

    @DeleteMapping("/offers/{offerId}")
    public void deleteOffer(@PathVariable("offerId") Long offerId){
        offerService.deteteOffer(offerId);
    }



    @PutMapping("offers/{offerId}")
    public OfferResponseDto updateImage(@PathVariable Long offerId,Integer maxSplitters){
        return offerService.updateMaxSplitters(offerId,maxSplitters);
    }



}
