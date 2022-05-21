package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.account.AccountProvider;
import com.winchesters.accountsharingapp.exception.offer.OfferNotEmptyException;
import com.winchesters.accountsharingapp.exception.offer.OfferNotFoundException;
import com.winchesters.accountsharingapp.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OfferService implements OfferServiceInterface {
    // private static final Logger LOG = LoggerFactory.getLogger(OfferService.class);
    private final OfferRepository offerRepository;
    private final UserService userService;

    @Override
    public void createOffer(Offer offer) {
        //todo : set calculatedProce
        offer.setOfferer(userService.getUser());
        offerRepository.save(offer);
    }

    @Override
    public Offer getOfferById(Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException("offer with id " + offerId + " not found."));
        return offer;
    }

    @Override
    public Page<Offer> getOffersByOfferer(Long offererId, int pageNumber, int pageSize) {
        Pageable sortedByDate = PageRequest.of(pageNumber - 1, pageSize, Sort.by("uploadDate").descending());
        return offerRepository.findByOffererId(offererId, sortedByDate);
    }

    @Override
    public Page<Offer> getByCalculatedPriceAndAccount_Provider(Double calculatedPrice, AccountProvider provider, int pageNumber, int pageSize) {
        Pageable sortedByDate = PageRequest.of(pageNumber - 1, pageSize, Sort.by("uploadDate").descending());
        return offerRepository.findByCalculatedPriceAndAccount_Provider(calculatedPrice,provider,sortedByDate);
    }

    @Override
    @Transactional
    public void updateMaxSplitters(Long offerId, Integer maxSplitters) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalStateException("offer with id : "+offerId+" not found"));
        if(maxSplitters!=null && offer.getAccount().getSubscription().getMaxUsers()>= maxSplitters){
            offer.setMaxSplitters(maxSplitters);
        }

    }

    @Override
    public void deteteOffer(Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalStateException("offer not found"));
        if (!(offer.getSplitters() == null)) {
            throw new OfferNotEmptyException("offer with id " + offerId + " not empty.");
        }

        offerRepository.delete(offer);
    }

    @Override
    public Page<Offer> getOffers(int pageNumber, int pageSize) {
        Pageable sortedByDate = PageRequest.of(pageNumber - 1, pageSize, Sort.by("uploadDate").descending());
        return offerRepository.findAll(sortedByDate);
    }


}
