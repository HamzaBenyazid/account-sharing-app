package com.winchesters.accountsharingapp.request;

import java.util.List;

public interface RequestServiceInterface {

    void createRequest(Long offerId);
    Request findRequestById(Long id);
    List<Request> getRequestsToUser(String username);
    void deleteRequest(Long requestId);
    List<Request> getRequestsForOffer(Long offerId);
}
