package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.account.AccountProvider;
import com.winchesters.accountsharingapp.account.AccountService;
import com.winchesters.accountsharingapp.auth.AuthenticationFacade;
import com.winchesters.accountsharingapp.dto.CreateOfferDto;
import com.winchesters.accountsharingapp.dto.OfferResponseDto;
import com.winchesters.accountsharingapp.exception.offer.InvalidMaxSplitterValue;
import com.winchesters.accountsharingapp.exception.offer.OfferNotEmptyException;
import com.winchesters.accountsharingapp.exception.offer.OfferNotFoundException;
import com.winchesters.accountsharingapp.mapper.EntityToDtoMapper;
import com.winchesters.accountsharingapp.user.User;
import com.winchesters.accountsharingapp.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OfferService implements OfferServiceInterface {
    // private static final Logger LOG = LoggerFactory.getLogger(OfferService.class);
    private final OfferRepository offerRepository;
    private final AccountService accountService;
    private final UserService userService;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public OfferResponseDto createOffer(CreateOfferDto dto) {
        Offer offer = new Offer();
        offer.setOfferer(userService.getUser());
        offer.setAccount(accountService.findAccountById(dto.accountId()));
        offer.setMaxSplitters(dto.maxSplitters());
        offer.setCalculatedPrice(offer.getAccount().getSubscription().getPrice() /dto.maxSplitters());

        Date date = new Date(new Date().getTime());
        offer.setUploadDate(date);

        offer = offerRepository.save(offer);
        return EntityToDtoMapper.offerToOfferResponseDto(offer);
    }

    @Override
    public Offer getOfferById(Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException("offer with id " + offerId + " not found."));
        return offer;
    }

    @Override
    public Page<Offer> getOffersByOfferer(String username, Integer pageNumber, Integer pageSize) {
        if(pageNumber == null) pageNumber=1;
        Pageable sortedByDate = PageRequest.of(pageNumber - 1, pageSize, Sort.by("uploadDate").descending());
        return offerRepository.findByOffererUsername(username, sortedByDate);
    }

    @Override
    public Page<Offer> getByCalculatedPriceAndAccount_Provider(Double calculatedPrice, AccountProvider provider, Integer pageNumber, Integer pageSize) {
        if(pageNumber == null) pageNumber=1;
        Pageable sortedByDate = PageRequest.of(pageNumber - 1, pageSize, Sort.by("uploadDate").descending());
        return offerRepository.findByCalculatedPriceAndAccount_Provider(calculatedPrice,provider,sortedByDate);
    }

    @Override
    public OfferResponseDto updateMaxSplitters(Long offerId, Integer maxSplitters) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalStateException("offer with id : "+offerId+" not found"));
        if(offer.getAccount().getSubscription().getMaxUsers() < maxSplitters || offer.getSplitters().size()> maxSplitters) {
            throw new InvalidMaxSplitterValue("Invalid maximum number of splitters");
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
    public Page<Offer> getOffers(Integer pageNumber, Integer  pageSize) {
        if(pageNumber == null) pageNumber=1;
        Pageable sortedByDate = PageRequest.of(pageNumber - 1, pageSize, Sort.by("uploadDate").descending());
        return offerRepository.findAll(sortedByDate);
    }


    public List<OfferResponseDto> listOffers() {
        return EntityToDtoMapper.offerToOfferResponseDto(offerRepository.findAll());

    }

    public List<String> listOfferSubscribers(Long offerId) {
        return this.getOfferById(offerId).getSplitters().stream().map(User::getUsername).toList();
    }

    public void removeSubscriber(Long offerId,String username) {
        Offer offer = getOfferById(offerId);
        offer.removeSplitter(username);
    }

    public void unsubscribe(Long offerId) {
        String  username = userService.getUser().getUsername();
        Offer offer = getOfferById(offerId);

        offer.removeSplitter(username);
    }
}
