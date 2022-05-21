package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OfferService {
    private static final Logger LOG = LoggerFactory.getLogger(OfferService.class);
    private final OfferRepository offerRepository;
    private final UserService userService;

    public void createOffer(Offer offer) {
        offer.setCreator(userService.getUser());
        offerRepository.save(offer);
    }
}
