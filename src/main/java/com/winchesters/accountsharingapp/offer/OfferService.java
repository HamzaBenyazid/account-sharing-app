package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.account.AccountProvider;
import com.winchesters.accountsharingapp.auth.AuthenticationFacade;
import com.winchesters.accountsharingapp.dto.OfferResponseDto;
import com.winchesters.accountsharingapp.exception.offer.MaxSplittersExceededException;
import com.winchesters.accountsharingapp.exception.offer.OfferNotEmptyException;
import com.winchesters.accountsharingapp.exception.offer.OfferNotFoundException;
import com.winchesters.accountsharingapp.mapper.EntityToDtoMapper;
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
    private final AuthenticationFacade authenticationFacade;

    @Override
    public void createOffer(Offer offer) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        Double calculatedPrice = offer.getAccount().getSubscription().getPrice() /offer.getMaxSplitters();
        offer.setCalculatedPrice(calculatedPrice);
        offer.setOfferer(userService.getUser());
        offer.setUploadDate(date);
        offerRepository.save(offer);
    }

    @Override
    public Offer getOfferById(Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException("offer with id " + offerId + " not found."));
        return offer;
    }

    @Override
    public Page<Offer> getOffersByOfferer(String username, int pageNumber, int pageSize) {
        Pageable sortedByDate = PageRequest.of(pageNumber - 1, pageSize, Sort.by("uploadDate").descending());
        return offerRepository.findByOffererUsername(username, sortedByDate);
    }

    @Override
    public Page<Offer> getByCalculatedPriceAndAccount_Provider(Double calculatedPrice, AccountProvider provider, int pageNumber, int pageSize) {
        Pageable sortedByDate = PageRequest.of(pageNumber - 1, pageSize, Sort.by("uploadDate").descending());
        return offerRepository.findByCalculatedPriceAndAccount_Provider(calculatedPrice,provider,sortedByDate);
    }

    @Override
    public OfferResponseDto updateMaxSplitters(Long offerId, Integer maxSplitters) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalStateException("offer with id : "+offerId+" not found"));
        if(offer.getAccount().getSubscription().getMaxUsers()>= maxSplitters){
            throw new MaxSplittersExceededException("Maximum number of splitters exceeded");
        }
        String username = authenticationFacade.getAuthenticatedUsername();
        if(username.equals("anonymousUser")){
            throw new IllegalStateException("user must be authenticated.");
        }
        if (!username.equals(offer.getOfferer().getUsername()))
            throw new IllegalStateException("You can't update this offer, you are not the creator");

        if(maxSplitters!=null){
            offer.setMaxSplitters(maxSplitters);
        }
        offerRepository.save(offer);
        return EntityToDtoMapper.offerToOfferResponseDto(offer);

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
