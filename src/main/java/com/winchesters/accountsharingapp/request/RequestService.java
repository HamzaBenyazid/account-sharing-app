package com.winchesters.accountsharingapp.request;

import com.winchesters.accountsharingapp.exception.request.RequestNotFoundException;
import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.offer.OfferService;
import com.winchesters.accountsharingapp.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RequestService implements RequestServiceInterface{

    private final RequestRepository requestRepository;
    private final UserService userService;
    private final OfferService offerService;

    @Override
    public void createRequest(Long offerId) {
        Request request = new Request();
        request.setRequester(userService.getUser());
        Offer offer = offerService.getOfferById(offerId);
        request.setOffer(offer);
        request.setStatus(RequestStatus.WAITING);
        requestRepository.save(request);
    }

    @Override
    public Request findRequestById(Long id){
        return requestRepository.findById(id)
                .orElseThrow(()->new RequestNotFoundException(id));
    }

    @Override
    public List<Request> getRequestsToUser(){
        return requestRepository.findAllByOfferOfferer(userService.getUser().getUsername());
    }

    @Override
    public void deleteRequest(Long requestId){
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalStateException("request not found"));
        requestRepository.delete(request);
    }
    @Override
    public List<Request> getRequestsForOffer(Long offerId){
        return requestRepository.findAllByOfferId(offerId);
    }

    @Override
    public void acceptRequest(Long requestId){
        Request request = requestRepository.findById(requestId)
                .orElseThrow(()-> new IllegalStateException("request not found"));
        request.setStatus(RequestStatus.ACCEPTED);
        requestRepository.save(request);
    }

    @Override
    public void denyRequest(Long requestId){
        Request request = requestRepository.findById(requestId)
                .orElseThrow(()-> new IllegalStateException("request not found"));
        request.setStatus(RequestStatus.DENIED);
        requestRepository.save(request);
    }

}
