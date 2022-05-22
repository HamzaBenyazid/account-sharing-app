package com.winchesters.accountsharingapp.request;

import java.util.List;

public interface RequestServiceInterface {

    void createRequest(Long offerId);
    Request findRequestById(Long id);
    List<Request> getRequestsToUser();
    void deleteRequest(Long requestId);
    List<Request> getRequestsForOffer(Long offerId);
    void denyRequest(Long requestId);
    void acceptRequest(Long requestId);
}
