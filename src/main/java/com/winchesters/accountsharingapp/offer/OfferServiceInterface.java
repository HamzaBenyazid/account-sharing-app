package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.account.AccountProvider;
import org.springframework.data.domain.Page;

public interface OfferServiceInterface {
    Page<Offer> getOffers(int pageNumber, int pageSize);
    void deteteOffer(Long offerId);
    void createOffer(Offer offer);
    Offer  getOfferById(Long offerId);

    Page<Offer> getOffersByOfferer(Long offererId,int pageNumber, int pageSize);

    Page<Offer> getByCalculatedPriceAndAccount_Provider(Double calculatedPrice , AccountProvider provider, int pageNumber, int pageSize);
    void updateMaxSplitters(Long offerId,Integer maxSplitters);

}
