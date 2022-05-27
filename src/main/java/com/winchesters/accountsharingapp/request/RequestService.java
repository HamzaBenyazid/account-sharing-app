package com.winchesters.accountsharingapp.request;

import com.google.common.collect.ImmutableList;
import com.winchesters.accountsharingapp.exception.request.InvalidRequesterException;
import com.winchesters.accountsharingapp.exception.request.RequestNotFoundException;
import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.offer.OfferRepository;
import com.winchesters.accountsharingapp.offer.OfferService;
import com.winchesters.accountsharingapp.user.User;
import com.winchesters.accountsharingapp.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RequestService implements RequestServiceInterface {

    private final RequestRepository requestRepository;
    private final UserService userService;
    private final OfferService offerService;
    private final OfferRepository offerRepository;

    @Override
    public void createRequest(Long offerId) {
        Request request = new Request();
        User user = userService.getUser();
        if (!(user.getOfferings() == null)) {
            if (user.getOfferings().stream().map(Offer::getId).toList().contains(offerId))
                throw new InvalidRequesterException("Can't request owned offer");
        }
        request.setRequester(user);
        Offer offer = offerService.getOfferById(offerId);
        request.setOffer(offer);
        request.setStatus(RequestStatus.WAITING);
        requestRepository.save(request);
    }

    @Override
    public Request findRequestById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));
    }

    @Override
    public List<Request> getRequestsToUser() {
        return requestRepository.findAllByRequesterUsername(userService.getUser().getUsername());
    }

    @Override
    public void deleteRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalStateException("request not found"));
        requestRepository.delete(request);
    }

    @Override
    public List<Request> getRequestsForOffer(Long offerId) {
        return requestRepository.findAllByOfferId(offerId);
    }

    @Override
    public void acceptRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalStateException("request not found"));
        request.setStatus(RequestStatus.ACCEPTED);
        Offer offer = offerService.getOfferById(request.getOffer().getId());
        List<User> splitters = new ArrayList<>(offer.getSplitters());
        splitters.add(request.getRequester());
        offer.setSplitters(splitters);
        requestRepository.save(request);
        offerRepository.save(offer);
    }

    @Override
    public void denyRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalStateException("request not found"));
        request.setStatus(RequestStatus.DENIED);
        requestRepository.save(request);
    }

}
