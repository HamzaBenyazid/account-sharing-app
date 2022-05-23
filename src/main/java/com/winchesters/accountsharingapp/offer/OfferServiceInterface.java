package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.account.AccountProvider;
import com.winchesters.accountsharingapp.dto.CreateOfferDto;
import com.winchesters.accountsharingapp.dto.OfferResponseDto;
import org.springframework.data.domain.Page;

public interface OfferServiceInterface {
    Page<Offer> getOffers(Integer pageNumber, Integer pageSize);

    OfferResponseDto createOffer(CreateOfferDto dto) ;
    void deteteOffer(Long offerId);
    Offer  getOfferById(Long offerId);

    Page<Offer> getOffersByOfferer(String username,Integer pageNumber, Integer pageSize);

    Page<Offer> getByCalculatedPriceAndAccount_Provider(Double calculatedPrice , AccountProvider provider, Integer pageNumber, Integer pageSize);
    OfferResponseDto updateMaxSplitters(Long offerId, Integer maxSplitters);

}
