package com.winchesters.accountsharingapp.offer;


import com.winchesters.accountsharingapp.account.AccountProvider;
import com.winchesters.accountsharingapp.dto.CreateOfferDto;
import com.winchesters.accountsharingapp.dto.OfferResponseDto;
import com.winchesters.accountsharingapp.mapper.EntityToDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/offer")
@RequiredArgsConstructor
public class OfferController {
    //private static final Logger LOG = LoggerFactory.getLogger(OfferController.class);
    @Autowired
    private final OfferService offerService;
    private final int pageSize=16;

    @PostMapping(value = "",consumes = "application/json")
    @ResponseStatus( HttpStatus.CREATED )
    private OfferResponseDto createOffer(@Valid @RequestBody CreateOfferDto offer) {
        return  offerService.createOffer(offer);
    }

    @GetMapping
    public List<Offer> filterOffers(@RequestParam Integer pageNumber,@RequestParam Double price,@RequestParam AccountProvider accountProvider){
       return  offerService.getByCalculatedPriceAndAccount_Provider(price,accountProvider,pageNumber,pageSize).stream().collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<OfferResponseDto> listOffers(){
        return  offerService.listOffers();
    }

    @GetMapping("/{offerId}")
    public @ResponseBody OfferResponseDto getOffer(@PathVariable("offerId") Long offerId){
        return  EntityToDtoMapper.offerToOfferResponseDto(offerService.getOfferById(offerId));
    }

    @DeleteMapping("/{offerId}")
    public void deleteOffer(@PathVariable("offerId") Long offerId){
        offerService.deteteOffer(offerId);
    }



    @PutMapping("/{offerId}")
    public OfferResponseDto updateImage(@PathVariable Long offerId,Integer maxSplitters){
        return offerService.updateMaxSplitters(offerId,maxSplitters);
    }

    @GetMapping("{offerId}/subscribers")
    public List<String> listOfferSubscribers(@PathVariable Long offerId){
        return offerService.listOfferSubscribers(offerId);
    }

    @DeleteMapping("{offerId}/remove-subscriber")
    public void removeSubscriber(@PathVariable Long offerId,@RequestBody String username){
        offerService.removeSubscriber(offerId,username);
    }

    @GetMapping("{offerId}/unsubscribe")
    public void unsubscribe(@PathVariable Long offerId){
        offerService.unsubscribe(offerId);
    }


}
